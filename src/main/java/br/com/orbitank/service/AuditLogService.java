package br.com.orbitank.service;

import br.com.orbitank.dto.AuditLogDTO;
import br.com.orbitank.entity.AuditLog;
import br.com.orbitank.repository.AuditLogRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuditLogService {

    private final AuditLogRepository repository;

    public AuditLogService(AuditLogRepository repository) {
        this.repository = repository;
    }

    public List<AuditLogDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(log -> {
                    AuditLogDTO dto = new AuditLogDTO();
                    dto.setId(log.getId());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public AuditLogDTO findById(Long id) {
        AuditLog log = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Log de auditoria não encontrado com o ID: " + id));

        AuditLogDTO dto = new AuditLogDTO();
        dto.setId(log.getId());
        return dto;
    }

    public AuditLogDTO create(AuditLogDTO dto) {
        AuditLog log = new AuditLog();
        AuditLog savedLog = repository.save(log);

        AuditLogDTO resultDto = new AuditLogDTO();
        resultDto.setId(savedLog.getId());
        return resultDto;
    }

    public AuditLogDTO update(Long id, AuditLogDTO dto) {
        AuditLog log = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Log de auditoria não encontrado com o ID: " + id));

        AuditLog updatedLog = repository.save(log);

        AuditLogDTO resultDto = new AuditLogDTO();
        resultDto.setId(updatedLog.getId());
        return resultDto;
    }

    public void delete(Long id) {
        AuditLog log = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Log de auditoria não encontrado com o ID: " + id));
        repository.delete(log);
    }
}