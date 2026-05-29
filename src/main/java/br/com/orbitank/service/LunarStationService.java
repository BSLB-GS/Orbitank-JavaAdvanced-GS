package br.com.orbitank.service;

import br.com.orbitank.dto.LunarStationDTO;
import br.com.orbitank.entity.LunarStation;
import br.com.orbitank.repository.LunarStationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LunarStationService {

    private final LunarStationRepository repository;

    public List<LunarStationDTO> findAll() {

        return repository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public LunarStationDTO findById(Long id) {

        LunarStation entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estação não encontrada"));

        return toDTO(entity);
    }

    public LunarStationDTO create(LunarStationDTO dto) {

        LunarStation entity = toEntity(dto);

        return toDTO(repository.save(entity));
    }

    public LunarStationDTO update(Long id, LunarStationDTO dto) {

        LunarStation entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estação não encontrada"));

        entity.setName(dto.getName());
        entity.setLocation(dto.getLocation());

        return toDTO(repository.save(entity));
    }

    public void delete(Long id) {

        repository.deleteById(id);
    }

    private LunarStationDTO toDTO(LunarStation entity) {

        return LunarStationDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .location(entity.getLocation())
                .status(entity.getStatus().name())
                .build();
    }

    private LunarStation toEntity(LunarStationDTO dto) {

        return LunarStation.builder()
                .id(dto.getId())
                .name(dto.getName())
                .location(dto.getLocation())
                .build();
    }
}