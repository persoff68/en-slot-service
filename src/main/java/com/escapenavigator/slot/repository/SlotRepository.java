package com.escapenavigator.slot.repository;

import com.escapenavigator.slot.model.Slot;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;


@Repository
public interface SlotRepository extends MongoRepository<Slot, UUID> {

    List<Slot> findAllByHolidayId(UUID holidayId);
    List<Slot> findAllByTariffId(UUID tariffId);
    List<Slot> findAllByStartDateGreaterThanAndEndDateLessThanAndQuestroomIdIn(Date startDate, Date endDate, List<UUID> questroomIds);

}
