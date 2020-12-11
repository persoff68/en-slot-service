package com.escapenavigator.slot.repository;

import com.escapenavigator.slot.model.Template;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TemplateRepository extends MongoRepository<Template, UUID> {
    List<Template> findByProfileId(UUID profileId);
}
