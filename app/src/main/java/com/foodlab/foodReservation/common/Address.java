package com.foodlab.foodReservation.common;

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

}
