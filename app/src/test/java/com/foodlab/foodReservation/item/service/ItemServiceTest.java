package com.foodlab.foodReservation.item.service;

import com.foodlab.foodReservation.common.Address;
import com.foodlab.foodReservation.item.dto.request.CreateItemRequest;
import com.foodlab.foodReservation.item.dto.request.UpdateItemRequest;
import com.foodlab.foodReservation.item.dto.response.CreateItemResponse;
import com.foodlab.foodReservation.item.dto.response.DeleteItemResponse;
import com.foodlab.foodReservation.item.dto.response.UpdateItemResponse;
import com.foodlab.foodReservation.item.entity.Item;
import com.foodlab.foodReservation.item.repository.ItemRepository;
import com.foodlab.foodReservation.seller.entity.Seller;
import com.foodlab.foodReservation.store.entity.Store;
import com.foodlab.foodReservation.store.repository.StoreRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ItemServiceTest {

    @Mock
    StoreRepository storeRepository;

    @Mock
    ItemRepository itemRepository;

    @InjectMocks
    ItemService itemService;

    @DisplayName("메뉴 수정 요청이 들어왔을 때, 메뉴가 존재하면 메뉴가 수정되어야 합니다.")
    @Test
    void updateItemShouldUpdateItemWhenItemFound() {

        // given
        Item item = new Item("하와이안 피자", 10000, new Store());

        when(itemRepository.findById(123L)).thenReturn(Optional.of(item));

        UpdateItemRequest request = UpdateItemRequest.builder()
                .name("누구나 좋아하는 하와이안 피자")
                .price(11000)
                .build();

        // when
        UpdateItemResponse response = itemService.updateItem(123L, request);

        // then
        assertEquals("누구나 좋아하는 하와이안 피자", item.getName());
        assertEquals("누구나 좋아하는 하와이안 피자", response.getName());
        assertEquals(11000, item.getPrice());
        assertEquals(11000, response.getPrice());

        verify(itemRepository).findById(123L);

    }

    @DisplayName("메뉴 수정 요청이 들어왔을 때, 메뉴가 존재하지 않으면 메뉴 수정 요청은 예외를 발생시켜야 합니다.")
    @Test
    void updateItemShouldThrowWhenItemNotFound() {

        // given
        when(itemRepository.findById(123L)).thenReturn(Optional.empty());

        UpdateItemRequest request = UpdateItemRequest.builder()
                .name("누구나 좋아하는 하와이안 피자")
                .price(11000)
                .build();

        // then
        assertThrows(IllegalArgumentException.class, () -> itemService.updateItem(123L, request));

        verify(itemRepository).findById(123L);

    }


    @DisplayName("매뉴 등록 성공")
    @Test
    void createItemSuccess() {

        // given
        Address address = Address.builder().address("서울역").longitude(129.041621).latitude(35.114928).zipCode("04320").build();
        Seller seller = Seller.builder().id(1L).username("test_user").password("test_password").build();
        Store store = Store.builder().id(1L).name("test_store").address(address).seller(seller).build();
        when(storeRepository.findById(store.getId())).thenReturn(Optional.of(store));

        Item item = Item.builder().id(1L).name("아이스아메리카노").price(4500).store(store).deleted(false).build();
        when(itemRepository.save(any(Item.class))).thenReturn(item);
        CreateItemRequest createItemRequest = CreateItemRequest.builder().name("아이스아메리카노").price(4500).storeId(store.getId()).build();

        // when
        CreateItemResponse savedItem = itemService.createItem(createItemRequest);

        // then
        assertEquals(savedItem.getSavedItemId(), 1L);

    }

    @DisplayName("매뉴 등록 실패 - 음식점이 존재하지 않는 경우")
    @Test
    void createItemFail() {

        // given
        CreateItemRequest createItemRequest = CreateItemRequest.builder().name("아이스아메리카노").price(4500).storeId(null).build();

        // expected
        assertThrows(IllegalArgumentException.class, () -> itemService.createItem(createItemRequest));

    }

    @DisplayName("매뉴 삭제 성공")
    @Test
    void deleteItemSuccess() {

        // given
        Item item = Item.builder().id(1L).name("아이스아메리카노").price(4500).store(any(Store.class)).deleted(false).build();
        itemRepository.save(item);

        // when
        when(itemRepository.findById(1L)).thenReturn(Optional.of(item));
        Item savedItem = itemRepository.findById(item.getId()).orElseThrow(IllegalArgumentException::new);
        DeleteItemResponse deletedItem = itemService.deleteItem(savedItem.getId());

        // then
        assertTrue(deletedItem.isDeleted());

    }

    @DisplayName("매뉴 삭제 실패 - 삭제할 매뉴가 존재하지 않는 경우")
    @Test
    void deleteItemFail() {

        // given
        when(itemRepository.findById(1L)).thenReturn(Optional.empty());

        // expected
        assertThrows(IllegalArgumentException.class, () -> itemService.deleteItem(1L));

    }
}
