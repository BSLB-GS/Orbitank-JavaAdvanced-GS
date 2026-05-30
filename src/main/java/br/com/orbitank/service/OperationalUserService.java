package br.com.orbitank.service;

import br.com.orbitank.dto.Request.OperationalUserRequest;
import br.com.orbitank.dto.Response.OperationalUserResponse;
import br.com.orbitank.entity.OperationalUser;
import br.com.orbitank.repository.OperationalUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OperationalUserService {

    private final OperationalUserRepository repository;

    public List<OperationalUserResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public OperationalUserResponse findById(Long id) {
        OperationalUser user = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Usuário operacional não encontrado com o ID: " + id));

        return toResponse(user);
    }

    public OperationalUserResponse create(OperationalUserRequest request) {
        OperationalUser user = toEntity(request);
        return toResponse(repository.save(user));
    }

    public OperationalUserResponse update(Long id, OperationalUserRequest request) {
        OperationalUser user = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Usuário operacional não encontrado com o ID: " + id));

        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPasswordHash(request.getPasswordHash());
        user.setRole(request.getRole());
        user.setStatus(request.getStatus());
        user.setCreatedAt(request.getCreatedAt());
        user.setLastLoginAt(request.getLastLoginAt());

        OperationalUser updatedUser = repository.save(user);

        return toResponse(updatedUser);
    }

    public void delete(Long id) {
        OperationalUser user = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Usuário operacional não encontrado com o ID: " + id));
        repository.delete(user);
    }

    private OperationalUserResponse toResponse(OperationalUser entity) {
        return OperationalUserResponse.builder()
                .id(entity.getId())
                .fullName(entity.getFullName())
                .email(entity.getEmail())
                .passwordHash(entity.getPasswordHash())
                .role(entity.getRole())
                .status(entity.getStatus())
                .createdAt(entity.getCreatedAt())
                .lastLoginAt(entity.getLastLoginAt())
                .build();
    }

    private OperationalUser toEntity(OperationalUserRequest request) {
        return OperationalUser.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .passwordHash(request.getPasswordHash())
                .role(request.getRole())
                .status(request.getStatus())
                .createdAt(request.getCreatedAt())
                .lastLoginAt(request.getLastLoginAt())
                .build();
    }
}