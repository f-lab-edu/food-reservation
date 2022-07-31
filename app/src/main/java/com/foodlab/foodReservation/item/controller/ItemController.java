package com.foodlab.foodReservation.item.controller;

import com.foodlab.foodReservation.item.dto.request.UpdateItemRequest;
import com.foodlab.foodReservation.item.dto.response.UpdateItemResponse;
import com.foodlab.foodReservation.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PutMapping("/items/{itemId}")
    public UpdateItemResponse updateItem(@PathVariable("itemId") Long itemId, @RequestBody @Valid UpdateItemRequest updateItemRequest) {
        return itemService.updateItem(itemId, updateItemRequest);
    }
}
