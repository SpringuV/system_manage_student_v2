package project.com.example.my_project_manage_student_system.SecurityConfiguration;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import project.com.example.my_project_manage_student_system.Dto.ApiResponse;
import project.com.example.my_project_manage_student_system.Exception.ErrorCode;

import java.io.IOException;

@Component
@Slf4j
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        ErrorCode errorCode = ErrorCode.TOKEN_INVALID;
        response.setStatus(errorCode.getHttpStatus().value()); // set status code
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        ApiResponse<?> apiResponse =ApiResponse.builder().code(errorCode.getCode())
                .message(errorCode.getMessage())
                .build();

        ObjectMapper objectMapper = new ObjectMapper();

        response.getWriter().write(objectMapper.writeValueAsString(apiResponse));
        response.flushBuffer();
    }
}
