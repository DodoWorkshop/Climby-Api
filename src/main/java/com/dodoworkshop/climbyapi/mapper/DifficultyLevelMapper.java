package com.dodoworkshop.climbyapi.mapper;

import com.dodoworkshop.climbyapi.api.DifficultyLevelDto;
import com.dodoworkshop.climbyapi.entity.DifficultyLevel;
import org.springframework.stereotype.Component;

@Component
public class DifficultyLevelMapper {

    public DifficultyLevelDto toDto(DifficultyLevel entity) {
        return DifficultyLevelDto.builder()
                .id(entity.getId())
                .color(entity.getColor())
                .score(entity.getScore())
                .order(entity.getOrder())
                .build();
    }
}
