package com.escapenavigator.slot.service;

import com.escapenavigator.slot.model.Tariff;

import com.escapenavigator.slot.error.ModelAlreadyExistsException;
import com.escapenavigator.slot.error.ModelNotFoundException;

import com.escapenavigator.slot.repository.SlotRepository;
import com.escapenavigator.slot.repository.TariffRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


/**
 * 
 * Tariff API is a simple CRUD, but we can't remove it if tariff.id is used in one of the slots
 * 
 */


@Service
@RequiredArgsConstructor
public class TariffService {

    private final TariffRepository tariffRepository;
    private final SlotRepository slotRepository;

    public Tariff getById(UUID id) {
        return tariffRepository.findById(id)
                .orElseThrow(ModelNotFoundException::new);
    }

    
    public List<Tariff> getByProfile(UUID profileId) {
        return tariffRepository.findByProfileId(profileId);
    }


    public Tariff create(Tariff tariff) {
        if (tariff.getId() != null) {
            throw new ModelAlreadyExistsException();
        }

        return tariffRepository.save(tariff);
    }


    public Tariff update(Tariff tariff) {
        if (tariffRepository.existsById(tariff.getId())) {
            throw new ModelNotFoundException();
        }

        return tariffRepository.save(tariff);
    }


    public void delete(UUID id) {
        if (tariffRepository.existsById(id)) {
            throw new ModelNotFoundException();
        }

        // if slots contains this tariff we can't remove it
        if (!slotRepository.findAllByTariffId(id).isEmpty()) {
            throw new Error("Slots contains this tariff.");
        }

        tariffRepository.deleteById(id);
    }



}
