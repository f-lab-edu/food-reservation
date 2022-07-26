package com.foodlab.foodReservation.item.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Data
public class UpdateItemRequest {

    @NotBlank
    private String name;

    @NotNull
    @PositiveOrZero
    private Integer price;
}
