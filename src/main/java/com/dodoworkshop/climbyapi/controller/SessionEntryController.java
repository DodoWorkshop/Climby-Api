package com.dodoworkshop.climbyapi.controller;

import com.dodoworkshop.climbyapi.api.SessionEntryDto;
import com.dodoworkshop.climbyapi.api.request.AddSessionEntryRequest;
import com.dodoworkshop.climbyapi.entity.SessionEntry;
import com.dodoworkshop.climbyapi.mapper.SessionEntryMapper;
import com.dodoworkshop.climbyapi.service.SessionEntryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/sessions/active/entries", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class SessionEntryController {

    private final SessionEntryService sessionEntryService;

    private final SessionEntryMapper sessionEntryMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SessionEntryDto addEntry(@RequestBody AddSessionEntryRequest request){
        final SessionEntry entry = sessionEntryService.addToSession(request);

        return sessionEntryMapper.toDto(entry);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEntry(@PathVariable long id){
        sessionEntryService.removeFromSession(id);
    }
}
