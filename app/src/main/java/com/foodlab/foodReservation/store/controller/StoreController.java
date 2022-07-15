package com.foodlab.foodReservation.store.controller;


import com.foodlab.foodReservation.store.dto.request.StoreUpdateDto;
import com.foodlab.foodReservation.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @PutMapping("/store/{storeId}")
    public ResponseEntity<Void> updateStore(@PathVariable Long storeId, @Valid @RequestBody StoreUpdateDto storeUpdateDto) {
        storeService.updateStore(storeId, storeUpdateDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
