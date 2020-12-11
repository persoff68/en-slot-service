package com.escapenavigator.slot.repository;

import com.escapenavigator.slot.model.Slot;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;


@Repository
public interface SlotRepository extends MongoRepository<Slot, UUID> {

    /**
     * find all slots by {@link Holiday} id
     * 
     * @param holidayId
     * @return
     */
    List<Slot> findAllByHolidayId(UUID holidayId);
    
    List<Slot> findAllByTariffId(UUID tariffId);

    List<Slot> findAllByTemplateId(UUID tariffId);

    /**
     * find all {@link Slot}s between dates which contains questroom.id from ids list
     * 
     * @param startDate
     * @param endDate
     * @param questroomIds
     * @return
     */
    List<Slot> findAllByStartDateGreaterThanAndEndDateLessThanAndQuestroomIdIn(Date startDate, Date endDate, List<UUID> questroomIds);
    
    /**
     * find all {@link Slot}s between dates which orderId not equal null
     * 
     * @param startDate
     * @param endDate
     * @return
     */
    List<Slot> findAllByStartDateGreaterThanAndEndDateLessThanAndOrderIdNonNull(Date startDate, Date endDate);

}
