package com.escapenavigator.slot.domain.dto;

import com.escapenavigator.slot.domain.Slot;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
public class SlotDTO extends Slot implements Serializable {
}
