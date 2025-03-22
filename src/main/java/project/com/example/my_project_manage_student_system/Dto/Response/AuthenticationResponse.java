package project.com.example.my_project_manage_student_system.Dto.Response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationResponse {
    boolean authenticated;
    String token;
}
