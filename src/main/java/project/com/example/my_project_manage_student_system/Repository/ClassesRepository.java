package project.com.example.my_project_manage_student_system.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.com.example.my_project_manage_student_system.Entity.Classes;

@Repository
public interface ClassesRepository extends JpaRepository<Classes, String> {
}
