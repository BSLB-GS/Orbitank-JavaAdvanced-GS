package br.com.orbitank.controller;

import br.com.orbitank.entity.MiningRobot;
import br.com.orbitank.repository.MiningRobotRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mining-robots")
@RequiredArgsConstructor

public class MiningRobotController {

    private final MiningRobotRepository repository;

    @GetMapping
    public List<MiningRobot> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MiningRobot> findById(
            @PathVariable Long id
    ) {

        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public MiningRobot create(
            @RequestBody @Valid MiningRobot miningRobot
    ) {

        return repository.save(miningRobot);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MiningRobot> update(
            @PathVariable Long id,
            @RequestBody @Valid MiningRobot miningRobot
    ) {

        return repository.findById(id)
                .map(existing -> {

                    existing.setIdentification(
                            miningRobot.getIdentification()
                    );

                    existing.setCurrentVolume(
                            miningRobot.getCurrentVolume()
                    );

                    existing.setCargoCapacity(
                            miningRobot.getCargoCapacity()
                    );

                    existing.setCurrentIceCargo(
                            miningRobot.getCurrentIceCargo()
                    );

                    existing.setBatteryLevel(
                            miningRobot.getBatteryLevel()
                    );

                    existing.setStatus(
                            miningRobot.getStatus()
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