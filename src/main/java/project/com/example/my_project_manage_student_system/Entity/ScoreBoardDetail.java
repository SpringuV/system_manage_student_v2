package project.com.example.my_project_manage_student_system.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "scoreboard_detail")
public class ScoreBoardDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int scoreBoardDetailId;

    @ManyToOne
    @JoinColumn(name = "report_id")
    Report report;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    Subject subject;

    @Column(name = "score")
    float score;

    @Column(name = "semester")
    int semester;

    @Column(name = "school_year")
    String schoolYear;
}
