package com.dodoworkshop.climbyapi.service;

import com.dodoworkshop.climbyapi.api.request.StartSessionRequest;
import com.dodoworkshop.climbyapi.api.request.StopSessionRequest;
import com.dodoworkshop.climbyapi.entity.Place;
import com.dodoworkshop.climbyapi.entity.Session;
import com.dodoworkshop.climbyapi.exception.type.api.BadRequestException;
import com.dodoworkshop.climbyapi.exception.type.api.ResourceNotFoundException;
import com.dodoworkshop.climbyapi.repository.PlaceRepository;
import com.dodoworkshop.climbyapi.repository.SessionRepository;
import com.dodoworkshop.climbyapi.utils.UserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SessionService {

    private final SessionRepository sessionRepository;
    private final PlaceRepository placeRepository;

    public List<Session> getAll() {
        final String userId = UserUtils.getUserId();

        return sessionRepository.findAllByUserId(userId);
    }

    public Session getCurrent() {
        final String userId = UserUtils.getUserId();

        final List<Session> sessions = sessionRepository.findAllByUserIdAndEndedAtIsNull(userId);

        if (sessions.isEmpty()) throw new ResourceNotFoundException(Session.class);
        if (sessions.size() > 1) throw new IllegalStateException("Too many active sessions");
        return sessions.get(0);
    }

    @Transactional
    public Session start(StartSessionRequest request) {
        final String userId = UserUtils.getUserId();

        if (sessionRepository.existsByUserIdAndEndedAtIsNull(userId)) {
            throw new BadRequestException("User already have a running session");
        }

        final Place place = placeRepository.findById(request.placeId())
                .orElseThrow(() -> new ResourceNotFoundException(Place.class));

        final Session session = Session.builder()
                .userId(userId)
                .place(place)
                .startedAt(LocalDateTime.now())
                .build();

        return sessionRepository.save(session);
    }

    @Transactional
    public void stop(StopSessionRequest request) {
        final Session session = getCurrent();

        session.setEndedAt(request.endDate() != null
                ? request.endDate()
                : LocalDateTime.now()
        );

        sessionRepository.save(session);
    }
}
