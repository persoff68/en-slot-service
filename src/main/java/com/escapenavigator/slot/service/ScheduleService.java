package com.escapenavigator.slot.service;

import com.escapenavigator.slot.model.Schedule;

import com.escapenavigator.slot.error.ModelAlreadyExistsException;
import com.escapenavigator.slot.error.ModelNotFoundException;

import com.escapenavigator.slot.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public Schedule getById(UUID id) {
        return scheduleRepository.findById(id)
                .orElseThrow(ModelNotFoundException::new);
    }

    public List<Schedule> getByProfile(UUID profileId) {
        return scheduleRepository.findByProfileId(profileId);
    }

    public Schedule create(Schedule schedule) {
        if (schedule.getId() != null) {
            throw new ModelAlreadyExistsException();
        }

        // if slots contains order.id throw error

        // remove all slots and generate new 


        return scheduleRepository.save(schedule);
    }

    public void delete(UUID id) {
        if (scheduleRepository.existsById(id)) {
            throw new ModelNotFoundException();
        }

        // if schedule in past, just remove it

        // if schedule is current, throw error

        // if schedule is future - 
            // if slots contains order.id throw error
            // define actual schedule
            // remove all slots and generate new 

        scheduleRepository.deleteById(id);
    }



}
