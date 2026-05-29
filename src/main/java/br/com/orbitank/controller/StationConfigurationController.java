package br.com.orbitank.controller;

import br.com.orbitank.dto.StationConfigurationDTO;
import br.com.orbitank.service.StationConfigurationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/station-configurations")
public class StationConfigurationController {

    private final StationConfigurationService service;

    // CONSTRUTOR MANUAL (Substitui o @RequiredArgsConstructor e resolve o erro)
    public StationConfigurationController(StationConfigurationService service) {
        this.service = service;
    }

    // 1. GET - Listar todas as configurações
    @GetMapping
    public ResponseEntity<List<StationConfigurationDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    // 2. GET - Buscar uma configuração por ID
    @GetMapping("/{id}")
    public ResponseEntity<StationConfigurationDTO> findById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(service.findById(id));
    }

    // 3. POST - Criar uma nova configuração
    @PostMapping
    public ResponseEntity<StationConfigurationDTO> create(
            @RequestBody @Valid StationConfigurationDTO dto
    ) {
        return ResponseEntity.ok(service.create(dto));
    }

    // 4. PUT - Atualizar uma configuração existente
    @PutMapping("/{id}")
    public ResponseEntity<StationConfigurationDTO> update(
            @PathVariable Long id,
            @RequestBody @Valid StationConfigurationDTO dto
    ) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    // 5. DELETE - Remover uma configuração por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id
    ) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}