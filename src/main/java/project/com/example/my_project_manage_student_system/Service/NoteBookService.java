package project.com.example.my_project_manage_student_system.Service;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import project.com.example.my_project_manage_student_system.Dto.Request.NoteBook.NoteBookCreateRequest;
import project.com.example.my_project_manage_student_system.Dto.Request.NoteBook.NotebookUpdateRequest;
import project.com.example.my_project_manage_student_system.Dto.Response.NoteBook.NoteBookCreateResponse;
import project.com.example.my_project_manage_student_system.Dto.Response.NoteBook.NotebookResponse;
import project.com.example.my_project_manage_student_system.Dto.Response.NoteBook.NotebookUpdateResponse;
import project.com.example.my_project_manage_student_system.Entity.NoteBook;
import project.com.example.my_project_manage_student_system.Exception.AppException;
import project.com.example.my_project_manage_student_system.Exception.ErrorCode;
import project.com.example.my_project_manage_student_system.Mapper.NotebookMapper;
import project.com.example.my_project_manage_student_system.Repository.NoteBookRepository;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NoteBookService {
    NoteBookRepository noteBookRepository;
    NotebookMapper notebookMapper;

    public NoteBookCreateResponse createNoteBook(NoteBookCreateRequest request){
        NoteBook noteBook = notebookMapper.toNotebook(request);
        // check notebook exist
        if(noteBookRepository.checkNotebookExist(noteBook.getNotebookId(), noteBook.getSemester(), noteBook.getSchoolYear())){
            throw new AppException(ErrorCode.NOTEBOOK_EXISTED);
        }
        // create
        noteBook.setCreateAt(LocalDate.now());
        try {
            noteBookRepository.save(noteBook);
        } catch (DataIntegrityViolationException e){
            throw new AppException(ErrorCode.NOTEBOOK_EXISTED);
        }
        return notebookMapper.toNotebookCreateResponse(noteBook);
    }

    public NotebookUpdateResponse modifyNotebook(NotebookUpdateRequest request){
        NoteBook noteBook = noteBookRepository.findById(request.getNotebookId()).orElseThrow(()-> new AppException(ErrorCode.NOTEBOOK_NOT_FOUND));
        notebookMapper.toNotebookFromUpdateRequest(noteBook, request);
        try{
            noteBookRepository.save(noteBook);
        } catch (DataIntegrityViolationException e){
            throw new AppException(ErrorCode.NOTEBOOK_EXISTED);
        }
        return notebookMapper.toUpdateResponse(noteBook);
    }

    @Transactional
    public String deleteNotebook(String id){
        int deleteCount = noteBookRepository.deleteByIdCustom(id);
        if(deleteCount == 0){
            return "Notebook not found !";
        }
        return "Notebook deleted successfully !";
    }

    public NotebookResponse getNoteBook(NoteBookCreateRequest request){
        NoteBook noteBook = noteBookRepository.getNotebook(request.getClassId(), request.getSemester(), request.getSchoolYear()).orElseThrow(()->new AppException(ErrorCode.NOTEBOOK_NOT_FOUND));
        return notebookMapper.toNoteResponse(noteBook);
    }

    public Set<NotebookResponse> getListNote(){
        return noteBookRepository.findAll().stream().map(notebookMapper::toNoteResponse).collect(Collectors.toSet());
    }
}
