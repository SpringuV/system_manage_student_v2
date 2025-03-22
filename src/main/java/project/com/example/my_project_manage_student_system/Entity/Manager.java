package project.com.example.my_project_manage_student_system.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "manager")
public class Manager extends User{

    @Column(name = "phone")
    String phone;

    @ManyToOne
    @JoinColumn(name = "school_id")
    School school;

}
