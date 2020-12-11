package com.escapenavigator.slot.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;
import java.util.List;


@Data
@Document(collection = "tariff")
public class Tariff {

    @Id
    private UUID id = UUID.randomUUID();
    private UUID profileId; 
    private String title; // tariff title
    private List<PriceSchema> priceSchema; 

    @Data
    public static class PriceSchema {
        private int count;
        private int price;
    }

}

