package com.escapenavigator.slot.repository;

import com.escapenavigator.slot.model.Holiday;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.List;

@Repository
public interface HolidayRepository extends MongoRepository<Holiday, UUID> {
    List<Holiday> findByProfileId(UUID profileId);
}
