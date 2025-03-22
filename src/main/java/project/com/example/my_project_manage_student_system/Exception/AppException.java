package project.com.example.my_project_manage_student_system.Exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Getter
@Setter
public class AppException extends RuntimeException {
    private final ErrorCode errorCode;
    public AppException(ErrorCode errorCode){
        super(errorCode.getMessage()); // ke thua tu lop runtimeexception voi message cua errorcode
        this.errorCode = errorCode;
    }
}
