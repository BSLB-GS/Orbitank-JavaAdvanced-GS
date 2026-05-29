package br.com.orbitank.service;

import br.com.orbitank.dto.StationConfigurationDTO;
import br.com.orbitank.entity.StationConfiguration;
import br.com.orbitank.repository.StationConfigurationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StationConfigurationService {

    private final StationConfigurationRepository repository;

    public StationConfigurationService(StationConfigurationRepository repository) {
        this.repository = repository;
    }

    public List<StationConfigurationDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(config -> {
                    StationConfigurationDTO dto = new StationConfigurationDTO();
                    dto.setId(config.getId());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public StationConfigurationDTO findById(Long id) {
        StationConfiguration config = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Configuração não encontrada com o ID: " + id));

        StationConfigurationDTO dto = new StationConfigurationDTO();
        dto.setId(config.getId());
        return dto;
    }

    public StationConfigurationDTO create(StationConfigurationDTO dto) {
        StationConfiguration config = new StationConfiguration();
        StationConfiguration savedConfig = repository.save(config);

        StationConfigurationDTO resultDto = new StationConfigurationDTO();
        resultDto.setId(savedConfig.getId());
        return resultDto;
    }

    public StationConfigurationDTO update(Long id, StationConfigurationDTO dto) {
        StationConfiguration config = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Configuração não encontrada com o ID: " + id));

        StationConfiguration updatedConfig = repository.save(config);

        StationConfigurationDTO resultDto = new StationConfigurationDTO();
        resultDto.setId(updatedConfig.getId());
        return resultDto;
    }

    public void delete(Long id) {
        StationConfiguration config = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Configuração não encontrada com o ID: " + id));
        repository.delete(config);
    }
}