package com.foodlab.foodReservation.store.dto.request;

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

    @NotBlank(message = "상호명을 입력해주세요.")
    private String name;
    @NotBlank(message = "주소를 입력해주세요.")
    private String address;
    @NotNull(message = "경도값을 입력해주세요.")
    private Double longitude;
    @NotNull(message = "위도값을 입력해주세요.")
    private Double latitude;
    @NotBlank(message = "우편번호를 입력해주세요.")
    private String zipCode;

    @NotNull
    private Long sellerId;

}
