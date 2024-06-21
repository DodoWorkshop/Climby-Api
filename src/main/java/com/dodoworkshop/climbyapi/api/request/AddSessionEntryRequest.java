package com.dodoworkshop.climbyapi.api.request;

import lombok.Builder;

@Builder
public record AddSessionEntryRequest(
        long difficultyLevelId
) {
}
