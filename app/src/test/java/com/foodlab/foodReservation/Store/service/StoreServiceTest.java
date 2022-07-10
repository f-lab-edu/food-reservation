package com.foodlab.foodReservation.Store.service;

import com.foodlab.foodReservation.Common.Address;
import com.foodlab.foodReservation.Seller.entity.Seller;
import com.foodlab.foodReservation.Store.entity.Store;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StoreServiceTest {

    @Autowired
    StoreService storeService;
    @Autowired
    EntityManager em;

    @Test
    @Transactional
    @DisplayName("충분한 정보가 주어졌을 때 Store 엔티티를 생성하여 DB에 저장한다.")
    void createTest() {
        // given
        Seller seller = Seller.createSeller("owner@gmail.com");
        em.persist(seller);

        Address address = Address.createAddress(10.0, 20.0, "123-456");

        // when
        Long store_id = storeService.create("굉장한 맛집", address, seller.getId());

        // then
        Store foundStore = em.find(Store.class, store_id);

        assertEquals(seller.getId(), foundStore.getSeller().getId(),
                "store의 seller의 아이디가 입력한 값과 동일해야 한다.");
        assertEquals(seller.getEmailAddress(), foundStore.getSeller().getEmailAddress(),
                "store의 seller의 email 주소가 입력한 값과 동일해야 한다.");
        assertEquals(address, foundStore.getAddress(),
                "store의 주소가 입력한 값과 동일해야 한다.");
    }



}