package com.escapenavigator.slot.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;
import java.util.List;

/**
 * Создание шаблона простое
 * 
 * Редактировать шаблон можно только если нет игр на слоты с данным шаблоном
 * При редактировании шаблона необходимо перегенерировать все слоты, использующие данный шаблон в Schedule
 * 
 * Удалить шаблон можно, только если он не используется в Schedule
 */

@Data
@Document(collection = "template")
public class Template {

    @Id
    private UUID id = UUID.randomUUID();
    private String questroomId;
    private String profileId;
    private List slotsTemplate; // [[{start: '10:00', end: "11:00", tariff: tariff.id}],[],[],[],[],[],[]]

}

