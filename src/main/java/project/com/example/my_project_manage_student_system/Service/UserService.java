package project.com.example.my_project_manage_student_system.Service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import project.com.example.my_project_manage_student_system.Dto.Request.User.UserCreateRequest;
import project.com.example.my_project_manage_student_system.Dto.Request.User.UserUpdateRequest;
import project.com.example.my_project_manage_student_system.Dto.Response.User.UserCreateResponse;
import project.com.example.my_project_manage_student_system.Entity.User;
import project.com.example.my_project_manage_student_system.Exception.AppException;
import project.com.example.my_project_manage_student_system.Exception.ErrorCode;
import project.com.example.my_project_manage_student_system.Mapper.UserMapper;
import project.com.example.my_project_manage_student_system.Repository.UserRepository;

@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;

    public UserCreateResponse createUser(UserCreateRequest request){
        User user = userMapper.toUserFromCreate(request);
        // check user
        if(!userRepository.existsByUserId(user.getUserId())){
             throw  new AppException(ErrorCode.USER_EXISTED);
        }
        // save
        userRepository.save(user);
        return userMapper.toUserCreateResponse(user);
    }

    public UserCreateResponse getUser(String userId){
        User user = userRepository.findByUserId(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        return userMapper.toUserCreateResponse(user);
    }

    public void deleteUser(String userId){
        userRepository.deleteById(userId);
    }

    public UserCreateResponse updateUser(String userId, UserUpdateRequest request){
        User user = userRepository.findByUserId(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        userMapper.toUserFromUserUpdateRq(user, request);
        return  userMapper.toUserCreateResponse(user);
    }
}
