package com.foodlab.foodReservation.store;


import com.foodlab.foodReservation.common.Address;
import com.foodlab.foodReservation.seller.entity.Seller;
import com.foodlab.foodReservation.seller.repository.SellerRepository;
import com.foodlab.foodReservation.store.dto.request.UpdateStoreRequest;
import com.foodlab.foodReservation.store.dto.response.StoreDetailResponse;
import com.foodlab.foodReservation.store.dto.response.UpdateStoreResponse;
import com.foodlab.foodReservation.store.entity.Store;
import com.foodlab.foodReservation.store.repository.StoreRepository;
import com.foodlab.foodReservation.store.service.StoreService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class StoreServiceTest {

    @Mock
    StoreRepository storeRepository;

    @Mock
    SellerRepository sellerRepository;

    @InjectMocks
    StoreService storeService;

    @Test
    @DisplayName("음식점 정보 수정 성공")
    void updateStore() {

        // given
        Address address = Address.builder()
                .address("서울역")
                .longitude(129.041621)
                .latitude(35.114928)
                .zipCode("04320")
                .build();
        Seller seller = Seller.builder()
                .username("test_user")
                .password("test_password")
                .build();
        when(sellerRepository.findById(1L)).thenReturn(Optional.of(seller));
        Store store = Store.builder()
                .seller(seller)
                .name("스타벅스_서울역점")
                .address(address)
                .build();
        when(storeRepository.findById(1L)).thenReturn(Optional.of(store));

        // when
        UpdateStoreRequest updateStoreRequest = UpdateStoreRequest.builder()
                .name("스타벅스_부산역점")
                .address("부산역")
                .longitude(129.041418419)
                .latitude(35.115078556)
                .zipCode("48731")
                .sellerId(1L)
                .build();
        UpdateStoreResponse updatedStore = storeService.updateStore(1L, updateStoreRequest);

        // then
        StoreDetailResponse resultStore = storeService.getStore(updatedStore.getStoreId());
        assertEquals(resultStore.getName(), "스타벅스_부산역점");
        assertEquals(resultStore.getAddress().getAddress(), "부산역");
        assertEquals(resultStore.getAddress().getLongitude(), 129.041418419);
        assertEquals(resultStore.getAddress().getLatitude(), 35.115078556);
        assertEquals(resultStore.getAddress().getLatitude(), 35.115078556);
        assertEquals(resultStore.getAddress().getZipCode(), "48731");
    }

    @Test
    @DisplayName("음식점 정보 수정 실패 - 존재하지 않는 음식점")
    void updateStoreFail() {

        // given
        when(storeRepository.findById(1L)).thenReturn(Optional.empty());

        // when
        UpdateStoreRequest updateStoreRequest = UpdateStoreRequest.builder().build();

        // then
        assertThrows(IllegalArgumentException.class,
                () -> storeService.updateStore(1L, updateStoreRequest));

    }

}
