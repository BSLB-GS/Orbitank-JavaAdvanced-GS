package br.com.orbitank.controller;

import br.com.orbitank.dto.SupplyRequestDTO;
import br.com.orbitank.service.SupplyRequestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/supply-requests")
@RequiredArgsConstructor
public class SupplyRequestController {

    private final SupplyRequestService service;

    @GetMapping
    public ResponseEntity<List<SupplyRequestDTO>> findAll() {

        return ResponseEntity.ok(
                service.findAll()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupplyRequestDTO> findById(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                service.findById(id)
        );
    }

    @PostMapping
    public ResponseEntity<SupplyRequestDTO> create(
            @RequestBody @Valid SupplyRequestDTO dto
    ) {

        return ResponseEntity.ok(
                service.create(dto)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<SupplyRequestDTO> update(
            @PathVariable Long id,
            @RequestBody @Valid SupplyRequestDTO dto
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

    @PostMapping("/{id}/analyze")
    public ResponseEntity<SupplyRequestDTO> analyzeRequest(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                service.analyzeRequest(id)
        );
    }
}