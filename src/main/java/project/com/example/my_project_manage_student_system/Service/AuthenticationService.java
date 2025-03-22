package project.com.example.my_project_manage_student_system.Service;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import project.com.example.my_project_manage_student_system.Dto.Request.AuthenticationRequest;
import project.com.example.my_project_manage_student_system.Dto.Request.IntrospectRequest;
import project.com.example.my_project_manage_student_system.Dto.Response.AuthenticationResponse;
import project.com.example.my_project_manage_student_system.Dto.Response.IntrospectResponse;
import project.com.example.my_project_manage_student_system.Entity.InvalidatedToken;
import project.com.example.my_project_manage_student_system.Entity.User;
import project.com.example.my_project_manage_student_system.Exception.AppException;
import project.com.example.my_project_manage_student_system.Exception.ErrorCode;
import project.com.example.my_project_manage_student_system.Repository.InvalidTokenRepository;
import project.com.example.my_project_manage_student_system.Repository.UserRepository;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AuthenticationService {

    @NonFinal
    @Value("${jwt.signerKey}")
    private String SIGNER_KEY;

    @NonFinal
    @Value("${jwt.refresh-duration}")
    private int REFRESH_DURATION;
    // nonfinal vì @RequiredArgsConstructor tạo constructor tự động chỉ cho các trường final.
    // Các trường không final, như SIGNER_KEY, được inject bởi @Value và yêu cầu Spring xử lý thông qua setter
    // hoặc reflection. Nếu chưa được xử lý đúng, có thể gây lỗi.
    @NonFinal
    @Value("${jwt.valid-duration}")
    private int VALID_DURATION;

    UserRepository userRepository;
    InvalidTokenRepository invalidTokenRepository;
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest){
        User user = userRepository.findByUserId(authenticationRequest.getUserId()).orElseThrow(() -> new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION));
        // verify password
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        boolean authenticated = passwordEncoder.matches(authenticationRequest.getPassword(), user.getPassword());
        if(!authenticated){
            throw new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION);
        }
        // generate token
        String token = generateToken(user);
        return AuthenticationResponse.builder().token(token).authenticated(true).build();
    }

    private String generateToken(User user){
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet jwtClaimsSet =new JWTClaimsSet.Builder()
                .subject(user.getUserId())
                .issuer("manage_student")
                .issueTime(new Date())
                // con tiep
                .claim("scope", buildScope(user))
                .expirationTime(new Date(Instant.now().plus(VALID_DURATION,ChronoUnit.SECONDS).toEpochMilli()))
                .jwtID(UUID.randomUUID().toString())
                .build();
        Payload payload =new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject =new JWSObject(jwsHeader, payload);
        // ki so
        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return  jwsObject.serialize(); //serialize(): Chuyển đổi JWT đã ký thành chuỗi định dạng Base64Url (header.payload.signature).
        } catch (JOSEException e){
            log.error("Cannot generate token", e);
            throw new RuntimeException(e);
        }
    }

    public IntrospectResponse introspect(IntrospectRequest introspectRequest) throws ParseException, JOSEException {
        String token = introspectRequest.getToken();
        boolean isValid = true;
        try{
            verifyToken(token, false);
        } catch (AppException e){
            isValid = false;
        }

        return IntrospectResponse.builder().isValid(isValid).build();
    }

    private SignedJWT verifyToken(String token, boolean isRefresh) throws JOSEException, ParseException {
        JWSVerifier jwsVerifier =new MACVerifier(SIGNER_KEY.getBytes());
        SignedJWT signedJWT =SignedJWT.parse(token);

        var verified =signedJWT.verify(jwsVerifier);

        Date expiryTime = (isRefresh) ? new Date(signedJWT
                .getJWTClaimsSet()
                .getIssueTime()
                .toInstant()
                .plus(REFRESH_DURATION, ChronoUnit.SECONDS)
                .toEpochMilli()) : signedJWT.getJWTClaimsSet().getExpirationTime();

        if(!(verified && expiryTime.after(new Date()))){
            throw new AppException(ErrorCode.UN_AUTHENTICATED);
        }

        // nếu tồn tại các token đã hết hạn hoặc đã đăng xuất
        if(invalidTokenRepository.existsById(signedJWT.getJWTClaimsSet().getJWTID())){
            throw new AppException(ErrorCode.TOKEN_INVALID);
        }
        return signedJWT;
    }

    public AuthenticationResponse refreshToken(IntrospectRequest request) throws ParseException, JOSEException {
        SignedJWT signedToken = verifyToken(request.getToken(), true);
        String jit =signedToken.getJWTClaimsSet().getJWTID();
        Date expiryTime =signedToken.getJWTClaimsSet().getExpirationTime();

        InvalidatedToken invalidatedToken = InvalidatedToken.builder().id(jit).expiryTime(expiryTime).build();
        invalidTokenRepository.save(invalidatedToken);

        String userId =signedToken.getJWTClaimsSet().getSubject();
        var user =userRepository.findByUserId(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        var token = generateToken(user);

        return AuthenticationResponse.builder().token(token).authenticated(true).build();
    }

    private String buildScope(User user){
        StringJoiner joiner =new StringJoiner(" ");
        if(!CollectionUtils.isEmpty((Collection<?>) user.getRole())){
            joiner.add("ROLE_" + user.getRole().getRoleName());
            if(!CollectionUtils.isEmpty(user.getRole().getPermissionSet())){
                user.getRole().getPermissionSet().forEach(permission -> joiner.add(permission.getPermissionName()));
            }
        }
        return  joiner.toString();
    }

    public void logout(IntrospectRequest request) throws JOSEException, ParseException{
        try{
            // phải refresh vì ngăn chặn việc sử dụng lại token khi logout
            SignedJWT signedToken = verifyToken(request.getToken(), true);
            String jit = signedToken.getJWTClaimsSet().getJWTID();
            Date expiryTime =signedToken.getJWTClaimsSet().getExpirationTime();

            InvalidatedToken invalidatedToken = InvalidatedToken.builder().expiryTime(expiryTime).id(jit).build();
            invalidTokenRepository.save(invalidatedToken);

        } catch (AppException e){
            log.error("Token alreadt expired");
            throw new AppException(ErrorCode.TOKEN_INVALID);
        }
    }
}
