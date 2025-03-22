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
@Table(name = "student")
public class Student extends User {
    @ManyToOne
    @JoinColumn(name = "school_id")
    School school;

    @ManyToOne
    @JoinColumn(name = "class_name")
    Classes classes;

    @OneToMany(mappedBy = "student")
    Set<Report> reportSet;
}
