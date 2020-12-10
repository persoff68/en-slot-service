package com.escapenavigator.slot.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;
import java.util.List;

/**
 * Апи тарифов - стандартный CRUD без каких либо особенностей,
 * но удалить тариф нельзя, если он есть хотья бы в одном из активных слотов
 */

@Data
@Document(collection = "tariff")
public class Tariff {

    @Id
    private UUID id = UUID.randomUUID();
    private String title; // название тарифа
    private String profileId; 
    private List price; // объект с ключем - кол-во человек, значением - цена (не знаю как тип ,объект, указать)

}

