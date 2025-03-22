package project.com.example.my_project_manage_student_system.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "permission")
public class Permission {

    @Id
    @Column(name = "permission_name")
    String permissionName;

    @Column(name = "description")
    String description;

    @ManyToMany(mappedBy = "permissionSet")
    Set<Role> roleSet;
}
