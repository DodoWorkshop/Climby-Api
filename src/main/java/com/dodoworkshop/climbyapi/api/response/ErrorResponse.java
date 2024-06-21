package com.dodoworkshop.climbyapi.api.response;

import lombok.Builder;

@Builder
public record ErrorResponse(
        String message,
        Class<?> errorType
) {
}
