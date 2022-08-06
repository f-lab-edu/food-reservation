package com.foodlab.foodReservation.item.controller;

import com.foodlab.foodReservation.item.dto.request.CreateItemRequest;
import com.foodlab.foodReservation.item.dto.request.UpdateItemRequest;
import com.foodlab.foodReservation.item.dto.response.CreateItemResponse;
import com.foodlab.foodReservation.item.dto.response.DeleteItemResponse;
import com.foodlab.foodReservation.item.dto.response.UpdateItemResponse;
import com.foodlab.foodReservation.item.entity.Item;
import com.foodlab.foodReservation.item.service.ItemService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class ItemControllerUnitTest {

    @Mock
    ItemService itemService;

    @InjectMocks
    ItemController itemController;

    @DisplayName("매뉴 등록 성공")
    @Test
    void createItemSuccess() {
        // given
        CreateItemRequest createItemRequest = CreateItemRequest.builder().name("TEST_MENU").price(100000).storeId(1L).build();
        when(itemService.createItem(createItemRequest)).thenReturn(new CreateItemResponse(1L));

        // when
        CreateItemResponse createItemResponse = itemController.createItem(createItemRequest);

        // then
        assertEquals(1L, createItemResponse.getSavedItemId());
    }

    @DisplayName("매뉴 등록 실패 - 음식점 존재하지 않는 예외 발생")
    @Test
    void createItemFail() {
        // given
        CreateItemRequest createItemRequest = CreateItemRequest.builder().name("TEST_MENU").price(100000).storeId(null).build();

        // when
        when(itemService.createItem(createItemRequest)).thenThrow(new IllegalArgumentException("존재하지 않는 음식점입니다."));

        // then
        assertThrows(IllegalArgumentException.class, () -> itemController.createItem(createItemRequest));
    }

    @DisplayName("매뉴 삭제 성공")
    @Test
    void deleteItemSuccess() {
        // given
        when(itemController.deleteItem(1L)).thenReturn(new DeleteItemResponse(1L, true));

        // expected
        assertEquals(itemController.deleteItem(1L).getDeletedItemId(), 1L);
    }

    @DisplayName("매뉴 삭제 실패 - 삭제할 매뉴가 존재하지 않는 예외 발생")
    @Test
    void deleteItemFail() {
        // given
        when(itemService.deleteItem(1L)).thenThrow(new IllegalArgumentException("존재하지 않는 매뉴입니다."));

        // expected
        assertThrows(IllegalArgumentException.class, () -> itemController.deleteItem(1L));
    }

    @DisplayName("매뉴 수정 성공")
    @Test
    void updateItemSuccess() {
        // given
        UpdateItemRequest updateItemRequest = UpdateItemRequest.builder().name("TEST_MENU").price(10000).build();
        when(itemService.updateItem(1L, updateItemRequest)).thenReturn(new UpdateItemResponse("TEST_MENU", 10000));

        // when
        UpdateItemResponse updateItemResponse = itemController.updateItem(1L, updateItemRequest);

        // then
        assertEquals(updateItemResponse.getName(), "TEST_MENU");
        assertEquals(updateItemResponse.getPrice(), 10000);
    }

    @DisplayName("매뉴 수정 실패 - 수정할 매뉴가 존재하지 않는 예외 발생")
    @Test
    void updateItemFail() {
        // given
        UpdateItemRequest updateItemRequest = UpdateItemRequest.builder().name("TEST_MENU").price(10000).build();

        // when
        when(itemService.updateItem(1L, updateItemRequest)).thenThrow(new IllegalArgumentException("존재하지 않는 매뉴입니다."));

        // then
        assertThrows(IllegalArgumentException.class, () -> itemController.updateItem(1L, updateItemRequest));
    }

}
