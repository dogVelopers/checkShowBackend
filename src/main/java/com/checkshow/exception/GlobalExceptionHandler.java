package com.checkshow.exception;

import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {InvalidDataAccessApiUsageException.class })
    protected ResponseEntity<ErrorResponse> handleInvalidDataAccessApiUsageException() {
        return ErrorResponse.toResponseEntity(ErrorCode.NOT_NULL_PARAM);
    }

    @ExceptionHandler(value = { MethodArgumentTypeMismatchException.class })
    protected ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException() {
        return ErrorResponse.toResponseEntity(ErrorCode.CANNOT_FORMAT_NUMBER);
    }

    @ExceptionHandler(value = { CustomException.class })
    protected ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {
        return ErrorResponse.toResponseEntity(e.getErrorCode());
    }
}
