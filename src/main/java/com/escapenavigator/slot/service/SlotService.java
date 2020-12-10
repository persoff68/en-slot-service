package com.escapenavigator.slot.service;

import com.escapenavigator.slot.model.Slot;
import com.escapenavigator.slot.error.ModelAlreadyExistsException;
import com.escapenavigator.slot.error.ModelNotFoundException;
import com.escapenavigator.slot.repository.SlotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SlotService {

    private final SlotRepository slotRepository;

    public Slot getById(UUID id) {
        return slotRepository.findById(id)
                .orElseThrow(ModelNotFoundException::new);
    }

    public Slot create(Slot slot) {
        if (slot.getId() != null) {
            throw new ModelAlreadyExistsException();
        }
        return slotRepository.save(slot);
    }

    public Slot update(Slot slot) {
        if (slotRepository.existsById(slot.getId())) {
            throw new ModelNotFoundException();
        }
        return slotRepository.save(slot);
    }

    public void delete(UUID id) {
        if (slotRepository.existsById(id)) {
            throw new ModelNotFoundException();
        }
        slotRepository.deleteById(id);
    }

}
