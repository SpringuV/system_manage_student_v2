package project.com.example.my_project_manage_student_system.Dto.Response.NoteBook;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NotebookResponse {
    String notebookId;
    String schoolYear;
    int semester;
    String classId;
    LocalDate createAt;
    LocalDate updateAt;
}
