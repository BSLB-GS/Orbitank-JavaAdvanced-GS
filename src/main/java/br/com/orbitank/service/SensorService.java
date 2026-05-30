package br.com.orbitank.service;

import br.com.orbitank.dto.Request.SensorRequest;
import br.com.orbitank.dto.Response.SensorResponse;
import br.com.orbitank.entity.Sensor;
import br.com.orbitank.repository.SensorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SensorService {

    private final SensorRepository repository;

    public List<SensorResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public SensorResponse findById(Long id) {
        Sensor sensor = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sensor não encontrado"));

        return toResponse(sensor);
    }

    public SensorResponse create(SensorRequest request) {
        Sensor sensor = toEntity(request);
        return toResponse(repository.save(sensor));
    }

    public SensorResponse update(Long id, SensorRequest request) {
        Sensor sensor = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sensor não encontrado"));

        sensor.setIdentifier(request.getIdentifier());
        sensor.setLocation(request.getLocation());
        sensor.setStatus(request.getStatus());
        sensor.setSensorType(request.getSensorType());

        return toResponse(repository.save(sensor));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Sensor não encontrado");
        }
        repository.deleteById(id);
    }

    private SensorResponse toResponse(Sensor sensor) {
        return SensorResponse.builder()
                .id(sensor.getId())
                .identifier(sensor.getIdentifier())
                .location(sensor.getLocation())
                .status(sensor.getStatus())
                .sensorType(sensor.getSensorType())
                .build();
    }

    private Sensor toEntity(SensorRequest request) {
        return Sensor.builder()

                .identifier(request.getIdentifier())
                .location(request.getLocation())
                .status(request.getStatus())
                .sensorType(request.getSensorType())
                .build();
    }
}