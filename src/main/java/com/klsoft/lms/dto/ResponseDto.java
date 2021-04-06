package com.klsoft.lms.dto;

import com.klsoft.lms.utils.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResponseDto {
    private String message;
    private String status = Status.SUCCESS.value();
    private int statusCode = HttpStatus.OK.value();
    private Object data = null;

    public ResponseDto(String message) {
        this.message = message;
    }

    public ResponseDto(String message, Object data) {
        this.message = message;
        this.data = data;
    }
}
