package br.com.orbitank.service;

import br.com.orbitank.dto.SupplyRequestDTO;
import br.com.orbitank.entity.SupplyRequest;
import br.com.orbitank.repository.SupplyRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplyRequestService {

    private final SupplyRequestRepository repository;

    public List<SupplyRequestDTO> findAll() {

        return repository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public SupplyRequestDTO findById(Long id) {

        SupplyRequest entity = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Solicitação não encontrada"));

        return toDTO(entity);
    }

    public SupplyRequestDTO create(SupplyRequestDTO dto) {

        return toDTO(repository.save(toEntity(dto)));
    }

    public SupplyRequestDTO update(Long id, SupplyRequestDTO dto) {

        SupplyRequest entity = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Solicitação não encontrada"));

        entity.setRequestedWaterVolume(
                dto.getRequestedWaterVolume()
        );

        entity.setRequestedH2Volume(
                dto.getRequestedH2Volume()
        );

        entity.setRequestedO2Volume(
                dto.getRequestedO2Volume()
        );

        entity.setDenialReason(
                dto.getDenialReason()
        );

        return toDTO(repository.save(entity));
    }

    public void delete(Long id) {

        repository.deleteById(id);
    }

    private SupplyRequestDTO toDTO(SupplyRequest entity) {

        return SupplyRequestDTO.builder()
                .id(entity.getId())
                .requestedWaterVolume(
                        entity.getRequestedWaterVolume()
                )
                .requestedH2Volume(
                        entity.getRequestedH2Volume()
                )
                .requestedO2Volume(
                        entity.getRequestedO2Volume()
                )
                .denialReason(
                        entity.getDenialReason()
                )
                .status(entity.getStatus().name())
                .build();
    }

    private SupplyRequest toEntity(SupplyRequestDTO dto) {

        return SupplyRequest.builder()
                .id(dto.getId())
                .requestedWaterVolume(
                        dto.getRequestedWaterVolume()
                )
                .requestedH2Volume(
                        dto.getRequestedH2Volume()
                )
                .requestedO2Volume(
                        dto.getRequestedO2Volume()
                )
                .denialReason(
                        dto.getDenialReason()
                )
                .build();
    }
}