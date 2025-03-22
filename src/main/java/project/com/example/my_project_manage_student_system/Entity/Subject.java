package project.com.example.my_project_manage_student_system.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "subject")
public class Subject {
    @Id
    @Column(name = "subject_id")
    String subjectId;

    @Column(name = "subject_name")
    String subjectName;

    @ManyToMany(mappedBy = "subjectSet")
    Set<Teacher> teacherSet;

    @OneToMany(mappedBy = "subject")
    Set<NoteBookDetail> noteBookDetailSet;

    @OneToMany(mappedBy = "subject")
    Set<ScoreBoardDetail> scoreBoardDetailSet;
}
