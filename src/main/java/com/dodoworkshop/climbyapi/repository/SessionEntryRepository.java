package com.dodoworkshop.climbyapi.repository;

import com.dodoworkshop.climbyapi.entity.SessionEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionEntryRepository extends JpaRepository<SessionEntry, Long> {

}
