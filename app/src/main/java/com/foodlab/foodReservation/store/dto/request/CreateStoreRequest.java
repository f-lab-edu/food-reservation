package com.foodlab.foodReservation.store.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CreateStoreRequest {

    // TODO: 일시적 필드. 추후 인증 기능에서 sellerId를 가져오게 된다면 이 필드는 삭제 예정.
    @NotNull
    private long sellerId;

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

}
