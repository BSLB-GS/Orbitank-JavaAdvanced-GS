package br.com.orbitank.service;

import br.com.orbitank.dto.Request.ResourceTankRequest;
import br.com.orbitank.dto.Response.ResourceTankResponse;
import br.com.orbitank.entity.LunarStation;
import br.com.orbitank.entity.ResourceTank;
import br.com.orbitank.repository.LunarStationRepository;
import br.com.orbitank.repository.ResourceTankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResourceTankService {

    private final ResourceTankRepository repository;
    private final LunarStationRepository lunarStationRepository;

    public List<ResourceTankResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public ResourceTankResponse findById(Long id) {
        ResourceTank entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tanque não encontrado"));

        return toResponse(entity);
    }

    public ResourceTankResponse create(ResourceTankRequest request) {
        return toResponse(repository.save(toEntity(request)));
    }

    public ResourceTankResponse update(Long id, ResourceTankRequest request) {
        ResourceTank entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tanque não encontrado"));

        LunarStation station = lunarStationRepository.findById(request.getLunarStationId())
                .orElseThrow(() -> new RuntimeException("Estação Lunar não encontrada com o ID: " + request.getLunarStationId()));

        entity.setLunarStation(station);
        entity.setMaxCapacity(request.getMaxCapacity());
        entity.setCurrentVolume(request.getCurrentVolume());
        entity.setCurrentPressure(request.getCurrentPressure());
        entity.setCurrentTemperature(request.getCurrentTemperature());
        entity.setResourceType(request.getResourceType());

        return toResponse(repository.save(entity));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private ResourceTankResponse toResponse(ResourceTank entity) {
        return ResourceTankResponse.builder()
                .id(entity.getId())
                .lunarStationId(entity.getLunarStation().getId())
                .maxCapacity(entity.getMaxCapacity())
                .currentVolume(entity.getCurrentVolume())
                .currentPressure(entity.getCurrentPressure())
                .currentTemperature(entity.getCurrentTemperature())
                .resourceType(entity.getResourceType())
                .build();
    }

    private ResourceTank toEntity(ResourceTankRequest request) {
        LunarStation station = lunarStationRepository.findById(request.getLunarStationId())
                .orElseThrow(() -> new RuntimeException("Estação Lunar não encontrada com o ID: " + request.getLunarStationId()));

        return ResourceTank.builder()
                .lunarStation(station)
                .maxCapacity(request.getMaxCapacity())
                .currentVolume(request.getCurrentVolume())
                .currentPressure(request.getCurrentPressure())
                .currentTemperature(request.getCurrentTemperature())
                .resourceType(request.getResourceType())
                .build();
    }
}