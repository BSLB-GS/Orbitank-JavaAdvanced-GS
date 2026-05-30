package br.com.orbitank.controller;

import br.com.orbitank.dto.Request.LunarStationRequest;
import br.com.orbitank.dto.Response.LunarStationResponse;
import br.com.orbitank.service.LunarStationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stations")
@RequiredArgsConstructor
public class LunarStationController {

    private final LunarStationService service;

    @GetMapping
    public ResponseEntity<List<LunarStationResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LunarStationResponse> findById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<LunarStationResponse> create(
            @RequestBody @Valid LunarStationRequest request
    ) {
        return ResponseEntity.ok(service.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LunarStationResponse> update(
            @PathVariable Long id,
            @RequestBody @Valid LunarStationRequest request
    ) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id
    ) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}