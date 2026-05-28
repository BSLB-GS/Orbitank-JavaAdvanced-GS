package br.com.orbitank.controller;

import br.com.orbitank.entity.Sensor;
import br.com.orbitank.repository.SensorRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sensors")
@RequiredArgsConstructor

public class SensorController {

    private final SensorRepository repository;

    @GetMapping
    public List<Sensor> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sensor> findById(
            @PathVariable Long id
    ) {

        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Sensor create(
            @RequestBody @Valid Sensor sensor
    ) {

        return repository.save(sensor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sensor> update(
            @PathVariable Long id,
            @RequestBody @Valid Sensor sensor
    ) {

        return repository.findById(id)
                .map(existing -> {

                    existing.setIdentifier(
                            sensor.getIdentifier()
                    );

                    existing.setLocation(
                            sensor.getLocation()
                    );

                    existing.setStatus(
                            sensor.getStatus()
                    );

                    existing.setSensorType(
                            sensor.getSensorType()
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