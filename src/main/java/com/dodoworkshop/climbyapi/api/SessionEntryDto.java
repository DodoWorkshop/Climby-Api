package com.dodoworkshop.climbyapi.api;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record SessionEntryDto(
        long id,

        LocalDateTime createdAt,

        DifficultyLevelDto difficultyLevel
) {
}
