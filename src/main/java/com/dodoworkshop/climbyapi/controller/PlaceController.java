package com.dodoworkshop.climbyapi.controller;

import com.dodoworkshop.climbyapi.api.PlaceDto;
import com.dodoworkshop.climbyapi.api.request.PlaceCreationRequest;
import com.dodoworkshop.climbyapi.mapper.PlaceMapper;
import com.dodoworkshop.climbyapi.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/places", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class PlaceController {

    private final PlaceService placeService;

    private final PlaceMapper placeMapper;

    @GetMapping
    public List<PlaceDto> getAll() {
        return placeService.getPlaces()
                .stream()
                .map(placeMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public PlaceDto getOne(@PathVariable Long id) {
        return placeMapper.toDto(placeService.getPlace(id));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('SCOPE_write:places')")
    @ResponseStatus(HttpStatus.CREATED)
    public PlaceDto create(@RequestBody PlaceCreationRequest request) {
        return placeMapper.toDto(placeService.createPlace(request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_write:places')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        placeService.deletePlace(id);
    }
}
