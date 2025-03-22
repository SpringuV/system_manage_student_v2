package project.com.example.my_project_manage_student_system.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import project.com.example.my_project_manage_student_system.Dto.Request.User.UserCreateRequest;
import project.com.example.my_project_manage_student_system.Dto.Request.User.UserUpdateRequest;
import project.com.example.my_project_manage_student_system.Dto.Response.User.UserCreateResponse;
import project.com.example.my_project_manage_student_system.Entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    void toUserFromUserUpdateRq(@MappingTarget User user, UserUpdateRequest userUpdateRequest);

    User toUserFromCreate(UserCreateRequest request);
    UserCreateResponse toUserCreateResponse(User user);
}
