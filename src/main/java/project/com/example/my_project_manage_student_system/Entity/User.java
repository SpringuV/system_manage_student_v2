package project.com.example.my_project_manage_student_system.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;


@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "user")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

    @Id
    @Column(name = "user_id")
    String userId;

    @Column(name = "full_name")
    String fullName;

    @Column(name = "address")
    String address;

    @Column(name = "dob")
    LocalDate dob;

    @Column(name = "create_at")
    LocalDate createAt;

    @Column(name = "email", unique = true)
    String email;

    @Column(name = "password")
    String password;

    @ManyToOne
    @JoinColumn(name = "role_name")
    Role role;
}
