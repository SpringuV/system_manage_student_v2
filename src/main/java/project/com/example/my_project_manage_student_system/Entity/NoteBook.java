package project.com.example.my_project_manage_student_system.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.Set;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "notebook")
public class NoteBook {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "notebook_id", updatable = false, nullable = false)
    String notebookId;

    @ManyToOne
    @JoinColumn(name = "class_name", nullable = false)
    Classes classes;

    @Column(name = "semester", nullable = false)
    int semester;

    @Column(name = "school_year" , nullable = false, length = 9) // Example: "2024-2025"
    String schoolYear;

    @CreationTimestamp
    @Column(name = "create_at",updatable = false)
    LocalDate createAt;

    @UpdateTimestamp
    @Column(name = "update_at")
    LocalDate updateAt;

    @OneToMany(mappedBy = "notebook", cascade = CascadeType.ALL, orphanRemoval = true)
            @OrderBy("NoteDetail_Id ASC")
    Set<NoteBookDetail> noteBookDetailSet;
}
