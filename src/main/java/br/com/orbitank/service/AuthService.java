package br.com.orbitank.service;

import br.com.orbitank.dto.Request.LoginRequest;
import br.com.orbitank.dto.Response.LoginResponse;
import br.com.orbitank.entity.OperationalUser;
import br.com.orbitank.repository.OperationalUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final OperationalUserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public LoginResponse login(
            LoginRequest request
    ) {

        OperationalUser user = repository
                .findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("Usuário não encontrado")
                );

        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            throw new RuntimeException("Senha inválida");
        }

        return LoginResponse.builder()
                .userId(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .role(user.getRole().name())
                .status(user.getStatus().name())
                .build();
    }
}