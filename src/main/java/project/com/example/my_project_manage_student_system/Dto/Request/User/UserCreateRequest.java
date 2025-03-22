package project.com.example.my_project_manage_student_system.Dto.Request.User;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreateRequest {
    String userId;
    String password;
    String fullName;
    String address;
    LocalDate dob;
    String email;

}
