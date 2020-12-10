package com.escapenavigator.slot.web.rest;

import com.escapenavigator.slot.mapper.SlotMapper;
import com.escapenavigator.slot.model.Slot;
import com.escapenavigator.slot.model.dto.SlotDTO;
import com.escapenavigator.slot.service.SlotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(SlotResource.ENDPOINT)
public class SlotResource {
    public static final String ENDPOINT = "/api/slot";

    private final SlotService slotService;
    private final SlotMapper slotMapper;

    @GetMapping("/{id}")
    public ResponseEntity<SlotDTO> get(@PathVariable UUID id) {
        Slot slot = slotService.getById(id);
        SlotDTO slotDTO = slotMapper.pojoToDto(slot);
        return ResponseEntity.ok(slotDTO);
    }

    @PostMapping
    public ResponseEntity<SlotDTO> create(@RequestBody SlotDTO slotDTO) {
        Slot slot = slotMapper.dtoToPojo(slotDTO);
        Slot newSlot = slotService.create(slot);
        SlotDTO newSlotDTO = slotMapper.pojoToDto(newSlot);
        return ResponseEntity.status(HttpStatus.CREATED).body(newSlotDTO);
    }

    @PutMapping
    public ResponseEntity<SlotDTO> update(@RequestBody SlotDTO slotDTO) {
        Slot slot = slotMapper.dtoToPojo(slotDTO);
        Slot newSlot = slotService.update(slot);
        SlotDTO newSlotDTO = slotMapper.pojoToDto(newSlot);
        return ResponseEntity.ok(newSlotDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SlotDTO> delete(@PathVariable UUID id) {
        slotService.delete(id);
        return ResponseEntity.ok().build();
    }

}
