package com.example.blogspring.exception;


import com.example.blogspring.dto.ErrorResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import static com.example.blogspring.exception.ErrorCode.*;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    protected ResponseEntity<ErrorResponseDto> handleBadCredentialException(BadCredentialsException e) {
        final ErrorResponseDto response = ErrorResponseDto.of(BAD_CREDENTIALS);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    protected ResponseEntity<ErrorResponseDto> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        final ErrorResponseDto response = ErrorResponseDto.of(e);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    protected ResponseEntity<ErrorResponseDto> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        final ErrorResponseDto response = ErrorResponseDto.of(METHOD_NOT_ALLOWED);
        return new ResponseEntity<>(response, HttpStatus.METHOD_NOT_ALLOWED);
    }

    // @Valid, @Validated 에서 binding error 발생 시 (@RequestBody)
    @ExceptionHandler
    protected ResponseEntity<ErrorResponseDto> handleBindException(BindException e) {
        final ErrorResponseDto response = ErrorResponseDto.of(INVALID_INPUT_VALUE, e.getBindingResult());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // 비즈니스 요구사항에 따른 Exception
    @ExceptionHandler
    protected ResponseEntity<ErrorResponseDto> handleBusinessException(BusinessException e) {
        final ErrorCode errorCode = e.getErrorCode();
        final ErrorResponseDto response = ErrorResponseDto.of(errorCode, e.getErrors());
        return new ResponseEntity<>(response, HttpStatus.valueOf(errorCode.getStatus()));
    }

    // 그 밖에 발생하는 모든 예외처리가 이곳으로 모인다.
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponseDto> handleException(Exception e) {
        log.error("Exception: ", e);
        final ErrorResponseDto response = ErrorResponseDto.of(INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
