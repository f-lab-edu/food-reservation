package com.foodlab.foodReservation.Store.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CreateStoreRequest {

    @NotEmpty
    private Long sellerId;

    @NotEmpty
    private String storeName;

    @NotEmpty
    private Double longitude;

    @NotEmpty
    private Double latitude;

    @NotEmpty
    private String zipcode;
}
