package br.com.orbitank.controller;

import br.com.orbitank.entity.OperationalAlert;
import br.com.orbitank.repository.OperationalAlertRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/operational-alerts")
@RequiredArgsConstructor

public class OperationalAlertController {

    private final OperationalAlertRepository repository;

    @GetMapping
    public List<OperationalAlert> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OperationalAlert> findById(
            @PathVariable Long id
    ) {

        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public OperationalAlert create(
            @RequestBody @Valid OperationalAlert operationalAlert
    ) {

        return repository.save(operationalAlert);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OperationalAlert> update(
            @PathVariable Long id,
            @RequestBody @Valid OperationalAlert operationalAlert
    ) {

        return repository.findById(id)
                .map(existing -> {

                    existing.setSource(
                            operationalAlert.getSource()
                    );

                    existing.setMessage(
                            operationalAlert.getMessage()
                    );

                    existing.setActive(
                            operationalAlert.getActive()
                    );

                    existing.setTriggeredAt(
                            operationalAlert.getTriggeredAt()
                    );

                    existing.setResolvedAt(
                            operationalAlert.getResolvedAt()
                    );

                    existing.setSeverity(
                            operationalAlert.getSeverity()
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