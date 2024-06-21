package com.dodoworkshop.climbyapi.api;

import lombok.Builder;

import java.util.List;

@Builder
public record PlaceDto(
        long id,

        String userId,

        String name,

        List<DifficultyLevelDto> difficultyLevels
) {
}
