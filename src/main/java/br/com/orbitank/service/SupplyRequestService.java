package br.com.orbitank.service;

import br.com.orbitank.dto.SupplyRequestDTO;
import br.com.orbitank.entity.SupplyRequest;
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

    public List<SupplyRequestDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(request -> {
                    SupplyRequestDTO dto = new SupplyRequestDTO();
                    dto.setId(request.getId());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public SupplyRequestDTO findById(Long id) {
        SupplyRequest request = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Solicitação não encontrada com o ID: " + id));

        SupplyRequestDTO dto = new SupplyRequestDTO();
        dto.setId(request.getId());
        return dto;
    }

    public SupplyRequestDTO create(SupplyRequestDTO dto) {
        SupplyRequest request = new SupplyRequest();

        SupplyRequest savedRequest = repository.save(request);

        SupplyRequestDTO resultDto = new SupplyRequestDTO();
        resultDto.setId(savedRequest.getId());
        return resultDto;
    }

    public SupplyRequestDTO update(Long id, SupplyRequestDTO dto) {
        SupplyRequest request = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Solicitação não encontrada com o ID: " + id));

        SupplyRequest updatedRequest = repository.save(request);

        SupplyRequestDTO resultDto = new SupplyRequestDTO();
        resultDto.setId(updatedRequest.getId());
        return resultDto;
    }

    public void delete(Long id) {
        SupplyRequest request = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Solicitação não encontrada com o ID: " + id));
        repository.delete(request);
    }

    public SupplyRequestDTO analyzeRequest(Long id) {
        SupplyRequest request = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Solicitação não encontrada para análise com o ID: " + id));

        SupplyRequest requestSalvo = repository.save(request);


        SupplyRequestDTO resultadoDto = new SupplyRequestDTO();
        resultadoDto.setId(requestSalvo.getId());

        return resultadoDto;
    }
}