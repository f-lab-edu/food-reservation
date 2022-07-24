package com.foodlab.foodReservation.store.dto.response;

import lombok.Data;

@Data
public class UpdateStoreResponse {

    private Long storeId;

    public UpdateStoreResponse(Long storeId) {
        this.storeId = storeId;
    }

}
