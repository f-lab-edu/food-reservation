package com.foodlab.foodReservation.item.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DeleteItemResponse {

    private final Long deletedItemId;
    private final boolean isDeleted;

}
