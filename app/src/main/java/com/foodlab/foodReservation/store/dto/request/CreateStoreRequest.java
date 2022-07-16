package com.foodlab.foodReservation.store.dto.request;

import lombok.Data;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CreateStoreRequest {

    public static final String nameBlankErrorMsg = "상점 이름은 꼭 입력해야 합니다.";
    public static final String addressBlankErrorMsg = "상점 주소는 꼭 입력해야 합니다.";

    public static final String longitudeRangeErrorMsg = "경도의 값은 -180도 이상, 180도 이하여야 합니다.";
    public static final String longitudeNullErrorMsg = "경도의 값은 널이 될 수 없습니다.";

    public static final String latitudeRangeErrorMsg = "위도의 값은 -90도 이상, 90도 이하여야 합니다.";
    public static final String latitudeNullErrorMsg = "위도의 값은 널이 될 수 없습니다.";
    public static final String zipCodeBlankErrorMsg = "상점 우편번호는 꼭 입력해야 합니다.";


    // TODO: 일시적 필드. 추후 인증 기능에서 sellerId를 가져오게 된다면 이 필드는 삭제 예정.
    private long sellerId;

    @NotBlank(message = nameBlankErrorMsg)
    private String name;

    @NotBlank(message = addressBlankErrorMsg)
    private String address;

    @DecimalMax(value = "180.0", message = longitudeRangeErrorMsg)
    @DecimalMin(value = "-180.0", message = longitudeRangeErrorMsg)
    @NotNull(message = longitudeNullErrorMsg)
    private Double longitude;

    @DecimalMax(value = "90.0", message = latitudeRangeErrorMsg)
    @DecimalMin(value = "-90.0", message = latitudeRangeErrorMsg)
    @NotNull(message = latitudeNullErrorMsg)
    private Double latitude;

    @NotBlank(message = zipCodeBlankErrorMsg)
    private String zipCode;

}


