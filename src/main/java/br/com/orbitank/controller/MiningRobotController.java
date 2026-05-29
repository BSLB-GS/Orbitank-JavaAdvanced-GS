package br.com.orbitank.controller;

import br.com.orbitank.dto.MiningRobotDTO;
import br.com.orbitank.service.MiningRobotService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mining-robots")
@RequiredArgsConstructor
public class MiningRobotController {

    private final MiningRobotService service;

    @GetMapping
    public ResponseEntity<List<MiningRobotDTO>> findAll() {

        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MiningRobotDTO> findById(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<MiningRobotDTO> create(
            @RequestBody @Valid MiningRobotDTO dto
    ) {

        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MiningRobotDTO> update(
            @PathVariable Long id,
            @RequestBody @Valid MiningRobotDTO dto
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