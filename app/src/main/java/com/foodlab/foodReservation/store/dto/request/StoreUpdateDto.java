package com.foodlab.foodReservation.store.dto.request;

import com.foodlab.foodReservation.seller.entity.Seller;
import lombok.Data;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class StoreUpdateDto {

    @NotBlank
    private String name;

    @NotBlank
    private String address;

    @NotNull
    private double longitude;

    @NotNull
    private double latitude;

    @NotBlank
    private String zipCode;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Seller seller;

}
