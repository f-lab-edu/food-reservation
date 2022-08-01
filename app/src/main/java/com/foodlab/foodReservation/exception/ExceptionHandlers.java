package com.foodlab.foodReservation.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Slf4j
@RestControllerAdvice
public class ExceptionHandlers {

    /* Repository findById 메서드로 엔티티를 못 찾았을 때 발생하는 예외 처리 */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public Result<Object> illegalArgument(IllegalArgumentException e) {
        log.error("[exceptionHandler] ex", e);
        return Result.builder()
                .success(false)
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .errorMessage(e.getMessage())
                .data(null)
                .build();
    }

    /* Controller 메서드 @Valid 통과 못했을 시 발생하는 예외 처리 */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public Result<Object> notValidMethodArgument(MethodArgumentNotValidException e) {
        log.error("[exceptionHandler] ex", e);
        return Result.builder()
                .success(false)
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .errorMessage(e.getMessage())
                .data(null)
                .build();
    }

}


