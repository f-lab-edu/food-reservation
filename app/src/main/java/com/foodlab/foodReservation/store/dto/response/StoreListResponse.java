package com.foodlab.foodReservation.store.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StoreListResponse {
    private Long storeId;
    private String storeName;
}
