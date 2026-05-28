package br.com.orbitank.controller;

import br.com.orbitank.entity.SensorReading;
import br.com.orbitank.repository.SensorReadingRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sensor-readings")
@RequiredArgsConstructor

public class SensorReadingController {

    private final SensorReadingRepository repository;

    @GetMapping
    public List<SensorReading> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SensorReading> findById(
            @PathVariable Long id
    ) {

        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public SensorReading create(
            @RequestBody @Valid SensorReading sensorReading
    ) {

        return repository.save(sensorReading);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SensorReading> update(
            @PathVariable Long id,
            @RequestBody @Valid SensorReading sensorReading
    ) {

        return repository.findById(id)
                .map(existing -> {

                    existing.setSensor(
                            sensorReading.getSensor()
                    );

                    existing.setReadingValue(
                            sensorReading.getReadingValue()
                    );

                    existing.setTimestamp(
                            sensorReading.getTimestamp()
                    );

                    return ResponseEntity.ok(
                            repository.save(existing)
                    );
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id
    ) {

        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}