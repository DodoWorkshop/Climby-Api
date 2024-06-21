package com.dodoworkshop.climbyapi.mapper;

import com.dodoworkshop.climbyapi.api.DifficultyLevelDto;
import com.dodoworkshop.climbyapi.api.PlaceDto;
import com.dodoworkshop.climbyapi.entity.Place;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Comparator;

@Component
@RequiredArgsConstructor
public class PlaceMapper {

    private final DifficultyLevelMapper difficultyLevelMapper;

    public PlaceDto toDto(Place entity) {
        return PlaceDto.builder()
                .id(entity.getId())
                .userId(entity.getUserId())
                .name(entity.getName())
                .difficultyLevels(entity.getDifficultyLevels()
                        .stream()
                        .map(difficultyLevelMapper::toDto)
                        .sorted(Comparator.comparingInt(DifficultyLevelDto::order))
                        .toList()
                )
                .build();
    }
}
