package com.escapenavigator.slot.model.dto;

import com.escapenavigator.slot.model.Slot;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
public class SlotDTO extends Slot implements Serializable {
}
