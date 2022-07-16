package com.foodlab.foodReservation.common;

import com.foodlab.foodReservation.store.dto.request.StoreUpdateDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Getter
@Embeddable
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private String address;
    private double longitude;
    private double latitude;
    private String zipCode;

    public Address(StoreUpdateDto storeUpdateDto) {
        this.address = storeUpdateDto.getAddress();
        this.longitude = storeUpdateDto.getLongitude();
        this.latitude = storeUpdateDto.getLatitude();
        this.zipCode = storeUpdateDto.getZipCode();
    }

}
