package com.foodlab.foodReservation.item.repository;


import com.foodlab.foodReservation.store.dto.response.StoreDetailWithItemsResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemRepositoryCustom {
    Page<StoreDetailWithItemsResponse> getItems(Long storeId, Pageable pageable);
}
