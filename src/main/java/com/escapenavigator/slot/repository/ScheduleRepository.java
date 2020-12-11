package com.escapenavigator.slot.repository;

import com.escapenavigator.slot.model.Schedule;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ScheduleRepository extends MongoRepository<Schedule, UUID> {
    List<Schedule> findByProfileId(UUID profileId);
}
