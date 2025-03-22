package project.com.example.my_project_manage_student_system.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import project.com.example.my_project_manage_student_system.Dto.Request.NoteBook.NoteBookCreateRequest;
import project.com.example.my_project_manage_student_system.Dto.Request.NoteBook.NotebookUpdateRequest;
import project.com.example.my_project_manage_student_system.Dto.Response.NoteBook.NoteBookCreateResponse;
import project.com.example.my_project_manage_student_system.Dto.Response.NoteBook.NotebookResponse;
import project.com.example.my_project_manage_student_system.Dto.Response.NoteBook.NotebookUpdateResponse;
import project.com.example.my_project_manage_student_system.Entity.Classes;
import project.com.example.my_project_manage_student_system.Entity.NoteBook;

@Mapper(componentModel = "spring")
public interface NotebookMapper {
    @Mapping(source = "classId", target = "classes", qualifiedByName = "mapClassIdToEntity")
    NoteBook toNotebook(NoteBookCreateRequest noteBookCreateRequest);

    @Mapping(source = "classes.className", target = "classId")
    NoteBookCreateResponse toNotebookCreateResponse(NoteBook noteBook);

    @Mapping(source = "classId", target = "classes", qualifiedByName = "mapClassIdToEntity")
    void toNotebookFromUpdateRequest(@MappingTarget NoteBook noteBook, NotebookUpdateRequest request);

    @Mapping(source = "classes.className", target = "classId")
    NotebookUpdateResponse toUpdateResponse(NoteBook noteBook);

    @Mapping(source = "classes.className", target = "classId")
    NotebookResponse toNoteResponse(NoteBook noteBook);

    @Named("mapClassIdToEntity")
    default Classes mapClassIdToEntity(String classId){
        if(classId == null){
            return null;
        }
        Classes classes = new Classes();
        classes.setClassName(classId);
        return classes; // Không truy vấn DB, chỉ tạo đối tượng
    }
}
