package com.dodoworkshop.climbyapi.repository;

import com.dodoworkshop.climbyapi.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session, Long> {

    List<Session> findAllByUserIdAndEndedAtIsNull(String userId);

    List<Session> findAllByUserId(String userId);

    boolean existsByUserIdAndEndedAtIsNull(String userId);

    Optional<Session> findByIdAndUserId(long id, String userId);
}
