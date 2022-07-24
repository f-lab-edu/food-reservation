package com.foodlab.foodReservation.store.service;

import com.foodlab.foodReservation.common.Address;
import com.foodlab.foodReservation.seller.entity.Seller;
import com.foodlab.foodReservation.seller.repository.SellerRepository;
import com.foodlab.foodReservation.store.dto.request.CreateStoreRequest;
import com.foodlab.foodReservation.store.dto.request.UpdateStoreRequest;
import com.foodlab.foodReservation.store.dto.response.CreateStoreResponse;
import com.foodlab.foodReservation.store.dto.response.StoreDetailDto;
import com.foodlab.foodReservation.store.dto.response.UpdateStoreResponse;
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
    public UpdateStoreResponse updateStore(Long storeId, UpdateStoreRequest updateStoreRequest) {
        Store savedStore = storeRepository.findById(storeId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 음식점입니다."));

        Seller seller = sellerRepository.findById(updateStoreRequest.getSellerId()).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 판매자입니다."));

        Address newAddress = Address.builder()
                .address(updateStoreRequest.getAddress())
                .latitude(updateStoreRequest.getLatitude())
                .longitude(updateStoreRequest.getLongitude())
                .zipCode(updateStoreRequest.getZipCode())
                .build();

        savedStore.update(updateStoreRequest.getName(), seller, newAddress);
        return new UpdateStoreResponse(storeId);
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

    public void deleteStore(Long storeId) {
        Store store = storeRepository.findById(storeId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 음식점입니다."));
        store.delete();
    }

    public StoreDetailDto getStore(Long storeId) {
        Store store = storeRepository.findById(storeId).orElseThrow(() -> new IllegalArgumentException("존재하는 음식점이 아닙니다."));
        return new StoreDetailDto(store.getId(), store.getName(), store.getAddress());
    }

}

