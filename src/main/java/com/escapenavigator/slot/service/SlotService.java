package com.escapenavigator.slot.service;

import com.escapenavigator.slot.model.Slot;
import com.escapenavigator.slot.model.Holiday;
import com.escapenavigator.slot.model.Schedule;
import com.escapenavigator.slot.error.ModelAlreadyExistsException;
import com.escapenavigator.slot.error.ModelNotFoundException;
import com.escapenavigator.slot.repository.ScheduleRepository;
import com.escapenavigator.slot.repository.SlotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SlotService {

    private final SlotRepository slotRepository;
    private final ScheduleRepository scheduleRepository;

    /**
     * 
     * @param id
     * @return
     */
    public Slot getById(UUID id) {
        return slotRepository.findById(id).orElseThrow(ModelNotFoundException::new);
    }

    /**
     * 
     * @param slot
     * @return
     */
    public Slot create(Slot slot) {
        if (slot.getId() != null) {
            throw new ModelAlreadyExistsException();
        }
        return slotRepository.save(slot);
    }

    /**
     * 
     */
    public Slot update(Slot slot) {
        if (slotRepository.existsById(slot.getId())) {
            throw new ModelNotFoundException();
        }
        return slotRepository.save(slot);
    }

    /**
     * 
     */
    public void delete(UUID id) {
        if (slotRepository.existsById(id)) {
            throw new ModelNotFoundException();
        }
        slotRepository.deleteById(id);
    }

    /**
     * 
     * @param holidayId
     */
    public void removeHolidayId(UUID holidayId) {
        List<Slot> slotList = slotRepository.findAllByHolidayId(holidayId);

        slotList.forEach(s -> {
            s.setHolidayId(null);
        });

        slotRepository.saveAll(slotList);
    }

    /**
     * 
     * @param holiday
     */
    public void addHolidayId(Holiday holiday) {
        List<Slot> slotList = slotRepository.findAllByStartDateGreaterThanAndEndDateLessThanAndQuestroomIdIn(
                holiday.getStartDate(), holiday.getEndDate(), holiday.getQuestroomIds());

        slotList.forEach(s -> {
            s.setHolidayId(holiday.getId());
        });

        slotRepository.saveAll(slotList);
    }

    /**
     * after deleting schedule we need to rebuild slots
     * 
     * @param schedule
     */
    public void rebuildSlots(Schedule schedule) {

        Date endDate = getEndDateForGenerating(schedule.getStartDate(), schedule.getQuestroomId());

        if (someHasOrder(schedule.getStartDate(), endDate)) {
            throw new Error();
        }

        Schedule prevSchedule = scheduleRepository.findByStartDateLessThanByStatusByQuestroomId(schedule.getStartDate(),
                "current", schedule.getQuestroomId());

        deleteByDateAndQuestroom(schedule.getStartDate(), endDate, schedule.getQuestroomId());
        generateByDateAndQuestroom(schedule.getStartDate(), endDate, schedule.getQuestroomId(),
                prevSchedule.getTemplateId());
    }

    /**
     * 
     * @param startDate
     * @param endDate
     * @param questroomId
     */
    public void deleteByDateAndQuestroom(Date startDate, Date endDate, UUID questroomId) {
        List<Slot> slots = slotRepository.find();
        // remove
    }

    /**
     * 
     * @param startDate
     * @param endDate
     * @param questroomId
     * @param templateId
     */
    public void generateByDateAndQuestroom(Date startDate, Date endDate, UUID questroomId, UUID templateId) {
        // generate and save
    }

    /**
     * Define next date for generating slots
     * 
     * @param startDate
     * @param questroomId
     * @return
     */
    public Date getEndDateForGenerating(Date startDate, UUID questroomId) {
        Schedule nextSchedule = scheduleRepository.findNextByDateAndQuestroom(startDate, questroomId);
        return nextSchedule ? nextSchedule.getStartDate() : new Date();
    }

    /**
     * Check if {@link Slot}s contains order.id
     * 
     * @param startDate
     * @param endDate
     * @return
     */

    public boolean isAnyHasOrder(Date startDate, Date endDate) {
        List<Slot> slots = slotRepository.findAllByStartDateGreaterThanAndEndDateLessThanAndOrderIdNonNull(startDate,
                endDate);
        return !slots.isEmpty();
    }

    /**
     * Check if {@link Slot}s contains template.id
     * 
     * @param templateId
     * @return
     */
    public boolean isAnyHasTemplateId(UUID templateId) {
        List<Slot> slots = slotRepository.findAllByTemplateId(templateId);
        return !slots.isEmpty();
    }

}
