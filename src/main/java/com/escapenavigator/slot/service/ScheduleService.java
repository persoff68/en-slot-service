package com.escapenavigator.slot.service;

import com.escapenavigator.slot.model.Schedule;
import com.escapenavigator.slot.model.Slot;

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
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final SlotService slotService;
    
    /**
     * Get {@link Schedule} by ID
     * 
     * @param id
     * @return
     */
    public Schedule getById(UUID id) {
        return scheduleRepository.findById(id)
                .orElseThrow(ModelNotFoundException::new);
    }

    /**
     * Get {@link Schedule} by Profile ID
     * 
     * @param profileId
     * @return
     */
    public List<Schedule> getByProfile(UUID profileId) {
        return scheduleRepository.findByProfileId(profileId);
    }

    /**
     * Create {@link Schedule}
     * 
     * @param schedule
     * @return
     */
    public Schedule create(Schedule schedule) {
        if (schedule.getId() != null) {
            throw new ModelAlreadyExistsException();
        }

        // if slots contains order.id throw error
        if (slotService.someHasOrder(schedule)) {
            throw new Error();
        }

        // if schedule startDate equal today set schedule status to actual
        schedule.setStatus(schedule.getStartDate().equals(new Date()) ? "current" : "future");
        
        // after schedule is created we nedd to rebuild slots
        slotService.generateSlots(schedule);

        return scheduleRepository.save(schedule);
    }

    /**
     * Remove {@link Schedule} by ID
     * 
     * @param id
     */
    public void delete(UUID id) {

        Schedule schedule = scheduleRepository.findById(id)
            .orElseThrow(ModelNotFoundException::new);

        // we can't remove current schedule
        if (schedule.getStatus().equals("current")) {
            throw new Error();
        }

        scheduleRepository.deleteById(id); 

        // if schedule is futured we need to rebuild slots
        if (!schedule.getStatus().equals("past")) {
            slotService.rebuildSlots(schedule);
        } 


    }




}
