package com.foodlab.foodReservation.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Slf4j
@RestControllerAdvice
public class GlobalRestControllerAdvice {

    /*
    아래의 다른 핸들러에 의해 catch 되지 않은 범용적인 예외의 처리.
    잘못된 클라이언트 요청에 의한 예외를 제외한 나머지 예외를 처리.
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public Response<Map<String, String>> exceptionHandler(Exception e) {
        log.error("[allExceptionHandler]", e);

        Map<String, String> map = new HashMap<>();
        map.put("message", null);

        return Response.<Map<String, String>>builder()
                .status(Response.Status.ERROR)
                .data(map)
                .build();
    }

    /* 컨트롤러 메서드 파리미터 @Valid 통과 못했을 시 발생하는 예외 처리 (DTO validation 실패 예외 처리) */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public Response<List<ErrorLine>> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        log.error("[methodArgumentNotValidExceptionHandler]:", e);

        List<ErrorLine> errorLines = e.getAllErrors().stream()
                .map(error -> ErrorLine.of((FieldError) error))
                .collect(Collectors.toList());

        return Response.<List<ErrorLine>>builder()
                .status(Response.Status.FAIL)
                .data(errorLines)
                .build();
    }

    // TODO: 세세한 예외에 대한 핸들러 점차적으로 추가

}


