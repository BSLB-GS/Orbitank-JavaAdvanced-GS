package br.com.orbitank.service;

import br.com.orbitank.dto.SpaceMissionDTO;
import br.com.orbitank.entity.SpaceMission;
import br.com.orbitank.enums.MissionPriority;
import br.com.orbitank.enums.MissionStatus;
import br.com.orbitank.repository.SpaceMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpaceMissionService {

    private final SpaceMissionRepository repository;

    public List<SpaceMissionDTO> findAll() {

        return repository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public SpaceMissionDTO findById(Long id) {

        SpaceMission entity = repository.findById(id)
                .orElseThrow();

        return toDTO(entity);
    }

    public SpaceMissionDTO create(SpaceMissionDTO dto) {

        SpaceMission entity = toEntity(dto);

        return toDTO(repository.save(entity));
    }

    public SpaceMissionDTO update(Long id, SpaceMissionDTO dto) {

        SpaceMission entity = repository.findById(id)
                .orElseThrow();

        entity.setMissionCode(dto.getMissionCode());
        entity.setMissionName(dto.getMissionName());
        entity.setDestination(dto.getDestination());
        entity.setScheduledLaunchDate(dto.getScheduledLaunchDate());
        entity.setStatus(MissionStatus.valueOf(dto.getStatus()));
        entity.setPriority(MissionPriority.valueOf(dto.getPriority()));

        return toDTO(repository.save(entity));
    }

    public void delete(Long id) {

        repository.deleteById(id);
    }

    private SpaceMissionDTO toDTO(SpaceMission entity) {

        SpaceMissionDTO dto = new SpaceMissionDTO();

        dto.setId(entity.getId());
        dto.setMissionCode(entity.getMissionCode());
        dto.setMissionName(entity.getMissionName());
        dto.setDestination(entity.getDestination());
        dto.setScheduledLaunchDate(entity.getScheduledLaunchDate());
        dto.setStatus(entity.getStatus().name());
        dto.setPriority(entity.getPriority().name());

        return dto;
    }

    private SpaceMission toEntity(SpaceMissionDTO dto) {

        SpaceMission entity = new SpaceMission();

        entity.setId(dto.getId());
        entity.setMissionCode(dto.getMissionCode());
        entity.setMissionName(dto.getMissionName());
        entity.setDestination(dto.getDestination());
        entity.setScheduledLaunchDate(dto.getScheduledLaunchDate());
        entity.setStatus(MissionStatus.valueOf(dto.getStatus()));
        entity.setPriority(MissionPriority.valueOf(dto.getPriority()));

        return entity;
    }
}