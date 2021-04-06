package com.klsoft.lms.exception;

import com.klsoft.lms.dto.ResponseDto;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler({RequestException.class, MethodArgumentNotValidException.class, ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseDto methodArgumentNotValidExceptionHandler(Exception ex) {
        String errorMsg;
        if (ex instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException mex = (MethodArgumentNotValidException) ex;
            List<FieldError> errors = mex.getBindingResult().getFieldErrors();
            errorMsg = errors.get(0).getDefaultMessage();
        } else {
            errorMsg = ex.getLocalizedMessage();
        }

        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage(errorMsg);
        responseDto.setStatus(HttpStatus.BAD_REQUEST.getReasonPhrase());
        responseDto.setStatusCode(HttpStatus.BAD_REQUEST.value());
        return responseDto;
    }

    /* @ExceptionHandler(DataAccessException.class)
     @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
     @ResponseBody*/
    public ResponseDto dataAccessExceptionHandler(DataAccessException ex) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage(ex.getLocalizedMessage());
        responseDto.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        responseDto.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return responseDto;
    }
}
