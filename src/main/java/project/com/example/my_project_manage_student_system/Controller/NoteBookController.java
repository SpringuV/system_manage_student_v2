package project.com.example.my_project_manage_student_system.Controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import project.com.example.my_project_manage_student_system.Dto.ApiResponse;
import project.com.example.my_project_manage_student_system.Dto.Request.NoteBook.NoteBookCreateRequest;
import project.com.example.my_project_manage_student_system.Dto.Request.NoteBook.NotebookUpdateRequest;
import project.com.example.my_project_manage_student_system.Dto.Response.NoteBook.NoteBookCreateResponse;
import project.com.example.my_project_manage_student_system.Dto.Response.NoteBook.NotebookUpdateResponse;
import project.com.example.my_project_manage_student_system.Service.NoteBookService;

@RestController
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/notebook")
public class NoteBookController {
    NoteBookService noteBookService;

    @PostMapping
    ApiResponse<NoteBookCreateResponse> createNote(@RequestBody NoteBookCreateRequest request){
        return ApiResponse.<NoteBookCreateResponse>builder()
                .result(noteBookService.createNoteBook(request))
                .build();
    }

    @DeleteMapping("/{stringId}")
    ApiResponse<String> deleteNote(@PathVariable("stringId") String id){
        return ApiResponse.<String>builder()
                .result(noteBookService.deleteNotebook(id))
                .build();
    }

    @PutMapping
    ApiResponse<NotebookUpdateResponse> modifyNote(@RequestBody NotebookUpdateRequest request){
        return ApiResponse.<NotebookUpdateResponse>builder()
                .result(noteBookService.modifyNotebook(request))
                .build();
    }

}
