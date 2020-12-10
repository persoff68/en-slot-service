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

    private List<UUID> questRoomIdList = new ArrayList<>();

    @Scheduled(initialDelay = 1000 * 5, fixedDelay = Long.MAX_VALUE)
    public void reportCurrentTime() {
        generateQuestRoomIds();
        List<Slot> slotList = new ArrayList<>();
        questRoomIdList.forEach(questRoomId -> {
            List<Slot> yearsSlotList = generateYearOfSlots(questRoomId);
            slotList.addAll(yearsSlotList);
            log.info("GenerateSlotsTask: slots for questRoom " + questRoomId + " generated");
        });
        log.info("GenerateSlotsTask: all slots generated");
        slotRepository.saveAll(slotList);

        log.info("GenerateSlotsTask: all slots saved");
    }

    private List<Slot> generateYearOfSlots(UUID questRoomId) {
        List<Slot> slotList = new ArrayList<>();
        for (int i = 0; i < 365; i++) {
            Date date = generateDateOfYear(i);
            List<Slot> daysSlotList = generateDayOfSlots(questRoomId, date);
            slotList.addAll(daysSlotList);
        }
        return slotList;
    }

    private List<Slot> generateDayOfSlots(UUID questRoomId, Date date) {
        List<Slot> slotList = new ArrayList<>();
        for (int i = 10; i < 21; i++) {
            Slot slot = generateSlot(questRoomId, date, generateStartDate(date, i));
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

    private static Slot generateSlot(UUID questRoomId, Date date, Date start) {
        Date end = Date.from(date.toInstant().plus(Duration.ofHours(1)));

        Slot slot = new Slot();
        slot.setQuestRoomId(questRoomId);
        slot.setDate(date);
        slot.setStart(start);
        slot.setEnd(end);
        return slot;
    }

    private void generateQuestRoomIds() {
        questRoomIdList = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            questRoomIdList.add(UUID.randomUUID());
        }
    }


}
