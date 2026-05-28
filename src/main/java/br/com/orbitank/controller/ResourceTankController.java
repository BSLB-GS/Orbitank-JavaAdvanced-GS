package br.com.orbitank.controller;

import br.com.orbitank.entity.ResourceTank;
import br.com.orbitank.repository.ResourceTankRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resource-tanks")
@RequiredArgsConstructor

public class ResourceTankController {

    private final ResourceTankRepository repository;

    @GetMapping
    public List<ResourceTank> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResourceTank> findById(@PathVariable Long id) {

        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResourceTank create(
            @RequestBody @Valid ResourceTank resourceTank
    ) {

        return repository.save(resourceTank);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResourceTank> update(
            @PathVariable Long id,
            @RequestBody @Valid ResourceTank resourceTank
    ) {

        return repository.findById(id)
                .map(existing -> {

                    existing.setMaxCapacity(
                            resourceTank.getMaxCapacity()
                    );

                    existing.setCurrentVolume(
                            resourceTank.getCurrentVolume()
                    );

                    existing.setCurrentPressure(
                            resourceTank.getCurrentPressure()
                    );

                    existing.setCurrentTemperature(
                            resourceTank.getCurrentTemperature()
                    );

                    existing.setResourceType(
                            resourceTank.getResourceType()
                    );

                    return ResponseEntity.ok(
                            repository.save(existing)
                    );
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}