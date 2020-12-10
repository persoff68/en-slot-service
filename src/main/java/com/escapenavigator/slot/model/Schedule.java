package com.escapenavigator.slot.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.UUID;
import java.util.List;

/**
 * При создании расписания происходит генерация слотов
 * 
 * Редактировать расписание нельзя
 * 
 * Удалить расписание можно, только если оно не актуальное
 */

@Data
@Document(collection = "schedule")
public class Schedule {

    @Id
    private UUID id = UUID.randomUUID();
    private String profileId;
    private Date from; // с какой даты вступает в силу данное расписание
    private Template template; //!! айди шаблона для генерации расписания
    private List questroomIds; // айдишники квестов, для которых генерировать слоты

}
