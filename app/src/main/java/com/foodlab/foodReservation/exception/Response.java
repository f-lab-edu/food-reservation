package com.foodlab.foodReservation.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/*
https://github.com/omniti-labs/jsend
JSend : 웹 서버의 JSON 응답 format 에 대한 한 specification.

해당 specification 을 참조하여 아래의 타입을 정의함.
 */

@Getter
@Builder
@AllArgsConstructor
public class Response<T> {
    private final Status status;
    private final T data;

    public enum Status {
        SUCCESS("success"),
        FAIL("fail"),
        ERROR("error");

        private final String status;

        Status(String status) {
            this.status = status;
        }

        @Override
        public String toString() {
            return this.status;
        }
    }

}

