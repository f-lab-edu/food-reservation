package com.foodlab.foodReservation.store.controller;


import com.foodlab.foodReservation.store.dto.request.CreateStoreRequest;
import com.foodlab.foodReservation.store.dto.request.StoreUpdateDto;
import com.foodlab.foodReservation.store.dto.response.CreateStoreResponse;
import com.foodlab.foodReservation.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @PostMapping("/stores")
    public ResponseEntity<CreateStoreResponse> createStore(@RequestBody @Valid CreateStoreRequest createStoreDto) {
        CreateStoreResponse response = storeService.createStore(createStoreDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/store/{storeId}")
    public ResponseEntity<Void> updateStore(@PathVariable Long storeId, @Valid @RequestBody StoreUpdateDto storeUpdateDto) {
        storeService.updateStore(storeId, storeUpdateDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
