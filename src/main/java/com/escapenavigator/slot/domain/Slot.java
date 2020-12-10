package com.escapenavigator.slot.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.UUID;

@Data
public class Slot {

    @Id
    private UUID id;
    private String questRoom;
    private Date date;
    private Date start;
    private Date end;
    private UUID tariffId;
    private UUID profileId;

}
