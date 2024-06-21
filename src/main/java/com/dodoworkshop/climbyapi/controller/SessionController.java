package com.dodoworkshop.climbyapi.controller;

import com.dodoworkshop.climbyapi.api.SessionDto;
import com.dodoworkshop.climbyapi.api.request.StartSessionRequest;
import com.dodoworkshop.climbyapi.api.request.StopSessionRequest;
import com.dodoworkshop.climbyapi.mapper.SessionMapper;
import com.dodoworkshop.climbyapi.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/sessions", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class SessionController {

    private final SessionService sessionService;

    private final SessionMapper sessionMapper;

    @GetMapping
    public List<SessionDto> getAll() {
        return sessionService.getAll()
                .stream()
                .map(sessionMapper::toDto)
                .toList();
    }

    @GetMapping("/active")
    public SessionDto getActive() {
        return sessionMapper.toDto(sessionService.getCurrent());
    }

    @PostMapping("/start")
    @ResponseStatus(HttpStatus.CREATED)
    public SessionDto start(@RequestBody StartSessionRequest request) {
        return sessionMapper.toDto(sessionService.start(request));
    }

    @PostMapping("/active/stop")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void stop(@RequestBody StopSessionRequest request) {
        sessionService.stop(request);
    }
}
