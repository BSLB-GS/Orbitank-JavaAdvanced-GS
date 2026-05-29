package br.com.orbitank.controller;

import br.com.orbitank.dto.StationConfigurationDTO;
import br.com.orbitank.service.StationConfigurationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/station-configurations")
@RequiredArgsConstructor
public class StationConfigurationController {

    private final StationConfigurationService service;

    @GetMapping
    public ResponseEntity<List<StationConfigurationDTO>> findAll() {

        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StationConfigurationDTO> findById(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<StationConfigurationDTO> create(
            @RequestBody @Valid StationConfigurationDTO dto
    ) {

        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StationConfigurationDTO> update(
            @PathVariable Long id,
            @RequestBody @Valid StationConfigurationDTO dto
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