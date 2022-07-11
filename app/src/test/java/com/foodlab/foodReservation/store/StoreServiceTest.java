package com.foodlab.foodReservation.store;

import com.foodlab.foodReservation.common.Address;
import com.foodlab.foodReservation.seller.entity.Seller;
import com.foodlab.foodReservation.seller.repository.SellerRepository;
import com.foodlab.foodReservation.store.dto.StoreUpdateDto;
import com.foodlab.foodReservation.store.entity.Store;
import com.foodlab.foodReservation.store.repository.StoreRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StoreServiceTest {

    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private SellerRepository sellerRepository;

    @Test
    @DisplayName("음식점 정보 수정")
    void updateStoreTest() {

        // given
        Address address = Address.builder()
                .address("testAddress1")
                .longitude(131)
                .latitude(37)
                .zipCode("07007")
                .build();

        Seller seller = Seller.builder()
                .username("testSeller1")
                .password("testPassword1")
                .build();
        Seller seller1 = sellerRepository.save(seller);

        Store store = Store.builder()
                .name("testStore1")
                .address(address)
                .seller(seller1)
                .build();

        storeRepository.save(store);

        // when
        Store savedStore = storeRepository.findById(1L).orElseThrow(
                () -> new IllegalArgumentException("store_not_found"));

        StoreUpdateDto storeUpdateDto = new StoreUpdateDto();
        storeUpdateDto.setName("testStore2");
        storeUpdateDto.setAddress("testAddress2");

        Address address2 = Address.builder()
                .address("testAddress2")
                .longitude(132)
                .latitude(38)
                .zipCode("07008")
                .build();

        savedStore.update(storeUpdateDto, address2);

        // then
        assertEquals(savedStore.getName(), "testStore2");

    }

}


