package com.foodlab.foodReservation.store.controller;


import com.foodlab.foodReservation.store.dto.request.CreateStoreRequest;
import com.foodlab.foodReservation.store.dto.request.UpdateStoreRequest;
import com.foodlab.foodReservation.store.dto.response.CreateStoreResponse;
import com.foodlab.foodReservation.store.dto.response.UpdateStoreResponse;
import com.foodlab.foodReservation.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<UpdateStoreResponse> updateStore(@PathVariable Long storeId, @Valid @RequestBody UpdateStoreRequest updateStoreRequest) {
        UpdateStoreResponse updateStoreResponse = storeService.updateStore(storeId, updateStoreRequest);
        return new ResponseEntity<>(updateStoreResponse, HttpStatus.OK);
    }

    @DeleteMapping("/store/{storeId}")
    public ResponseEntity<Void> deleteStore(@PathVariable Long storeId) {
        storeService.deleteStore(storeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
