package com.foodlab.foodReservation.Store.controller;

import com.foodlab.foodReservation.Common.Address;
import com.foodlab.foodReservation.Store.dto.CreateStoreRequest;
import com.foodlab.foodReservation.Store.dto.CreateStoreResponse;
import com.foodlab.foodReservation.Store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @PostMapping("/stores")
    public CreateStoreResponse createStore(@RequestBody @Valid CreateStoreRequest request) {
        Address address = Address.createAddress(request.getLongitude(), request.getLatitude(), request.getZipcode());
        Long storeId = storeService.createStore(request.getStoreName(), address, request.getSellerId());
        return new CreateStoreResponse(storeId);
    }

    @DeleteMapping("/stores/{id}")
    public void deleteStore(@PathVariable("id") Long id) {
        storeService.deleteStore(id);
    }
}
