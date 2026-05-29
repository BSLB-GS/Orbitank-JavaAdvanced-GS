package br.com.orbitank.controller;

import br.com.orbitank.dto.SpaceMissionDTO;
import br.com.orbitank.service.SpaceMissionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/missions")
@RequiredArgsConstructor
public class SpaceMissionController {

    private final SpaceMissionService service;

    @GetMapping
    public ResponseEntity<List<SpaceMissionDTO>> findAll() {

        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpaceMissionDTO> findById(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<SpaceMissionDTO> create(
            @RequestBody @Valid SpaceMissionDTO dto
    ) {

        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SpaceMissionDTO> update(
            @PathVariable Long id,
            @RequestBody @Valid SpaceMissionDTO dto
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