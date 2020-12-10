package com.escapenavigator.slot.model.dto;

import com.escapenavigator.slot.model.Schedule;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
public class ScheduleDTO extends Schedule implements Serializable {
}
