package project.com.example.my_project_manage_student_system.Dto.Response.User;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreateResponse {
    String userId;
    String fullName;
    String address;
    LocalDate dob;
    String email;
}
