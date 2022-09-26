package com.foodlab.foodReservation.store.repository;

import com.foodlab.foodReservation.store.dto.response.StoreListResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StoreRepositoryCustom {
    Page<StoreListResponse> getStores(Pageable pageable);
}

