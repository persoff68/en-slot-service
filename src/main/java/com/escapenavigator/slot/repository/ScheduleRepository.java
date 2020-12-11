package com.escapenavigator.slot.repository;

import com.escapenavigator.slot.model.Schedule;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
import java.util.Date;

@Repository
public interface ScheduleRepository extends MongoRepository<Schedule, UUID> {

    /**
     * 
     * @param profileId
     * @return
     */
    List<Schedule> findByProfileId(UUID profileId);

    /**
     * Find previos {@link Schedule} with status "current"
     * 
     * @param startDate
     * @param status
     * @param questroomId
     * @return
     */
    Schedule findByStartDateLessThanByStatusByQuestroomId(Date startDate, String status, UUID questroomId);
}
