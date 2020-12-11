package com.escapenavigator.slot.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.UUID;
import java.util.List;


@Data
@Document(collection = "holiday")
public class Holiday {

    @Id
    private UUID id = UUID.randomUUID();
    private String title; // holiday reason
    private UUID profileId; 
    private List<UUID> questroomIds; // List of questroomIds for closing
    private Date startDate;
    private Date endDate;


}
