package com.foodlab.foodReservation.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.validation.FieldError;


/*
컨트롤러 메서드 파라미터 DTO validation 실패 내역을 저장한다.
"필드 명, 에러 메시지, 잘못 입력된 값"으로 이뤄져 있으며,
여러 필드에서 실패했을 시, 아래 클래스의 리스트를 반환해야 한다.
 */
@Getter
@Builder
@AllArgsConstructor
public class ErrorLine {

    private final String fieldName;
    private final String errorMessage;
    private final String rejectedValue;

    public static ErrorLine of(FieldError fieldError) {
        return ErrorLine.builder()
                .fieldName(fieldError.getField())
                .errorMessage(fieldError.getDefaultMessage())
                .rejectedValue(String.valueOf(fieldError.getRejectedValue()))
                .build();
    }
}
