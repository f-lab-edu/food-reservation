package com.foodlab.foodReservation.Common;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.Embeddable;

// 불변객체
@Getter
@Embeddable
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class Address {

    private Double longitude;

    private Double latitude;

    private String zipcode;

    public static Address createAddress(Double longitude, Double latitude, String zipcode) {
        Address address = new Address();
        address.longitude = longitude;
        address.latitude = latitude;
        address.zipcode = zipcode;
        return address;
    }

}
