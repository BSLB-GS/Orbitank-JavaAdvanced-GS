package br.com.orbitank.service;

import br.com.orbitank.dto.SensorDTO;
import br.com.orbitank.entity.Sensor;
import br.com.orbitank.enums.SensorStatus;
import br.com.orbitank.enums.SensorType;
import br.com.orbitank.repository.SensorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SensorService {

    private final SensorRepository repository;

    public List<SensorDTO> findAll() {

        return repository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public SensorDTO findById(Long id) {

        Sensor sensor = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Sensor não encontrado")
                );

        return toDTO(sensor);
    }

    public SensorDTO create(SensorDTO dto) {

        Sensor sensor = toEntity(dto);

        return toDTO(repository.save(sensor));
    }

    public SensorDTO update(Long id, SensorDTO dto) {

        Sensor sensor = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Sensor não encontrado")
                );

        sensor.setIdentifier(dto.getIdentifier());

        sensor.setLocation(dto.getLocation());

        sensor.setStatus(
                SensorStatus.valueOf(dto.getStatus())
        );

        sensor.setSensorType(
                SensorType.valueOf(dto.getSensorType())
        );

        return toDTO(repository.save(sensor));
    }

    public void delete(Long id) {

        if (!repository.existsById(id)) {
            throw new RuntimeException("Sensor não encontrado");
        }

        repository.deleteById(id);
    }

    private SensorDTO toDTO(Sensor sensor) {

        return SensorDTO.builder()
                .id(sensor.getId())
                .identifier(sensor.getIdentifier())
                .location(sensor.getLocation())
                .status(sensor.getStatus().name())
                .sensorType(sensor.getSensorType().name())
                .build();
    }

    private Sensor toEntity(SensorDTO dto) {

        return Sensor.builder()
                .id(dto.getId())
                .identifier(dto.getIdentifier())
                .location(dto.getLocation())
                .status(
                        SensorStatus.valueOf(dto.getStatus())
                )
                .sensorType(
                        SensorType.valueOf(dto.getSensorType())
                )
                .build();
    }
}