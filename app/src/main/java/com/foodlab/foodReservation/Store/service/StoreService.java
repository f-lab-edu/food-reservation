package com.foodlab.foodReservation.Store.service;

import com.foodlab.foodReservation.Common.Address;
import com.foodlab.foodReservation.Seller.entity.Seller;
import com.foodlab.foodReservation.Seller.repository.SellerRepository;
import com.foodlab.foodReservation.Store.entity.Store;
import com.foodlab.foodReservation.Store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;
    private final SellerRepository sellerRepository;

    @Transactional
    public Long create(String storeName, Address storeAddress, Long sellerId) {
        Seller seller = sellerRepository.findById(sellerId).orElseThrow();
        Store store = Store.createStore(storeName, storeAddress, seller);
        Store savedStore = storeRepository.save(store);
        return savedStore.getId();
    }

    @Transactional
    public void delete(Long storeId) {
        Store foundStore = storeRepository.findById(storeId).orElseThrow();
        foundStore.delete();
    }
}
