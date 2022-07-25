package com.foodlab.foodReservation.store.dto.response;

import com.foodlab.foodReservation.common.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreDetailResponse {

    private Long id;
    private String name;
    private Address address;
    
}
