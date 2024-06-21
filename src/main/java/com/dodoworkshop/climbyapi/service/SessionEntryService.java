package com.dodoworkshop.climbyapi.service;

import com.dodoworkshop.climbyapi.api.request.AddSessionEntryRequest;
import com.dodoworkshop.climbyapi.entity.DifficultyLevel;
import com.dodoworkshop.climbyapi.entity.Session;
import com.dodoworkshop.climbyapi.entity.SessionEntry;
import com.dodoworkshop.climbyapi.exception.type.api.BadRequestException;
import com.dodoworkshop.climbyapi.exception.type.api.ResourceNotFoundException;
import com.dodoworkshop.climbyapi.repository.SessionEntryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SessionEntryService {

    private final SessionService sessionService;

    private final SessionEntryRepository sessionEntryRepository;

    @Transactional
    public SessionEntry addToSession(AddSessionEntryRequest request) {
        final Session session = sessionService.getCurrent();
        if(session.isDone()){
            throw new BadRequestException("Session is already done");
        }

        final DifficultyLevel difficultyLevel = session.getPlace()
                .getDifficultyLevels()
                .stream()
                .filter(level -> level.getId().equals(request.difficultyLevelId()))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException(DifficultyLevel.class));

        final SessionEntry sessionEntry = SessionEntry.builder()
                .session(session)
                .createdAt(LocalDateTime.now())
                .difficultyLevel(difficultyLevel)
                .build();

        return sessionEntryRepository.save(sessionEntry);
    }

    @Transactional
    public void removeFromSession(long id) {
        final Session session = sessionService.getCurrent();
        if(session.isDone()){
            throw new BadRequestException("Session is already done");
        }

        final SessionEntry entry = sessionEntryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(SessionEntry.class));

        final boolean entryBelongToSession = session.getEntries()
                .stream()
                .anyMatch(sessionEntry -> sessionEntry.getId().equals(id));
        if (!entryBelongToSession) {
            throw new ResourceNotFoundException(SessionEntry.class);
        }

        sessionEntryRepository.delete(entry);
    }
}
