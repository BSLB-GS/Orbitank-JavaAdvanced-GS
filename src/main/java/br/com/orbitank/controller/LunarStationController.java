package br.com.orbitank.controller;

import br.com.orbitank.entity.LunarStation;
import br.com.orbitank.repository.LunarStationRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stations")
@RequiredArgsConstructor

public class LunarStationController {

    private final LunarStationRepository repository;

    @GetMapping
    public List<LunarStation> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LunarStation> findById(@PathVariable Long id) {

        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public LunarStation create(
            @RequestBody @Valid LunarStation lunarStation
    ) {

        return repository.save(lunarStation);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LunarStation> update(
            @PathVariable Long id,
            @RequestBody @Valid LunarStation lunarStation
    ) {

        return repository.findById(id)
                .map(existing -> {

                    existing.setName(lunarStation.getName());
                    existing.setLocation(lunarStation.getLocation());
                    existing.setStatus(lunarStation.getStatus());

                    return ResponseEntity.ok(repository.save(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        return repository.findById(id)
                .map(station -> {

                    repository.delete(station);

                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}