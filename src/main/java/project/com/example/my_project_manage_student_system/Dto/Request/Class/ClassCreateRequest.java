package project.com.example.my_project_manage_student_system.Dto.Request.Class;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClassCreateRequest {
    String nameClass;
    String maTruong;
    int khoiLop;
}
