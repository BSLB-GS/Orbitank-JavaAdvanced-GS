package br.com.orbitank.service;

import br.com.orbitank.dto.Request.AuditLogRequest;
import br.com.orbitank.dto.Response.AuditLogResponse;
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

    public List<AuditLogResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    public AuditLogResponse findById(Long id) {
        AuditLog log = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Log de auditoria não encontrado com o ID: " + id));

        return convertToResponse(log);
    }

    public AuditLogResponse create(AuditLogRequest request) {
        AuditLog log = new AuditLog();

        log.setUser(request.getUser());
        log.setAction(request.getAction());
        log.setDescription(request.getDescription());
        log.setCreatedAt(request.getCreatedAt());

        AuditLog savedLog = repository.save(log);

        return convertToResponse(savedLog);
    }

    public AuditLogResponse update(Long id, AuditLogRequest request) {
        AuditLog log = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Log de auditoria não encontrado com o ID: " + id));

        log.setUser(request.getUser());
        log.setAction(request.getAction());
        log.setDescription(request.getDescription());
        log.setCreatedAt(request.getCreatedAt());

        AuditLog updatedLog = repository.save(log);

        return convertToResponse(updatedLog);
    }

    public void delete(Long id) {
        AuditLog log = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Log de auditoria não encontrado com o ID: " + id));
        repository.delete(log);
    }

    private AuditLogResponse convertToResponse(AuditLog log) {
        return AuditLogResponse.builder()
                .id(log.getId())
                .user(log.getUser())
                .action(log.getAction())
                .description(log.getDescription())
                .createdAt(log.getCreatedAt())
                .build();
    }
}