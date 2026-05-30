package br.com.orbitank.service;

import br.com.orbitank.dto.Request.SensorReadingRequest;
import br.com.orbitank.dto.Response.SensorReadingResponse;
import br.com.orbitank.entity.SensorReading;
import br.com.orbitank.repository.SensorReadingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SensorReadingService {

    private final SensorReadingRepository repository;

    public List<SensorReadingResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public SensorReadingResponse findById(Long id) {
        SensorReading entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Leitura do sensor não encontrada"));

        return toResponse(entity);
    }

    public SensorReadingResponse create(SensorReadingRequest request) {
        SensorReading entity = toEntity(request);
        return toResponse(repository.save(entity));
    }

    public SensorReadingResponse update(Long id, SensorReadingRequest request) {
        SensorReading entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Leitura do sensor não encontrada"));

        entity.setSensor(request.getSensor());
        entity.setReadingValue(request.getReadingValue());
        entity.setTimestamp(request.getTimestamp());

        return toResponse(repository.save(entity));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private SensorReadingResponse toResponse(SensorReading entity) {
        return SensorReadingResponse.builder()
                .id(entity.getId())
                .sensor(entity.getSensor())
                .readingValue(entity.getReadingValue())
                .timestamp(entity.getTimestamp())
                .build();
    }

    private SensorReading toEntity(SensorReadingRequest request) {
        return SensorReading.builder()
                .sensor(request.getSensor())
                .readingValue(request.getReadingValue())
                .timestamp(request.getTimestamp())
                .build();
    }
}