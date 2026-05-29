package br.com.orbitank.controller;

import br.com.orbitank.dto.OperationalUserDTO;
import br.com.orbitank.service.OperationalUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/operational-users")
@RequiredArgsConstructor
public class OperationalUserController {

    private final OperationalUserService service;

    @GetMapping
    public ResponseEntity<List<OperationalUserDTO>> findAll() {

        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OperationalUserDTO> findById(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<OperationalUserDTO> create(
            @RequestBody @Valid OperationalUserDTO dto
    ) {

        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OperationalUserDTO> update(
            @PathVariable Long id,
            @RequestBody @Valid OperationalUserDTO dto
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