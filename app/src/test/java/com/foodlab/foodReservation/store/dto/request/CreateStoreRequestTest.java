package com.foodlab.foodReservation.store.dto.request;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CreateStoreRequestTest {

    @Autowired
    private Validator validator;

    @Test
    void blankNameShouldViolate() {
        // given
        String name = "";

        CreateStoreRequest request = new CreateStoreRequest();
        request.setName(name);
        request.setAddress("서울시");
        request.setLongitude(12.0);
        request.setLatitude(12.0);
        request.setZipCode("123-456");

        // when
        Set<ConstraintViolation<CreateStoreRequest>> validate = validator.validate(request);

        // then
        assertEquals(1, validate.size());
        assertEquals(CreateStoreRequest.nameBlankErrorMsg, validate.stream().iterator().next().getMessage());
    }

    @Test
    void nullNameShouldViolate() {
        // given
        String name = null;

        CreateStoreRequest request = new CreateStoreRequest();
        request.setName(name);
        request.setAddress("서울시");
        request.setLongitude(12.0);
        request.setLatitude(12.0);
        request.setZipCode("123-456");

        // when
        Set<ConstraintViolation<CreateStoreRequest>> validate = validator.validate(request);

        // then
        assertEquals(1, validate.size());
        assertEquals(CreateStoreRequest.nameBlankErrorMsg, validate.stream().iterator().next().getMessage());
    }

    @Test
    void longitudeMoreThan180ShouldViolate() {
        // given
        double longitude = 180.1;

        CreateStoreRequest request = new CreateStoreRequest();
        request.setName("Kim");
        request.setAddress("서울시");
        request.setLongitude(longitude);
        request.setLatitude(12.0);
        request.setZipCode("123-456");

        // when
        Set<ConstraintViolation<CreateStoreRequest>> validate = validator.validate(request);

        // then
        assertEquals(1, validate.size());
        assertEquals(CreateStoreRequest.longitudeRangeErrorMsg, validate.stream().iterator().next().getMessage());
    }

    @Test
    void longitudeLessThanMinus180ShouldViolate() {
        // given
        double longitude = -180.1;

        CreateStoreRequest request = new CreateStoreRequest();
        request.setName("Kim");
        request.setAddress("서울시");
        request.setLongitude(longitude);
        request.setLatitude(12.0);
        request.setZipCode("123-456");

        // when
        Set<ConstraintViolation<CreateStoreRequest>> validate = validator.validate(request);

        // then
        assertEquals(1, validate.size());
        assertEquals(CreateStoreRequest.longitudeRangeErrorMsg, validate.stream().iterator().next().getMessage());
    }

    @Test
    void longitudeNullShouldViolate() {
        // given
        Double longitude = null;

        CreateStoreRequest request = new CreateStoreRequest();
        request.setName("Kim");
        request.setAddress("서울시");
        request.setLongitude(longitude);
        request.setLatitude(12.0);
        request.setZipCode("123-456");

        // when
        Set<ConstraintViolation<CreateStoreRequest>> validate = validator.validate(request);

        // then
        assertEquals(1, validate.size());
        assertEquals(CreateStoreRequest.longitudeNullErrorMsg, validate.stream().iterator().next().getMessage());
    }

}