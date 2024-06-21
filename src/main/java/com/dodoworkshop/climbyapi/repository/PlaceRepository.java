package com.dodoworkshop.climbyapi.repository;

import com.dodoworkshop.climbyapi.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place, Long> {
    List<Place> findAllByUserId(String userId);
}
