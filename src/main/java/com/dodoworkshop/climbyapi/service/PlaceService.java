package com.dodoworkshop.climbyapi.service;

import com.dodoworkshop.climbyapi.api.request.PlaceCreationRequest;
import com.dodoworkshop.climbyapi.entity.DifficultyLevel;
import com.dodoworkshop.climbyapi.entity.Place;
import com.dodoworkshop.climbyapi.exception.type.api.ResourceNotFoundException;
import com.dodoworkshop.climbyapi.repository.PlaceRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaceService {

    private final PlaceRepository placeRepository;

    public List<Place> getPlaces() {
        return placeRepository.findAll();
    }

    public Place getPlace(long id) {
        return placeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Place.class));
    }

    @Transactional
    public Place createPlace(PlaceCreationRequest request) {
        final Place place = Place.builder()
                .name(request.name())
                .build();

        final List<DifficultyLevel> difficultyLevels = request.difficultyLevels()
                .stream()
                .map(input -> DifficultyLevel.builder()
                        .score(input.score())
                        .color(input.color())
                        .order(input.order())
                        .place(place)
                        .build()
                )
                .toList();

        place.setDifficultyLevels(difficultyLevels);

        return placeRepository.save(place);
    }

    @Transactional
    public void deletePlace(long id) {
        final Place place = getPlace(id);

        placeRepository.delete(place);
    }
}
