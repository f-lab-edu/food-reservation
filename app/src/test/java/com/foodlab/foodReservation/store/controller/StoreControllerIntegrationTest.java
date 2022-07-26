package com.foodlab.foodReservation.store.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foodlab.foodReservation.store.dto.request.CreateStoreRequest;
import com.foodlab.foodReservation.store.dto.response.CreateStoreResponse;
import com.foodlab.foodReservation.store.service.StoreService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
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
        String content = objectMapper.writeValueAsString(validRequest);

        when(storeService.createStore(validRequest)).thenReturn(new CreateStoreResponse(25L));

        // then
        mockMvc.perform(post("/stores")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("storeId", org.hamcrest.core.Is.is(25)));

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
        String content = objectMapper.writeValueAsString(badRequest);

        // then
        mockMvc.perform(post("/stores")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
                )
                .andDo(print())
                .andExpect(status().isBadRequest());

    }

    // TODO: API 예외 처리 코드 추가 필요 - 아직 아래의 테스트를 통과하지 못한다.
    @DisplayName("상점 생성 거부 - 서비스에서 상점 생성 요청에 대해 거부(예외 발생)하면 400 상태 메시지를 반환해야 합니다.")
    @Test
    void createStoreShouldFailWhenServiceRejects() throws Exception {

        // given
        CreateStoreRequest validRequest = CreateStoreRequest.builder()
                .address("서울시")
                .latitude(12.34)
                .longitude(56.78)
                .name("홍콩반점")
                .zipCode("123-456")
                .build();
        String content = objectMapper.writeValueAsString(validRequest);

        when(storeService.createStore(validRequest)).thenThrow(new IllegalArgumentException("some error message"));

        // then
        mockMvc.perform(post("/stores")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isBadRequest());

    }

}
