package com.escapenavigator.slot.mapper;

import com.escapenavigator.slot.model.Slot;
import com.escapenavigator.slot.model.dto.SlotDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SlotMapper {

    SlotDTO pojoToDto(Slot slot);

    Slot dtoToPojo(SlotDTO slotDTO);

}
