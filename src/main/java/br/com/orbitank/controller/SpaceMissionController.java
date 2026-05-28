package br.com.orbitank.controller;

import br.com.orbitank.entity.SpaceMission;
import br.com.orbitank.repository.SpaceMissionRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/missions")
@RequiredArgsConstructor

public class SpaceMissionController {

    private final SpaceMissionRepository repository;

    @GetMapping
    public List<SpaceMission> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpaceMission> findById(@PathVariable Long id) {

        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public SpaceMission create(
            @RequestBody @Valid SpaceMission mission
    ) {

        return repository.save(mission);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SpaceMission> update(
            @PathVariable Long id,
            @RequestBody @Valid SpaceMission mission
    ) {

        return repository.findById(id)
                .map(existing -> {

                    existing.setMissionCode(mission.getMissionCode());
                    existing.setDestination(mission.getDestination());
                    existing.setScheduledLaunchDate(
                            mission.getScheduledLaunchDate()
                    );
                    existing.setStatus(mission.getStatus());

                    return ResponseEntity.ok(repository.save(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        return repository.findById(id)
                .map(mission -> {

                    repository.delete(mission);

                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}