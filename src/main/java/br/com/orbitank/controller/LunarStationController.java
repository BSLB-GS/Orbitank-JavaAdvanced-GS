package br.com.orbitank.controller;

import br.com.orbitank.dto.LunarStationDTO;
import br.com.orbitank.service.LunarStationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stations")
@RequiredArgsConstructor
public class LunarStationController {

    private final LunarStationService service;

    @GetMapping
    public ResponseEntity<List<LunarStationDTO>> findAll() {

        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LunarStationDTO> findById(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<LunarStationDTO> create(
            @RequestBody @Valid LunarStationDTO dto
    ) {

        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LunarStationDTO> update(
            @PathVariable Long id,
            @RequestBody @Valid LunarStationDTO dto
    ) {

        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id
    ) {

        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}