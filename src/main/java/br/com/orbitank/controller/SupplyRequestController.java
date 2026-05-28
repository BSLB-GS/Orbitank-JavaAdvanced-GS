package br.com.orbitank.controller;

import br.com.orbitank.entity.SupplyRequest;
import br.com.orbitank.repository.SupplyRequestRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.orbitank.service.SupplyRequestService;

import java.util.List;

@RestController
@RequestMapping("/supply-requests")
@RequiredArgsConstructor

public class SupplyRequestController {

    private final SupplyRequestRepository repository;

    private final SupplyRequestService service;

    @GetMapping
    public List<SupplyRequest> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupplyRequest> findById(
            @PathVariable Long id
    ) {

        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public SupplyRequest create(
            @RequestBody @Valid SupplyRequest supplyRequest
    ) {

        return repository.save(supplyRequest);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SupplyRequest> update(
            @PathVariable Long id,
            @RequestBody @Valid SupplyRequest supplyRequest
    ) {

        return repository.findById(id)
                .map(existing -> {

                    existing.setMission(
                            supplyRequest.getMission()
                    );

                    existing.setRequestedWaterVolume(
                            supplyRequest.getRequestedWaterVolume()
                    );

                    existing.setRequestedH2Volume(
                            supplyRequest.getRequestedH2Volume()
                    );

                    existing.setRequestedO2Volume(
                            supplyRequest.getRequestedO2Volume()
                    );

                    existing.setRequestDate(
                            supplyRequest.getRequestDate()
                    );

                    existing.setDenialReason(
                            supplyRequest.getDenialReason()
                    );

                    existing.setStatus(
                            supplyRequest.getStatus()
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

    @PostMapping("/{id}/analyze")
    public ResponseEntity<SupplyRequest> analyzeRequest(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                service.analyzeRequest(id)
        );
    }
}