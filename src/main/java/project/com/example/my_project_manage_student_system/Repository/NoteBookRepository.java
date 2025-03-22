package project.com.example.my_project_manage_student_system.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project.com.example.my_project_manage_student_system.Entity.NoteBook;

import java.util.Optional;

@Repository
public interface NoteBookRepository extends JpaRepository<NoteBook, String> {
    @Query("SELECT COUNT(nb)>0 FROM NoteBook nb WHERE nb.classes.className =:idClass AND nb.semester =:semester AND nb.schoolYear =:schoolYear")
    boolean checkNotebookExist(@Param("idClass") String idClass, @Param("semester") int semester,@Param("schoolYear") String schoolYear);

    @Query("SELECT nb FROM NoteBook nb WHERE nb.classes.className =:idClass AND nb.semester =:semester AND nb.schoolYear =:schoolYear")
    Optional<NoteBook> getNotebook(@Param("idClass") String idClass, @Param("semester") int semester, @Param("schoolYear") String schoolYear);

    @Modifying
    @Query("DELETE FROM NoteBook nb WHERE nb.notebookId =:id")
    int deleteByIdCustom(@Param("id") String id);
}
