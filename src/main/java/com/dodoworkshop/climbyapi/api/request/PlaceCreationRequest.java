package com.dodoworkshop.climbyapi.api.request;

import java.util.List;

public record PlaceCreationRequest(
        String name,

        List<DifficultyLevel> difficultyLevels
) {
    public record DifficultyLevel(
            long color,

            int score,

            int order
    ) {

    }
}
