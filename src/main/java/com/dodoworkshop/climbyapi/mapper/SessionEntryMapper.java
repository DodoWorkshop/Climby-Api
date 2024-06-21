package com.dodoworkshop.climbyapi.mapper;

import com.dodoworkshop.climbyapi.api.SessionEntryDto;
import com.dodoworkshop.climbyapi.entity.SessionEntry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SessionEntryMapper {

    private final DifficultyLevelMapper difficultyLevelMapper;

    public SessionEntryDto toDto(SessionEntry entity) {
        return SessionEntryDto.builder()
                .id(entity.getId())
                .difficultyLevel(difficultyLevelMapper.toDto(entity.getDifficultyLevel()))
                .createdAt(entity.getCreatedAt())
                .build();
    }
}
