package com.dodoworkshop.climbyapi.mapper;

import com.dodoworkshop.climbyapi.api.SessionDto;
import com.dodoworkshop.climbyapi.entity.Session;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class SessionMapper {

    private final SessionEntryMapper sessionEntryMapper;
    private final PlaceMapper placeMapper;

    public SessionDto toDto(Session session) {
        return SessionDto.builder()
                .id(session.getId())
                .place(placeMapper.toDto(session.getPlace()))
                .startedAt(session.getStartedAt())
                .endedAt(session.getEndedAt())
                .entries(session.getEntries() == null
                        ? new ArrayList<>()
                        : session.getEntries()
                        .stream()
                        .map(sessionEntryMapper::toDto)
                        .toList()
                )
                .build();
    }
}
