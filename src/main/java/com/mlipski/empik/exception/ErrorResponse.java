package com.mlipski.empik.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@ToString
@Getter
class ErrorResponse {
    private String message;
}