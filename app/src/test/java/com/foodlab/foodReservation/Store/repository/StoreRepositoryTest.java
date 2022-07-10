package com.foodlab.foodReservation.Store.repository;

import com.foodlab.foodReservation.Common.Address;
import com.foodlab.foodReservation.Seller.entity.Seller;
import com.foodlab.foodReservation.Seller.repository.SellerRepository;
import com.foodlab.foodReservation.Store.entity.Store;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StoreRepositoryTest {

    @Autowired
    StoreRepository storeRepository;
    @Autowired
    SellerRepository sellerRepository;

    @Test
    void test() {
        // given
        Seller seller = Seller.createSeller("abc@gmail.com");
        sellerRepository.save(seller);
        Address address = Address.createAddress(10.0, 20.0, "123-456");
        Store store = Store.createStore("미친 맛집", address, seller);
        storeRepository.save(store);

        // when
        Optional<Store> optionalFoundStore = storeRepository.findById(store.getId());
        Store foundStore = optionalFoundStore.orElseThrow();

        // then
        assertEquals(store.getId(), foundStore.getId());
    }

}
