package project.com.example.my_project_manage_student_system.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "teacher")
public class Teacher extends User {
    @Column(name = "degree_name")
    String degreeName;

    @Column(name = "degree_type")
    String degreeType;

    @Column(name = "issue_date")
    int issueDate;

    @Column(name = "phone")
    String phone;

    @ManyToOne
    @JoinColumn(name = "school_id")
    School school;

    @OneToMany(mappedBy = "teacher")
    Set<Report> reportSet;

    @ManyToMany
    @JoinTable(
            name = "teacher_subject",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    Set<Subject> subjectSet;

    @OneToMany (mappedBy = "teacher")
    Set<NoteBookDetail> noteBookDetailSet;
}
