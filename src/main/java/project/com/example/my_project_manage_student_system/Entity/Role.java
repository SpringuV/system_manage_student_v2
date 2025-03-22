package project.com.example.my_project_manage_student_system.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "role")
public class Role {

    @Id
    @Column(name = "role_name")
    String roleName;

    @Column(name = "description")
    String description;

    @ManyToMany
            @JoinTable(
                    name = "role_permission", joinColumns = @JoinColumn(name = "role_name"),
                    inverseJoinColumns = @JoinColumn(name = "permission_name")
            )
    Set<Permission> permissionSet;

    @OneToMany(mappedBy = "role")
    Set<User> userSet;
}
