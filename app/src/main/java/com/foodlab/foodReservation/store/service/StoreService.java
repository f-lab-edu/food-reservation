package com.foodlab.foodReservation.store.service;

import com.foodlab.foodReservation.common.Address;
import com.foodlab.foodReservation.seller.entity.Seller;
import com.foodlab.foodReservation.seller.repository.SellerRepository;
import com.foodlab.foodReservation.store.dto.request.CreateStoreRequest;
import com.foodlab.foodReservation.store.dto.request.StoreUpdateDto;
import com.foodlab.foodReservation.store.dto.response.CreateStoreResponse;
import com.foodlab.foodReservation.store.entity.Store;
import com.foodlab.foodReservation.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;
    private final SellerRepository sellerRepository;

    @Transactional
    public void updateStore(Long storeId, StoreUpdateDto storeUpdateDto) {
        Store savedStore = storeRepository.findById(storeId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 음식점입니다."));
        Address newAddress = new Address(storeUpdateDto);
        savedStore.update(storeUpdateDto, newAddress);
    }

    @Transactional
    public CreateStoreResponse createStore(CreateStoreRequest createStoreDto) {
        Seller seller = sellerRepository.findById(createStoreDto.getSellerId()).orElseThrow(
                () -> new IllegalArgumentException("잘못된 요청입니다."));
        Address address = new Address(createStoreDto.getAddress(), createStoreDto.getLongitude(), createStoreDto.getLatitude(), createStoreDto.getZipCode());
        Store store = new Store(createStoreDto.getName(), address, seller);
        Store savedStore = storeRepository.save(store);
        return new CreateStoreResponse(savedStore.getId());
    }

    @Transactional
    public void deleteStore(Long storeId) {
        Store store = storeRepository.findById(storeId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 음식점입니다."));
        store.delete();
    }
}

