package com.foodlab.foodReservation.Store.entity;

import com.foodlab.foodReservation.Common.Address;
import com.foodlab.foodReservation.Item.entity.Item;
import com.foodlab.foodReservation.Seller.entity.Seller;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.foodlab.foodReservation.Item.entity.Item.*;
import static org.junit.jupiter.api.Assertions.*;

class StoreTest {

    @Test
    @DisplayName("입력한 값으로 Store 객체를 생성해야 한다.")
    void createTest() {

        // given
        Address address = Address.createAddress(10.0, 20.0, "123-456");
        Seller seller = Seller.createSeller("owner@gmail.com");

        // when
        String storeName = "엄청난 맛집";
        Store store = Store.createStore(storeName, address, seller);

        // then
        assertEquals(storeName, store.getName());
        assertEquals(address, store.getAddress());
        assertEquals(new ArrayList<>(), store.getItems());
        assertSame(seller, store.getSeller());
        assertFalse(store.isDeleted());

    }

    @Test
    @DisplayName("store 삭제 시, store뿐만 아니라 연관되어 있는 item도 모두 soft delete 되어야 한다.")
    void deleteTest() {

        // given
        Address address = Address.createAddress(10.0, 20.0, "123-456");
        Seller seller = Seller.createSeller("owner@gmail.com");
        String storeName = "엄청난 맛집";
        Store store = Store.createStore(storeName, address, seller);


        Item item1 = createItem("오리지널 팟타이", "태국에서 먹는 맛 그대로!", store);
        Item item2 = createItem("그린 파파야 샐러드", "태국에서 먹는 맛 그대로!", store);

        // when
        store.delete();

        // then
        assertTrue(store.isDeleted());
        assertTrue(item1.isDeleted());
        assertTrue(item2.isDeleted());

    }

    @Test
    @DisplayName("store와 seller가 연관관계를 맺어야 한다.")
    void setSellerTest() {

        // given
        Address address = Address.createAddress(10.0, 20.0, "123-456");
        Seller seller1 = Seller.createSeller("owner1@gmail.com");
        String storeName = "엄청난 맛집";
        Store store = Store.createStore(storeName, address, seller1);

        // when
        Seller seller2 = Seller.createSeller("owner2@gmail.com");
        store.setSeller(seller2);

        // then
        assertSame(seller2, store.getSeller());
        List<Store> stores = new ArrayList<>();
        stores.add(store);
        assertEquals(stores, seller2.getStores());  // using List.equals

    }

}
