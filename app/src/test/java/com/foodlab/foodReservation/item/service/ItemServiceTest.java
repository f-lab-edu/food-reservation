package com.foodlab.foodReservation.item.service;

import com.foodlab.foodReservation.common.Address;
import com.foodlab.foodReservation.item.dto.request.CreateItemRequest;
import com.foodlab.foodReservation.item.dto.response.CreateItemResponse;
import com.foodlab.foodReservation.item.entity.Item;
import com.foodlab.foodReservation.item.repository.ItemRepository;
import com.foodlab.foodReservation.seller.entity.Seller;
import com.foodlab.foodReservation.seller.repository.SellerRepository;
import com.foodlab.foodReservation.store.entity.Store;
import com.foodlab.foodReservation.store.repository.StoreRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ItemServiceTest {

    @Mock
    StoreRepository storeRepository;

    @Mock
    ItemRepository itemRepository;

    @InjectMocks
    ItemService itemService;

    @Test
    @DisplayName("매뉴 등록")
    void createItem() {

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
        assertEquals(savedItem.getItemId(), 1L);

    }

}
