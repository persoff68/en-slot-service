package com.escapenavigator.slot.model.dto;

import com.escapenavigator.slot.model.Tariff;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
public class TariffDTO extends Tariff implements Serializable {
}
