package com.foodlab.foodReservation.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Result<T> {
    private final boolean success;
    private final int statusCode;
    private final String errorMessage;
    private final T data;
}
