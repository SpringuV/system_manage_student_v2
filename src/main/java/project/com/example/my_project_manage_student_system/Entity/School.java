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
@Table(name = "school")
public class School {
    @Id
    @Column(name = "sc_id")
    String schoolId;

    @Column(name = "sc_phone", unique = true)
    String phoneNumber;

    @Column(name = "sc_email")
    String email;

    @OneToMany(mappedBy = "school")
    Set<Student> studentSet;

    @OneToMany(mappedBy = "school")
    Set<Classes> classesSet;

    @OneToMany(mappedBy = "school")
    Set<Teacher> teacherSet;

    @OneToMany(mappedBy = "school")
    Set<Manager> managerSet;
}
