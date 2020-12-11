package com.escapenavigator.slot.service;

import com.escapenavigator.slot.model.Slot;
import com.escapenavigator.slot.model.Holiday;
import com.escapenavigator.slot.error.ModelAlreadyExistsException;
import com.escapenavigator.slot.error.ModelNotFoundException;
import com.escapenavigator.slot.repository.SlotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public void removeHolidayId(UUID holidayId) {
        List<Slot> slotList = slotRepository.findAllByHolidayId(holidayId);

        slotList.forEach(s -> {
            s.setHolidayId(null);
        });

        slotRepository.saveAll(slotList);
    }

    public void addHolidayId(Holiday holiday) {
        List<Slot> slotList = slotRepository.findAllByStartDateGreaterThanAndEndDateLessThanAndQuestroomIdIn(holiday.getStartDate(), holiday.getEndDate(), holiday.getQuestroomIds());

        slotList.forEach(s -> {
            s.setHolidayId(holiday.getId());
        });

        slotRepository.saveAll(slotList);
    }

}
