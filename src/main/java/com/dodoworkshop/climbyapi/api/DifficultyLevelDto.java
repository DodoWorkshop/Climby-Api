package com.dodoworkshop.climbyapi.api;

import lombok.Builder;

@Builder
public record DifficultyLevelDto(
        long id,

        long color,

        int score,

        int order
) {
}
