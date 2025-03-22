package project.com.example.my_project_manage_student_system.Dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL) // nhung field null se khong duoc hien thi
public class ApiResponse <T>{
    @Builder.Default
    int code = 100; // mac dinh 100 la oke
    T result;
    String message;
}
