package com.foodlab.foodReservation.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

    private Double longitude;

    private Double latitude;

    private String zipcode;

    public Address(Double longitude, Double latitude, String zipcode) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.zipcode = zipcode;
    }

    protected Address() {
    }

}
