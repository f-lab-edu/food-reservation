package com.foodlab.foodReservation.store.dto.response;

import lombok.Data;

@Data
public class CreateStoreResponse {

    private Long storeId;

    public CreateStoreResponse(Long id) {
        this.storeId = id;
    }
}
