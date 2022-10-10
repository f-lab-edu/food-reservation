package com.foodlab.foodReservation.store.service;

import com.foodlab.foodReservation.common.Address;
import com.foodlab.foodReservation.item.repository.ItemRepository;
import com.foodlab.foodReservation.seller.entity.Seller;
import com.foodlab.foodReservation.seller.repository.SellerRepository;
import com.foodlab.foodReservation.store.dto.request.CreateStoreRequest;
import com.foodlab.foodReservation.store.dto.request.UpdateStoreRequest;
import com.foodlab.foodReservation.store.dto.response.*;
import com.foodlab.foodReservation.store.entity.Store;
import com.foodlab.foodReservation.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;
    private final SellerRepository sellerRepository;
    private final ItemRepository itemRepository;

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

    @Transactional
    public DeleteStoreResponse deleteStore(Long storeId) {
        Store store = storeRepository.findById(storeId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 음식점입니다."));
        store.delete();
        return new DeleteStoreResponse(storeId);
    }

    public StoreDetailResponse getStore(Long storeId) {
        Store store = storeRepository.findById(storeId).orElseThrow(() -> new IllegalArgumentException("존재하는 음식점이 아닙니다."));
        return new StoreDetailResponse(store.getId(), store.getName(), store.getAddress());
    }

    public Page<StoreListResponse> getStores(Pageable pageable) {
        return storeRepository.getStores(pageable);
    }

    public Page<StoreDetailWithItemsResponse> getStoreWithItems(Long storeId, Pageable pageable) {
        Store store = storeRepository.findById(storeId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 매장입니다."));
        return itemRepository.getItems(storeId, pageable);
    }

}
