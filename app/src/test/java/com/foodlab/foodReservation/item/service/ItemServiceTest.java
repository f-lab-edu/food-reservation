package com.foodlab.foodReservation.item.service;

import com.foodlab.foodReservation.item.dto.request.UpdateItemRequest;
import com.foodlab.foodReservation.item.entity.Item;
import com.foodlab.foodReservation.item.repository.ItemRepository;
import com.foodlab.foodReservation.store.entity.Store;
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
    ItemRepository itemRepository;

    @InjectMocks
    ItemService itemService;

    @DisplayName("메뉴 수정 요청이 들어왔을 때, 메뉴가 존재하면 메뉴가 수정되어야 합니다.")
    @Test
    void updateItemShouldUpdateItemWhenItemFound() {

        // given
        Item item = new Item("하와이안 피자", 10000, new Store());

        when(itemRepository.findById(123L)).thenReturn(Optional.of(item));

        UpdateItemRequest request = new UpdateItemRequest();
        request.setName("누구나 좋아하는 하와이안 피자");
        request.setPrice(11000);

        // when
        itemService.updateItem(123L, request);

        // then
        assertEquals("누구나 좋아하는 하와이안 피자", item.getName());
        assertEquals(11000, item.getPrice());

        verify(itemRepository).findById(123L);

    }

    @DisplayName("메뉴 수정 요청이 들어왔을 때, 메뉴가 존재하지 않으면 메뉴 수정 요청은 예외를 발생시켜야 합니다.")
    @Test
    void updateItemShouldThrowWhenItemNotFound() {

        // given
        when(itemRepository.findById(123L)).thenReturn(Optional.empty());

        UpdateItemRequest request = new UpdateItemRequest();

        // then
        assertThrows(IllegalArgumentException.class, () -> itemService.updateItem(123L, request));

        verify(itemRepository).findById(123L);

    }
}