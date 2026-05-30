package br.com.orbitank.service;

import br.com.orbitank.dto.Request.SupplyRequestRequest;
import br.com.orbitank.dto.Response.SupplyRequestResponse;
import br.com.orbitank.entity.SpaceMission;
import br.com.orbitank.entity.SupplyRequest;
import br.com.orbitank.repository.SpaceMissionRepository;
import br.com.orbitank.repository.SupplyRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SupplyRequestService {

    private final SupplyRequestRepository repository;
    private final SpaceMissionRepository spaceMissionRepository;

    public List<SupplyRequestResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public SupplyRequestResponse findById(Long id) {
        SupplyRequest request = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Solicitação não encontrada com o ID: " + id));

        return toResponse(request);
    }

    public SupplyRequestResponse create(SupplyRequestRequest requestDto) {
        SupplyRequest request = toEntity(requestDto);
        return toResponse(repository.save(request));
    }

    public SupplyRequestResponse update(Long id, SupplyRequestRequest requestDto) {
        SupplyRequest request = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Solicitação não encontrada com o ID: " + id));

        SpaceMission mission = spaceMissionRepository.findById(requestDto.getMissionId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Missão não encontrada com o ID: " + requestDto.getMissionId()));

        request.setMission(mission);
        request.setRequestedWaterVolume(requestDto.getRequestedWaterVolume());
        request.setRequestedH2Volume(requestDto.getRequestedH2Volume());
        request.setRequestedO2Volume(requestDto.getRequestedO2Volume());
        request.setRequestDate(requestDto.getRequestDate());
        request.setDenialReason(requestDto.getDenialReason());
        request.setStatus(requestDto.getStatus());

        SupplyRequest updatedRequest = repository.save(request);
        return toResponse(updatedRequest);
    }

    public void delete(Long id) {
        SupplyRequest request = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Solicitação não encontrada com o ID: " + id));
        repository.delete(request);
    }

    public SupplyRequestResponse analyzeRequest(Long id) {
        SupplyRequest request = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Solicitação não encontrada para análise com o ID: " + id));

        SupplyRequest requestSalvo = repository.save(request);
        return toResponse(requestSalvo);
    }

    private SupplyRequestResponse toResponse(SupplyRequest entity) {
        return SupplyRequestResponse.builder()
                .id(entity.getId())
                .mission(entity.getMission())
                .requestedWaterVolume(entity.getRequestedWaterVolume())
                .requestedH2Volume(entity.getRequestedH2Volume())
                .requestedO2Volume(entity.getRequestedO2Volume())
                .requestDate(entity.getRequestDate())
                .denialReason(entity.getDenialReason())
                .status(entity.getStatus())
                .build();
    }

    private SupplyRequest toEntity(SupplyRequestRequest requestDto) {
        SpaceMission mission = spaceMissionRepository.findById(requestDto.getMissionId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Missão não encontrada com o ID: " + requestDto.getMissionId()));

        return SupplyRequest.builder()
                .mission(mission)
                .requestedWaterVolume(requestDto.getRequestedWaterVolume())
                .requestedH2Volume(requestDto.getRequestedH2Volume())
                .requestedO2Volume(requestDto.getRequestedO2Volume())
                .requestDate(requestDto.getRequestDate())
                .denialReason(requestDto.getDenialReason())
                .status(requestDto.getStatus())
                .build();
    }
}