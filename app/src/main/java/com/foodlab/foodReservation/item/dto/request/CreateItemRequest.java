package com.foodlab.foodReservation.item.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateItemRequest {

    @NotBlank(message = "매뉴 이름을 입력해주세요.")
    private String name;
    @NotNull(message = "가격을 입력해주세요.")
    private int price;
    private Long storeId;

}
