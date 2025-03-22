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
@Table(name = "report")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int reportId;

    @ManyToOne
            @JoinColumn(name = "student_id")
    Student student;

    @ManyToOne
            @JoinColumn(name = "teacher_id")
    Teacher teacher;

    @OneToMany(mappedBy = "report")
    Set<ScoreBoardDetail> scoreBoardDetailSet;

    @Column(name = "school_year")
    String schoolYear;

    @Column(name =  "comment")
    String comment;

    @Column(name = "conduct") // hạnh kiểm
    String conduct;
}
