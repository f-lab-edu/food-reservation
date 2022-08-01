package com.foodlab.foodReservation.store.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foodlab.foodReservation.store.dto.request.CreateStoreRequest;
import com.foodlab.foodReservation.store.dto.response.CreateStoreResponse;
import com.foodlab.foodReservation.store.service.StoreService;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = StoreController.class)
class StoreControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private StoreService storeService;

    @DisplayName("상점 생성 수행")
    @Test
    void createStoreTest() throws Exception {

        // given
        CreateStoreRequest validRequest = CreateStoreRequest.builder()
                .address("서울시")
                .latitude(12.34)
                .longitude(56.78)
                .name("홍콩반점")
                .zipCode("123-456")
                .build();
        when(storeService.createStore(any(CreateStoreRequest.class)))
                .thenReturn(new CreateStoreResponse(25L));

        // then
        mockMvc.perform(post("/stores")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validRequest))
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("storeId", Is.is(25)));

    }

    @DisplayName("상점 생성 거부 - DTO validation 실패 시 400 상태 메시지를 반환해야 합니다.")
    @Test
    void createStoreShouldFailWhenDTOValidationFails() throws Exception {

        // given
        CreateStoreRequest badRequest = CreateStoreRequest.builder()
                .address("서울시")
                .latitude(12.34)
                .longitude(56.78)
                .name(null) // @NotBlank violation
                .zipCode("123-456")
                .build();

        // then
        mockMvc.perform(post("/stores")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(badRequest))
                )
                .andDo(print())
                .andExpect(status().isBadRequest());

    }

    @DisplayName("상점 생성 거부 - 서비스에서 상점 생성 요청에 대해 IllegalArgumentException이 발생하면 400 상태 메시지를 반환해야 합니다.")
    @Test
    void createStoreShouldFailWhenServiceRejects() throws Exception {

        // given
        when(storeService.createStore(any(CreateStoreRequest.class)))
                .thenThrow(new IllegalArgumentException("some error message"));

        CreateStoreRequest validRequest = CreateStoreRequest.builder()
                .address("서울시")
                .latitude(12.34)
                .longitude(56.78)
                .name("홍콩반점")
                .zipCode("123-456")
                .build();

        // then
        mockMvc.perform(post("/stores")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validRequest))
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("success", Is.is(false)))
                .andExpect(jsonPath("statusCode", Is.is(HttpStatus.BAD_REQUEST.value())))
                .andExpect(jsonPath("errorMessage", Is.is("some error message")))
                .andExpect(jsonPath("data", IsNull.nullValue()));

        verify(storeService).createStore(any(CreateStoreRequest.class));

    }

}
