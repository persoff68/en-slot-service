package com.escapenavigator.slot.repository;

import com.escapenavigator.slot.model.Tariff;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TariffRepository extends MongoRepository<Tariff, UUID> {
    List<Tariff> findByProfileId(UUID profileId);
}
