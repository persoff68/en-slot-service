package com.escapenavigator.slot.task;

import com.escapenavigator.slot.model.Slot;
import com.escapenavigator.slot.repository.SlotRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class GenerateSlotsTask {

    private final SlotRepository slotRepository;

    private List<UUID> questroomIdList = new ArrayList<>();

    @Scheduled(initialDelay = 1000 * 10, fixedDelay = Long.MAX_VALUE)
    public void reportCurrentTime() {
        generatequestroomIds();
        questroomIdList.forEach(questroomId -> {
            List<Slot> slotList = generateYearOfSlots(questroomId);
            slotRepository.saveAll(slotList);
            log.info("GenerateSlotsTask: slots for questroom " + questroomId + " generated");
        });
        log.info("GenerateSlotsTask: all slots generated");
    }

    private List<Slot> generateYearOfSlots(UUID questroomId) {
        List<Slot> slotList = new ArrayList<>();
        for (int i = 0; i < 365; i++) {
            Date date = generateDateOfYear(i);
            List<Slot> daySlotList = generateDayOfSlots(questroomId, date);
            slotList.addAll(daySlotList);
        }
        return slotList;
    }

    private List<Slot> generateDayOfSlots(UUID questroomId, Date date) {
        List<Slot> slotList = new ArrayList<>();
        for (int i = 10; i < 21; i++) {
            Slot slot = generateSlot(questroomId, date, generateStartDate(date, i));
            slotList.add(slot);
        }
        return slotList;
    }

    private static Date generateDateOfYear(int i) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_YEAR, i);
        return cal.getTime();
    }

    private static Date generateStartDate(Date date, int i) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, i);
        return cal.getTime();
    }

    private static Slot generateSlot(UUID questroomId, Date date, Date start) {
        Date end = Date.from(date.toInstant().plus(Duration.ofHours(1)));

        Slot slot = new Slot();
        slot.setquestroomId(questroomId);
        slot.setDate(date);
        slot.setStart(start);
        slot.setEnd(end);
        return slot;
    }

    private void generatequestroomIds() {
        questroomIdList = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            questroomIdList.add(UUID.randomUUID());
        }
    }


}
