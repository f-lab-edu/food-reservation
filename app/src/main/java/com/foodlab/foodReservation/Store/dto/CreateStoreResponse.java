package com.foodlab.foodReservation.Store.dto;

import lombok.Data;

@Data
public class CreateStoreResponse {
    private Long id;

    public CreateStoreResponse(Long id) {
        this.id = id;
    }
}
