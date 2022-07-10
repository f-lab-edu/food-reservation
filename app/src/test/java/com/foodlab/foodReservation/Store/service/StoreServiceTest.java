package com.foodlab.foodReservation.Store.service;

import com.foodlab.foodReservation.Common.Address;
import com.foodlab.foodReservation.Item.entity.Item;
import com.foodlab.foodReservation.Seller.entity.Seller;
import com.foodlab.foodReservation.Store.entity.Store;
import com.foodlab.foodReservation.Store.excpetion.NotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StoreServiceTest {

    @Autowired
    StoreService storeService;
    @Autowired
    EntityManager em;

    @Test
    @Transactional
    @DisplayName("충분한 정보가 주어졌을 때 Store 엔티티가 DB에 저장되어야 한다.")
    void createStoreTest() {

        // given
        Seller seller = Seller.createSeller("owner@gmail.com");
        em.persist(seller);

        Address address = Address.createAddress(10.0, 20.0, "123-456");

        // when
        String storeName = "굉장한 맛집";
        Long store_id = storeService.createStore(storeName, address, seller.getId());
        Store foundStore = em.find(Store.class, store_id);

        // then
        assertEquals(storeName, foundStore.getName());
        assertEquals(address, foundStore.getAddress(),
                "저장된 store의 address 값이 입력된 것과 동일해야 한다.");
        assertEquals(seller.getId(), foundStore.getSeller().getId(),
                "저장된 store의 seller id가 입력된 것과 동일해야 한다.");
        List<Item> items = new ArrayList<>();
        assertEquals(items, foundStore.getItems(),
                "저장된 store의 items 값은 비어있는 ArrayList 값과 동일해야 한다.");
        assertFalse(foundStore.isDeleted(),
                "저장된 store의 deleted 값은 false여야 한다.");

    }

    @Test
    @Transactional
    @DisplayName("seller를 찾지 못할 경우 예외를 발생시켜야 한다.")
    void createStoreNotFoundSellerTest() {

        // given
        Address address = Address.createAddress(10.0, 20.0, "123-456");

        // when
        Long randomLongNumber = 91205L;

        // then
        assertThrows(NotFoundException.class, () -> storeService.createStore("굉장한 맛집", address, randomLongNumber));

    }

}
