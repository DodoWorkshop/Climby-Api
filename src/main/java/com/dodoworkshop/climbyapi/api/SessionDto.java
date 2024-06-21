package com.dodoworkshop.climbyapi.api;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record SessionDto(
        long id,

        PlaceDto place,

        LocalDateTime startedAt,

        LocalDateTime endedAt,

        List<SessionEntryDto> entries
) {
}
