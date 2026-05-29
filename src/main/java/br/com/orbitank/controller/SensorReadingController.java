package br.com.orbitank.controller;

import br.com.orbitank.dto.SensorReadingDTO;
import br.com.orbitank.service.SensorReadingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sensor-readings")
@RequiredArgsConstructor
public class SensorReadingController {

    private final SensorReadingService service;

    @GetMapping
    public List<SensorReadingDTO> findAll() {

        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SensorReadingDTO> findById(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                service.findById(id)
        );
    }

    @PostMapping
    public ResponseEntity<SensorReadingDTO> create(
            @RequestBody @Valid SensorReadingDTO dto
    ) {

        return ResponseEntity.ok(
                service.create(dto)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<SensorReadingDTO> update(
            @PathVariable Long id,
            @RequestBody @Valid SensorReadingDTO dto
    ) {

        return ResponseEntity.ok(
                service.update(id, dto)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id
    ) {

        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}