package project.com.example.my_project_manage_student_system.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "notebook_detail")
public class NoteBookDetail {
    @Id
    String NoteDetail_Id;

    @ManyToOne
    @JoinColumn(name = "notebook_id")
    NoteBook notebook;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    Teacher teacher;

    @Column(name = "comment")
    String comment;

    @Column(name = "sum_attendant")
    int numberOfAttendant;

    @Column(name = "study_date")
    LocalDate studyDate;

    @Column(name = "estimate")
    String estimate;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    Subject subject;
}
