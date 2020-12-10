package com.escapenavigator.slot.model.dto;

import com.escapenavigator.slot.model.Holiday;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
public class HolidayDTO extends Holiday implements Serializable {
}
