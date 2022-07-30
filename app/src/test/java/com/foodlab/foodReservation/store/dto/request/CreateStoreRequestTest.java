package com.foodlab.foodReservation.store.dto.request;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CreateStoreRequestTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    private final String validName = "홍콩반점";
    private final String validAddress = "서울시";
    private final Double validLongitude = 12.0;
    private final Double validLatitude = 12.0;
    private final String validZipCode = "123-456";

    @Test
    void validDtoShouldBeValid() {
        // given
        CreateStoreRequest request = CreateStoreRequest.builder()
                .name(validName)
                .address(validAddress)
                .longitude(validLongitude)
                .latitude(validLatitude)
                .zipCode(validZipCode)
                .build();

        // when
        Set<ConstraintViolation<CreateStoreRequest>> validate = validator.validate(request);

        // then
        assertEquals(0, validate.size());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"  ", "   ", "\t", "\n"})
    void blankNameShouldViolate(String name) {
        // given
        CreateStoreRequest request = CreateStoreRequest.builder()
                .name(name)
                .address(validAddress)
                .longitude(validLongitude)
                .latitude(validLatitude)
                .zipCode(validZipCode)
                .build();

        // when
        Set<ConstraintViolation<CreateStoreRequest>> validate = validator.validate(request);

        // then
        assertEquals(1, validate.size());
        assertEquals(CreateStoreRequest.nameBlankErrorMsg, validate.stream().iterator().next().getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"Kim", "Lee", "Park", "random name"})
    void notBlankNameShouldBeValid(String name) {
        // given
        CreateStoreRequest request = CreateStoreRequest.builder()
                .name(name)
                .address(validAddress)
                .longitude(validLongitude)
                .latitude(validLatitude)
                .zipCode(validZipCode)
                .build();

        // when
        Set<ConstraintViolation<CreateStoreRequest>> validate = validator.validate(request);

        // then
        assertEquals(0, validate.size());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"  ", "   ", "\t", "\n"})
    void blankAddressShouldViolate(String address) {
        // given
        CreateStoreRequest request = CreateStoreRequest.builder()
                .name(validName)
                .address(address)
                .longitude(validLongitude)
                .latitude(validLatitude)
                .zipCode(validZipCode)
                .build();

        // when
        Set<ConstraintViolation<CreateStoreRequest>> validate = validator.validate(request);

        // then
        assertEquals(1, validate.size());
        assertEquals(CreateStoreRequest.addressBlankErrorMsg, validate.stream().iterator().next().getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"Seoul ABC Street", "Taipei", "Somewhere in the world"})
    void notBlankAddressShouldBeValid(String address) {
        // given
        CreateStoreRequest request = CreateStoreRequest.builder()
                .name(validName)
                .address(address)
                .longitude(validLongitude)
                .latitude(validLatitude)
                .zipCode(validZipCode)
                .build();

        // when
        Set<ConstraintViolation<CreateStoreRequest>> validate = validator.validate(request);

        // then
        assertEquals(0, validate.size());
    }

    @ParameterizedTest
    @MethodSource("longitudeTestArgumentsProvider")
    void longitudeOutSidePlusMinus180ShouldViolate(Double longitude, String errorMsg) {
        // given
        CreateStoreRequest request = CreateStoreRequest.builder()
                .name(validName)
                .address(validAddress)
                .longitude(longitude)
                .latitude(validLatitude)
                .zipCode(validZipCode)
                .build();

        // when
        Set<ConstraintViolation<CreateStoreRequest>> validate = validator.validate(request);

        // then
        assertEquals(1, validate.size());
        assertEquals(errorMsg, validate.stream().iterator().next().getMessage());
    }

    static Stream<Arguments> longitudeTestArgumentsProvider() {
        return Stream.of(
                Arguments.of(null, CreateStoreRequest.longitudeNullErrorMsg),
                Arguments.of(180.1, CreateStoreRequest.longitudeRangeErrorMsg),
                Arguments.of(-180.1, CreateStoreRequest.longitudeRangeErrorMsg),
                Arguments.of(190.0, CreateStoreRequest.longitudeRangeErrorMsg),
                Arguments.of(-190.0, CreateStoreRequest.longitudeRangeErrorMsg)
        );
    }

    @ParameterizedTest
    @ValueSource(doubles = {180.0, 92.4, 0, -103.2, -180.0})
    void longitudeWithinPlusMinus180ShouldBeValid(Double longitude) {
        // given
        CreateStoreRequest request = CreateStoreRequest.builder()
                .name(validName)
                .address(validAddress)
                .longitude(longitude)
                .latitude(validLatitude)
                .zipCode(validZipCode)
                .build();

        // when
        Set<ConstraintViolation<CreateStoreRequest>> validate = validator.validate(request);

        // then
        assertEquals(0, validate.size());
    }

    @ParameterizedTest
    @MethodSource("latitudeTestArgumentsProvider")
    void latitudeOutSidePlusMinus90ShouldViolate(Double latitude, String errorMsg) {
        // given
        CreateStoreRequest request = CreateStoreRequest.builder()
                .name(validName)
                .address(validAddress)
                .longitude(validLongitude)
                .latitude(latitude)
                .zipCode(validZipCode)
                .build();

        // when
        Set<ConstraintViolation<CreateStoreRequest>> validate = validator.validate(request);

        // then
        assertEquals(1, validate.size());
        assertEquals(errorMsg, validate.stream().iterator().next().getMessage());
    }

    static Stream<Arguments> latitudeTestArgumentsProvider() {
        return Stream.of(
                Arguments.of(null, CreateStoreRequest.latitudeNullErrorMsg),
                Arguments.of(90.1, CreateStoreRequest.latitudeRangeErrorMsg),
                Arguments.of(-90.1, CreateStoreRequest.latitudeRangeErrorMsg),
                Arguments.of(100.0, CreateStoreRequest.latitudeRangeErrorMsg),
                Arguments.of(-100.0, CreateStoreRequest.latitudeRangeErrorMsg)
        );
    }

    @ParameterizedTest
    @ValueSource(doubles = {90.0, 30.9, 0, -47.8, -90.0})
    void latitudeWithinPlusMinus90ShouldBeValid(Double latitude) {
        // given
        CreateStoreRequest request = CreateStoreRequest.builder()
                .name(validName)
                .address(validAddress)
                .longitude(validLongitude)
                .latitude(latitude)
                .zipCode(validZipCode)
                .build();

        // when
        Set<ConstraintViolation<CreateStoreRequest>> validate = validator.validate(request);

        // then
        assertEquals(0, validate.size());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"  ", "   ", "\t", "\n"})
    void blankZipCodeShouldViolate(String zipCode) {
        // given
        CreateStoreRequest request = CreateStoreRequest.builder()
                .name(validName)
                .address(validAddress)
                .longitude(validLongitude)
                .latitude(validLatitude)
                .zipCode(zipCode)
                .build();

        // when
        Set<ConstraintViolation<CreateStoreRequest>> validate = validator.validate(request);

        // then
        assertEquals(1, validate.size());
        assertEquals(CreateStoreRequest.zipCodeBlankErrorMsg, validate.stream().iterator().next().getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"0923-234", "3891123", "A"})
    void notBlankZipCodeShouldViolate(String zipCode) {
        // given
        CreateStoreRequest request = CreateStoreRequest.builder()
                .name(validName)
                .address(validAddress)
                .longitude(validLongitude)
                .latitude(validLatitude)
                .zipCode(zipCode)
                .build();

        // when
        Set<ConstraintViolation<CreateStoreRequest>> validate = validator.validate(request);

        // then
        assertEquals(0, validate.size());
    }
}
