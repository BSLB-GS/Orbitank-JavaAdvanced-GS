package br.com.orbitank.service;

import br.com.orbitank.dto.OperationalAlertDTO;
import br.com.orbitank.entity.OperationalAlert;
import br.com.orbitank.repository.OperationalAlertRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OperationalAlertService {

    private final OperationalAlertRepository repository;

    public List<OperationalAlertDTO> findAll() {

        return repository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public OperationalAlertDTO findById(Long id) {

        OperationalAlert entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alerta não encontrado"));

        return toDTO(entity);
    }

    public OperationalAlertDTO create(OperationalAlertDTO dto) {

        return toDTO(repository.save(toEntity(dto)));
    }

    public OperationalAlertDTO update(Long id, OperationalAlertDTO dto) {

        OperationalAlert entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alerta não encontrado"));

        entity.setSource(dto.getSource());
        entity.setMessage(dto.getMessage());
        entity.setActive(dto.getActive());

        return toDTO(repository.save(entity));
    }

    public void delete(Long id) {

        repository.deleteById(id);
    }

    private OperationalAlertDTO toDTO(OperationalAlert entity) {

        return OperationalAlertDTO.builder()
                .id(entity.getId())
                .source(entity.getSource())
                .message(entity.getMessage())
                .active(entity.getActive())
                .severity(entity.getSeverity().name())
                .build();
    }

    private OperationalAlert toEntity(OperationalAlertDTO dto) {

        return OperationalAlert.builder()
                .id(dto.getId())
                .source(dto.getSource())
                .message(dto.getMessage())
                .active(dto.getActive())
                .build();
    }
}