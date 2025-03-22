package project.com.example.my_project_manage_student_system.Exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(101, "Uncategorized exception !", HttpStatus.INTERNAL_SERVER_ERROR),
    USER_NOT_FOUND(102, "User not found !", HttpStatus.NOT_FOUND),
    NOTEBOOK_NOT_FOUND(102, "Notebook not found !", HttpStatus.BAD_REQUEST),
    UN_AUTHENTICATED(103, "Un Authenticated", HttpStatus.UNAUTHORIZED),
    TOKEN_INVALID(104, "Token Invalid or Expired", HttpStatus.UNAUTHORIZED),
    USER_EXISTED(105, "User Existed !", HttpStatus.BAD_REQUEST),
    NOTEBOOK_EXISTED(105, "NoteBook Existed !", HttpStatus.BAD_REQUEST);

    private int code;
    private String message;
    private HttpStatus httpStatus;

    ErrorCode(int code, String message, HttpStatus httpStatus){
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
