package com.escapenavigator.slot.model.dto;

import com.escapenavigator.slot.model.Template;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
public class TemplateDTO extends Template implements Serializable {
}
