package com.foodlab.foodReservation.item.service;

import com.foodlab.foodReservation.item.dto.request.CreateItemRequest;
import com.foodlab.foodReservation.item.dto.request.UpdateItemRequest;
import com.foodlab.foodReservation.item.dto.response.CreateItemResponse;
import com.foodlab.foodReservation.item.dto.response.DeleteItemResponse;
import com.foodlab.foodReservation.item.entity.Item;
import com.foodlab.foodReservation.item.repository.ItemRepository;
import com.foodlab.foodReservation.store.entity.Store;
import com.foodlab.foodReservation.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final StoreRepository storeRepository;

    @Transactional
    public void updateItem(Long itemId, UpdateItemRequest updateItemRequest) {
        Item item = itemRepository.findById(itemId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 메뉴입니다."));
        item.updateItem(updateItemRequest.getName(), updateItemRequest.getPrice());
    }

    @Transactional
    public CreateItemResponse createItem(CreateItemRequest createItemRequest) {
        Store store = storeRepository.findById(createItemRequest.getStoreId()).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 음식점입니다."));
        Item item = Item.builder()
                .name(createItemRequest.getName())
                .price(createItemRequest.getPrice())
                .store(store)
                .deleted(false)
                .build();
        Item savedItem = itemRepository.save(item);
        return new CreateItemResponse(savedItem.getId());
    }

    @Transactional
    public DeleteItemResponse deleteItem(Long itemId) {
        Item item = itemRepository.findById(itemId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 매뉴입니다."));
        item.delete();
        return new DeleteItemResponse(item.getId());
    }

}
