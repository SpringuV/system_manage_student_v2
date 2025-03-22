package project.com.example.my_project_manage_student_system.Exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import project.com.example.my_project_manage_student_system.Dto.ApiResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse> handlingAppexception(AppException appException){
        return ResponseEntity.status(appException.getErrorCode().getHttpStatus())
                .body(ApiResponse.builder()
                        .code(appException.getErrorCode().getCode())
                        .message(appException.getMessage())
                        .build());
    }
}
