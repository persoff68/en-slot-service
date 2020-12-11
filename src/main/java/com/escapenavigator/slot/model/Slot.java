package com.escapenavigator.slot.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.UUID;

@Data
@Document(collection = "slot")
public class Slot {

    @Id
    private UUID id = UUID.randomUUID();
    private UUID questroomId;
    private Date date;
    private Date start;
    private Date end;
    private UUID tariffId;
    private UUID templateId;
    private UUID holidayId;
    private UUID profileId;

}
