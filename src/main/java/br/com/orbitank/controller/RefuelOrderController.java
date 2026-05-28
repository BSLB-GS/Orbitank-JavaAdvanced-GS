package br.com.orbitank.controller;

import br.com.orbitank.entity.RefuelOrder;
import br.com.orbitank.repository.RefuelOrderRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/refuel-orders")
@RequiredArgsConstructor

public class RefuelOrderController {

    private final RefuelOrderRepository repository;

    @GetMapping
    public List<RefuelOrder> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RefuelOrder> findById(
            @PathVariable Long id
    ) {

        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public RefuelOrder create(
            @RequestBody @Valid RefuelOrder refuelOrder
    ) {

        return repository.save(refuelOrder);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RefuelOrder> update(
            @PathVariable Long id,
            @RequestBody @Valid RefuelOrder refuelOrder
    ) {

        return repository.findById(id)
                .map(existing -> {

                    existing.setSupplyRequest(
                            refuelOrder.getSupplyRequest()
                    );

                    existing.setStartDate(
                            refuelOrder.getStartDate()
                    );

                    existing.setEndDate(
                            refuelOrder.getEndDate()
                    );

                    existing.setActualWaterTransferred(
                            refuelOrder.getActualWaterTransferred()
                    );

                    existing.setActualH2Transferred(
                            refuelOrder.getActualH2Transferred()
                    );

                    existing.setActualO2Transferred(
                            refuelOrder.getActualO2Transferred()
                    );

                    existing.setStatus(
                            refuelOrder.getStatus()
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