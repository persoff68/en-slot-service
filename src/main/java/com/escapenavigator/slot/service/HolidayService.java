package com.escapenavigator.slot.service;

import com.escapenavigator.slot.model.Holiday;
import com.escapenavigator.slot.error.ModelAlreadyExistsException;
import com.escapenavigator.slot.error.ModelNotFoundException;
import com.escapenavigator.slot.repository.HolidayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * 
 * При создании выходных все слоты на указанные квесты и выпадающие указанную дату 
 * получают параметр holiday: holiday.id
 * 
 * При удалении выходных у всех слотов c holiday.id ставим holiday: null
 * 
 * С целью уменьшения объема работ выходные редактировать нельзя, только создать или удалить
 * 
 */

@Service
@RequiredArgsConstructor
public class HolidayService {

    private final HolidayRepository holidayRepository;
    private final SlotService slotService;

    public Holiday getById(UUID id) {
        return holidayRepository.findById(id)
                .orElseThrow(ModelNotFoundException::new);
    }

    public List<Holiday> getByProfile(UUID profileId) {
        return holidayRepository.findByProfileId(profileId);
    }

    public Holiday create(Holiday holiday) {
        if (holiday.getId() != null) {
            throw new ModelAlreadyExistsException();
        }

        Holiday createdHoliday = holidayRepository.save(holiday);

        // add holiday.id to all slots, which satisfy the holiday dates and questrooms
        slotService.addHolidayId(createdHoliday); 

        return createdHoliday;
    }

    public void delete(UUID id) {
        if (holidayRepository.existsById(id)) {
            throw new ModelNotFoundException();
        }
        
        holidayRepository.deleteById(id);

        // remove holiday.id from all slots, which contains this holiday
        slotService.removeHolidayId(id); 

    }

}
