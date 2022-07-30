package com.foodlab.foodReservation.item.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Builder
@AllArgsConstructor
public class UpdateItemRequest {

    @NotBlank(message = "이름을 입력해 주세요")
    private final String name;

    @NotNull(message = "가격을 입력해 주세요")
    @PositiveOrZero(message = "가격은 0 이하일 수 없습니다.")
    private final Integer price;
}
