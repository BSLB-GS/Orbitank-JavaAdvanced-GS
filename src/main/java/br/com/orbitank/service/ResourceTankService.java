package br.com.orbitank.service;

import br.com.orbitank.dto.ResourceTankDTO;
import br.com.orbitank.entity.ResourceTank;
import br.com.orbitank.repository.ResourceTankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResourceTankService {

    private final ResourceTankRepository repository;

    public List<ResourceTankDTO> findAll() {

        return repository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public ResourceTankDTO findById(Long id) {

        ResourceTank entity = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Tanque não encontrado"));

        return toDTO(entity);
    }

    public ResourceTankDTO create(ResourceTankDTO dto) {

        return toDTO(repository.save(toEntity(dto)));
    }

    public ResourceTankDTO update(Long id, ResourceTankDTO dto) {

        ResourceTank entity = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Tanque não encontrado"));

        entity.setMaxCapacity(dto.getMaxCapacity());

        entity.setCurrentVolume(dto.getCurrentVolume());

        entity.setCurrentPressure(dto.getCurrentPressure());

        entity.setCurrentTemperature(dto.getCurrentTemperature());

        return toDTO(repository.save(entity));
    }

    public void delete(Long id) {

        repository.deleteById(id);
    }

    private ResourceTankDTO toDTO(ResourceTank entity) {

        return ResourceTankDTO.builder()
                .id(entity.getId())
                .maxCapacity(entity.getMaxCapacity())
                .currentVolume(entity.getCurrentVolume())
                .currentPressure(entity.getCurrentPressure())
                .currentTemperature(entity.getCurrentTemperature())
                .resourceType(entity.getResourceType().name())
                .build();
    }

    private ResourceTank toEntity(ResourceTankDTO dto) {

        return ResourceTank.builder()
                .id(dto.getId())
                .maxCapacity(dto.getMaxCapacity())
                .currentVolume(dto.getCurrentVolume())
                .currentPressure(dto.getCurrentPressure())
                .currentTemperature(dto.getCurrentTemperature())
                .build();
    }
}