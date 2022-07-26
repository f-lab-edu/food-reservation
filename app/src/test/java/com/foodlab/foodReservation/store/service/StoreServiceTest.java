package com.foodlab.foodReservation.store.service;

import com.foodlab.foodReservation.seller.entity.Seller;
import com.foodlab.foodReservation.seller.repository.SellerRepository;
import com.foodlab.foodReservation.store.dto.request.CreateStoreRequest;
import com.foodlab.foodReservation.store.dto.response.CreateStoreResponse;
import com.foodlab.foodReservation.store.entity.Store;
import com.foodlab.foodReservation.store.repository.StoreRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StoreServiceTest {

    @Mock
    StoreRepository storeRepository;

    @Mock
    SellerRepository sellerRepository;

    @InjectMocks
    StoreService storeService;

    CreateStoreRequest getCreateStoreRequest() {
        CreateStoreRequest request = new CreateStoreRequest();
        request.setSellerId(1);
        request.setName("Son");
        request.setAddress("Seoul");
        request.setLongitude(100.0);
        request.setLatitude(90.0);
        request.setZipCode("123");
        return request;
    }

    @DisplayName("상점 생성 요청이 들어왔을 때, 판매자가 존재하면 상점이 생성되어야 합니다.")
    @Test
    void createStoreShouldCreateStoreIfSellerFound() {
        // given
        CreateStoreRequest request = getCreateStoreRequest();
        Seller seller = new Seller();
        Store store = new Store();

        Long storeId = 423L;
        ReflectionTestUtils.setField(store, "id", storeId);

        when(sellerRepository.findById(request.getSellerId()))
                .thenReturn(Optional.of(seller));   // seller found

        when(storeRepository.save(any(Store.class)))
                .thenReturn(store);

        // when
        CreateStoreResponse response = storeService.createStore(request);

        // then
        assertEquals(storeId, response.getStoreId());

        // verify
        verify(sellerRepository).findById(request.getSellerId());
        verify(storeRepository).save(any(Store.class));
    }

    @DisplayName("상점 생성 요청이 들어왔을 때, 판매자가 존재하지 않으면 상점 만들기 요청은 예외를 발생시켜야 합니다.")
    @Test
    void createStoreShouldThrowIfSellerNotFound() {
        // given
        CreateStoreRequest request = getCreateStoreRequest();

        when(sellerRepository.findById(request.getSellerId()))
                .thenReturn(Optional.empty());   // seller not found

        // then
        assertThrows(IllegalArgumentException.class, () -> storeService.createStore(request));

        verify(sellerRepository).findById(request.getSellerId());
        verifyNoInteractions(storeRepository);
    }

    @DisplayName("상점 삭제 요청이 들어왔을 때, 해당 상점이 존재하면 상점을 삭제해야 합니다.")
    @Test
    void deleteStoreShouldDeleteStoreIfStoreFound() {
        // given
        Store store = new Store();
        Store spyStore = spy(store);

        Long storeId = 123L;

        when(storeRepository.findById(storeId))
                .thenReturn(Optional.of(spyStore));

        // when
        storeService.deleteStore(storeId);

        // then
        verify(storeRepository).findById(storeId);
        verify(spyStore).delete();
    }

    @DisplayName("상점 삭제 요청이 들어왔을 때, 해당 상점이 존재하지 않으면 예외를 발생시켜야 합니다.")
    @Test
    void deleteStoreShouldThrowIfStoreNotFound() {
        // given
        when(storeRepository.findById(anyLong()))
                .thenReturn(Optional.empty());

        // then
        assertThrows(IllegalArgumentException.class, () -> storeService.deleteStore(214L));
    }


}