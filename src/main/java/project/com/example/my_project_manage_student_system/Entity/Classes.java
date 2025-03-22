package project.com.example.my_project_manage_student_system.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "class")
public class Classes {

    @Id
    @Column(name = "class_name", unique = true)
    String className;

    @Column(name = "class_grade")
    int classGrade;

    @ManyToOne
    @JoinColumn(name = "school_id")
    School school;

    @OneToMany(mappedBy = "classes")
    Set<Student> studentSet;

    @OneToMany(mappedBy = "classes")
    Set<NoteBook> noteBookSet;

    public Classes(String classId) {
        this.className = classId;
    }
}
