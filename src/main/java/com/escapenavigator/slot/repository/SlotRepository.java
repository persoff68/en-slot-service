package com.escapenavigator.slot.repository;

import com.escapenavigator.slot.model.Slot;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SlotRepository extends MongoRepository<Slot, UUID> {
}
