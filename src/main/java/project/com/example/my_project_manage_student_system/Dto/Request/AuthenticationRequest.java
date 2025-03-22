package project.com.example.my_project_manage_student_system.Dto.Request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationRequest {
    String userId;
    String password;
}
