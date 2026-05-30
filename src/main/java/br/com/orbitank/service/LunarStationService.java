package br.com.orbitank.service;

import br.com.orbitank.dto.Request.LunarStationRequest;
import br.com.orbitank.dto.Response.LunarStationResponse;
import br.com.orbitank.entity.LunarStation;
import br.com.orbitank.repository.LunarStationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LunarStationService {

    private final LunarStationRepository repository;

    public List<LunarStationResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public LunarStationResponse findById(Long id) {
        LunarStation entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estação não encontrada com o ID: " + id));

        return toResponse(entity);
    }

    public LunarStationResponse findByStationCode(Long stationCode) {
        LunarStation entity = repository.findByStationCode(stationCode)
                .orElseThrow(() -> new RuntimeException("Estação não encontrada com o código: " + stationCode));

        return toResponse(entity);
    }

    public LunarStationResponse create(LunarStationRequest request) {
        LunarStation entity = toEntity(request);
        return toResponse(repository.save(entity));
    }

    public LunarStationResponse update(Long id, LunarStationRequest request) {
        LunarStation entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estação não encontrada com o ID: " + id));

        entity.setStationCode(request.getStationCode());
        entity.setName(request.getName());
        entity.setLocation(request.getLocation());
        entity.setStatus(request.getStatus());

        return toResponse(repository.save(entity));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private LunarStationResponse toResponse(LunarStation entity) {
        return LunarStationResponse.builder()
                .id(entity.getId())
                .stationCode(entity.getStationCode())
                .name(entity.getName())
                .location(entity.getLocation())
                .status(entity.getStatus())
                .build();
    }

    private LunarStation toEntity(LunarStationRequest request) {
        return LunarStation.builder()
                .stationCode(request.getStationCode())
                .name(request.getName())
                .location(request.getLocation())
                .status(request.getStatus())
                .build();
    }
}