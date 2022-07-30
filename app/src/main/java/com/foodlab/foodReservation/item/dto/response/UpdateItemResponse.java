package com.foodlab.foodReservation.item.dto.response;

import com.foodlab.foodReservation.item.entity.Item;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UpdateItemResponse {

    private final String name;
    private final Integer price;

    public static UpdateItemResponse of(Item item) {
        return UpdateItemResponse.builder()
                .name(item.getName())
                .price(item.getPrice())
                .build();
    }
}
