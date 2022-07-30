package com.foodlab.foodReservation.store.dto.response;

import lombok.Getter;

@Getter
public class DeleteStoreResponse {

    private final Long storeId;

    public DeleteStoreResponse(Long storeId) {
        this.storeId = storeId;
    }
}
