package br.com.orbitank.controller;

import br.com.orbitank.dto.ResourceTankDTO;
import br.com.orbitank.service.ResourceTankService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resource-tanks")
@RequiredArgsConstructor
public class ResourceTankController {

    private final ResourceTankService service;

    @GetMapping
    public ResponseEntity<List<ResourceTankDTO>> findAll() {

        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResourceTankDTO> findById(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<ResourceTankDTO> create(
            @RequestBody @Valid ResourceTankDTO dto
    ) {

        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResourceTankDTO> update(
            @PathVariable Long id,
            @RequestBody @Valid ResourceTankDTO dto
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