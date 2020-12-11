package com.escapenavigator.slot.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;
import java.util.List;


@Data
@Document(collection = "template")
public class Template {

    @Id
    private UUID id = UUID.randomUUID();
    private UUID questroomId;
    private UUID profileId;
    private List<SlotTemplateList> slotsTemplate; 

    @Data
    public static class SlotTemplateList {
        private int dayOfWeek;
        private List<SlotTemplate> slots;
    }

    @Data
    public static class SlotTemplate {
        private String start;
        private String end;
        private UUID tariff;
    }


}

