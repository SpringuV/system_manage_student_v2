package project.com.example.my_project_manage_student_system.Controller;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import project.com.example.my_project_manage_student_system.Dto.ApiResponse;
import project.com.example.my_project_manage_student_system.Dto.Request.User.UserCreateRequest;
import project.com.example.my_project_manage_student_system.Dto.Request.User.UserUpdateRequest;
import project.com.example.my_project_manage_student_system.Dto.Response.User.UserCreateResponse;
import project.com.example.my_project_manage_student_system.Service.UserService;

@RestController
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/user")
public class UserController {
    UserService userService;

    @PostMapping
    ApiResponse<UserCreateResponse> createUser(@RequestBody UserCreateRequest request){
        return ApiResponse.<UserCreateResponse>builder()
                .result(userService.createUser(request))
                .build();
    }

    @GetMapping("/{userId}")
    ApiResponse<UserCreateResponse> getUser(@PathVariable("userId") String userId){
        return ApiResponse.<UserCreateResponse>builder()
                .result(userService.getUser(userId))
                .build();
    }

    @PostMapping("/userId")
    ApiResponse<UserCreateResponse> updateUser(@PathVariable("userId") String userId, @RequestBody UserUpdateRequest request){
        return ApiResponse.<UserCreateResponse>builder()
                .result(userService.updateUser(userId, request))
                .build();
    }
}
