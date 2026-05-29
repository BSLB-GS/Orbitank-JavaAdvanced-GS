package br.com.orbitank.controller;

import br.com.orbitank.dto.RefuelOrderDTO;
import br.com.orbitank.service.RefuelOrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/refuel-orders")
@RequiredArgsConstructor
public class RefuelOrderController {

    private final RefuelOrderService service;

    @GetMapping
    public ResponseEntity<List<RefuelOrderDTO>> findAll() {

        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RefuelOrderDTO> findById(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<RefuelOrderDTO> create(
            @RequestBody @Valid RefuelOrderDTO dto
    ) {

        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RefuelOrderDTO> update(
            @PathVariable Long id,
            @RequestBody @Valid RefuelOrderDTO dto
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