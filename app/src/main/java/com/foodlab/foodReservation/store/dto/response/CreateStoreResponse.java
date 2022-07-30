package com.foodlab.foodReservation.store.dto.response;

import lombok.Getter;

@Getter
public class CreateStoreResponse {

    private final Long storeId;

    public CreateStoreResponse(Long id) {
        this.storeId = id;
    }
}
