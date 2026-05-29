package br.com.orbitank.service;

import br.com.orbitank.dto.SensorReadingDTO;
import br.com.orbitank.entity.SensorReading;
import br.com.orbitank.repository.SensorReadingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SensorReadingService {

    private final SensorReadingRepository repository;

    public List<SensorReadingDTO> findAll() {

        return repository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public SensorReadingDTO findById(Long id) {

        SensorReading entity = repository.findById(id)
                .orElseThrow();

        return toDTO(entity);
    }

    public SensorReadingDTO create(SensorReadingDTO dto) {

        SensorReading entity = toEntity(dto);

        return toDTO(repository.save(entity));
    }

    public SensorReadingDTO update(Long id, SensorReadingDTO dto) {

        SensorReading entity = repository.findById(id)
                .orElseThrow();

        entity.setReadingValue(dto.getReadingValue());
        entity.setTimestamp(dto.getTimestamp());

        return toDTO(repository.save(entity));
    }

    public void delete(Long id) {

        repository.deleteById(id);
    }

    private SensorReadingDTO toDTO(SensorReading entity) {

        SensorReadingDTO dto = new SensorReadingDTO();

        dto.setId(entity.getId());
        dto.setReadingValue(entity.getReadingValue());
        dto.setTimestamp(entity.getTimestamp());

        return dto;
    }

    private SensorReading toEntity(SensorReadingDTO dto) {

        SensorReading entity = new SensorReading();

        entity.setId(dto.getId());
        entity.setReadingValue(dto.getReadingValue());
        entity.setTimestamp(dto.getTimestamp());

        return entity;
    }
}