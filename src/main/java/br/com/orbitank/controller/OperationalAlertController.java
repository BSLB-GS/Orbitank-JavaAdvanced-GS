package br.com.orbitank.controller;

import br.com.orbitank.dto.OperationalAlertDTO;
import br.com.orbitank.service.OperationalAlertService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/operational-alerts")
@RequiredArgsConstructor
public class OperationalAlertController {

    private final OperationalAlertService service;

    @GetMapping
    public ResponseEntity<List<OperationalAlertDTO>> findAll() {

        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OperationalAlertDTO> findById(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<OperationalAlertDTO> create(
            @RequestBody @Valid OperationalAlertDTO dto
    ) {

        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OperationalAlertDTO> update(
            @PathVariable Long id,
            @RequestBody @Valid OperationalAlertDTO dto
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