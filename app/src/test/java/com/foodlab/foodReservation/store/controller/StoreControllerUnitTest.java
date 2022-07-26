package com.foodlab.foodReservation.store.controller;

import com.foodlab.foodReservation.store.dto.request.CreateStoreRequest;
import com.foodlab.foodReservation.store.dto.response.CreateStoreResponse;
import com.foodlab.foodReservation.store.service.StoreService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StoreControllerUnitTest {

    @Mock
    StoreService storeService;

    @InjectMocks
    StoreController storeController;

    @DisplayName("상점 생성 수행")
    @Test
    void createStoreShouldCreateStoreAndReturn() {

        // given
        CreateStoreRequest request = new CreateStoreRequest();
        when(storeService.createStore(request)).thenReturn(new CreateStoreResponse(23L));

        // when
        ResponseEntity<CreateStoreResponse> response = storeController.createStore(request);

        // then
        assertEquals(HttpStatus.CREATED,response.getStatusCode());
        assertEquals(23L , Objects.requireNonNull(response.getBody()).getStoreId());

        verify(storeService).createStore(request);

    }

    @DisplayName("상점 생성 거부 - 서비스에서 상점 생성 요청에 대해 거부(예외 발생)하면 컨트롤러 또한 예외를 발생 시켜야 합니다.")
    @Test
    void createStoreShouldThrowWhenServiceRejects() {

        // given
        CreateStoreRequest request = new CreateStoreRequest();
        when(storeService.createStore(request)).thenThrow(new IllegalArgumentException("잘못된 요청입니다."));

        // then
        assertThrows(IllegalArgumentException.class, () -> storeController.createStore(request));

        verify(storeService).createStore(request);

    }

    @DisplayName("상점 삭제 수행")
    @Test
    void deleteStoreShouldDeleteStore() {

        // when
        ResponseEntity<Void> response = storeController.deleteStore(45L);

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @DisplayName("상점 삭제 거부 - 서비스에서 상점 삭제 요청에 대해 거부(예외 발생)하면 컨트롤러 또한 예외를 발생 시켜야 합니다.")
    @Test
    void deleteStoreShouldThrowWhenServiceRejects() {

        // given
        doThrow(new IllegalArgumentException("잘못된 요청입니다.")).when(storeService).deleteStore(36L);

        // then
        assertThrows(IllegalArgumentException.class, () -> storeController.deleteStore(36L));

        verify(storeService).deleteStore(36L);

    }

}
