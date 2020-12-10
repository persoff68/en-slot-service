package com.escapenavigator.slot.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.UUID;
import java.util.List;

/**
 * При создании выходных все слоты на указанные квесты и выпадающие указанную дату 
 * получают параметр holiday: holiday.id
 * 
 * При удалении выходных у всех слотов c holiday.id ставим holiday: null
 * 
 * С целью уменьшения объема работ выходные редактировать нельзя, только создать или удалить
 */

@Data
@Document(collection = "holiday")
public class Holiday {

    @Id
    private UUID id = UUID.randomUUID();
    private String title; // причина перерыва
    private String profileId; 
    private List questroomIds; // массив квестов, на которые распространяется выходной
    private Date date; // дата начала выходных
    private Date dateEnd; // дата конца выходных включительно

}
