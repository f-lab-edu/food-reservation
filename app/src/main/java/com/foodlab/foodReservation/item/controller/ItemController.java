package com.foodlab.foodReservation.item.controller;

import com.foodlab.foodReservation.item.dto.request.CreateItemRequest;
import com.foodlab.foodReservation.item.dto.request.UpdateItemRequest;
import com.foodlab.foodReservation.item.dto.response.CreateItemResponse;
import com.foodlab.foodReservation.item.dto.response.DeleteItemResponse;
import com.foodlab.foodReservation.item.dto.response.UpdateItemResponse;
import com.foodlab.foodReservation.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/items/{itemId}")
    public UpdateItemResponse updateItem(@PathVariable("itemId") Long itemId, @RequestBody @Valid UpdateItemRequest updateItemRequest) {
        return itemService.updateItem(itemId, updateItemRequest);
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/items")
    public CreateItemResponse createItem(@RequestBody @Valid CreateItemRequest createItemRequest) {
        return itemService.createItem(createItemRequest);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/items/{itemId}")
    public DeleteItemResponse deleteItem(@PathVariable Long itemId) {
        return itemService.deleteItem(itemId);
    }
}
