package com.foodlab.foodReservation.store.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StoreDetailWithItemsResponse {

    private Long storeId;
    private String storeName;
    private String address;
    private Long ItemId;
    private String itemName;
    private int price;

}
