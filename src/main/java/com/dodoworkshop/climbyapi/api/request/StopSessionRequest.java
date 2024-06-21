package com.dodoworkshop.climbyapi.api.request;

import java.time.LocalDateTime;

public record StopSessionRequest(
        LocalDateTime endDate
) {
}
