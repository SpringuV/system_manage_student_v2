package project.com.example.my_project_manage_student_system.Dto.Request.NoteBook;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NoteBookCreateRequest {
    String schoolYear;
    int semester;
    String classId;
}
