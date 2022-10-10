package com.foodlab.foodReservation.store.controller;


import com.foodlab.foodReservation.store.dto.request.CreateStoreRequest;
import com.foodlab.foodReservation.store.dto.request.UpdateStoreRequest;
import com.foodlab.foodReservation.store.dto.response.CreateStoreResponse;
import com.foodlab.foodReservation.store.dto.response.DeleteStoreResponse;
import com.foodlab.foodReservation.store.dto.response.StoreListResponse;
import com.foodlab.foodReservation.store.dto.response.UpdateStoreResponse;
import com.foodlab.foodReservation.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/stores")
    public CreateStoreResponse createStore(@RequestBody @Valid CreateStoreRequest createStoreDto) {
        return storeService.createStore(createStoreDto);
    }

    @PutMapping("/store/{storeId}")
    public ResponseEntity<UpdateStoreResponse> updateStore(@PathVariable Long storeId, @Valid @RequestBody UpdateStoreRequest updateStoreRequest) {
        UpdateStoreResponse updateStoreResponse = storeService.updateStore(storeId, updateStoreRequest);
        return new ResponseEntity<>(updateStoreResponse, HttpStatus.OK);
    }

    @DeleteMapping("/store/{storeId}")
    public DeleteStoreResponse deleteStore(@PathVariable Long storeId) {
        return storeService.deleteStore(storeId);
    }

    @GetMapping("/stores")
    public Page<StoreListResponse> stores(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        return storeService.getStores(pageable);
    }

    @GetMapping("/store/{storeId}")
    public Page<StoreDetailWithItemsResponse> storeWithItems(@PathVariable Long storeId, @PageableDefault Pageable pageable) {
        return storeService.getStoreWithItems(storeId, pageable);
    }

}
