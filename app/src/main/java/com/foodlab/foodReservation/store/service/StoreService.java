package com.foodlab.foodReservation.store.service;

import com.foodlab.foodReservation.common.Address;
import com.foodlab.foodReservation.store.dto.StoreUpdateDto;
import com.foodlab.foodReservation.store.entity.Store;
import com.foodlab.foodReservation.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;

    @Transactional
    public void updateStore(Long storeId, StoreUpdateDto storeUpdateDto) {
        Store savedStore = storeRepository.findById(storeId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 음식점입니다."));
        Address newAddress = new Address(storeUpdateDto);
        savedStore.update(storeUpdateDto, newAddress);
    }

}

