package com.foodlab.foodReservation.store.dto.request;

import com.foodlab.foodReservation.seller.entity.Seller;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateStoreRequest {

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
    private Long sellerId;

}
