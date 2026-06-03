package br.com.orbitank.service;

import br.com.orbitank.dto.Request.LoginRequest;
import br.com.orbitank.dto.Response.LoginResponse;
import br.com.orbitank.entity.OperationalUser;
import br.com.orbitank.repository.OperationalUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final OperationalUserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final JavaMailSender mailSender;

    public LoginResponse login(LoginRequest request) {

        OperationalUser user = repository
                .findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            throw new RuntimeException("Senha inválida");
        }

        String token = jwtService.generateToken(user);

        return LoginResponse.builder()
                .userId(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .role(user.getRole().name())
                .status(user.getStatus().name())
                .token(token)
                .build();
    }

    public void forgotPassword(String email) {
        OperationalUser user = repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        String code = String.format("%06d", new Random().nextInt(999999));

        user.setResetCode(code);
        user.setResetCodeExpiresAt(LocalDateTime.now().plusMinutes(15));
        repository.save(user);

        sendEmail(user.getEmail(), code);
    }

    public void verifyResetCode(String email, String code) {
        OperationalUser user = repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (user.getResetCode() == null || !user.getResetCode().equals(code)) {
            throw new RuntimeException("Código inválido");
        }

        if (user.getResetCodeExpiresAt().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Código expirado");
        }
    }

    public void resetPassword(String email, String code, String newPassword) {
        verifyResetCode(email, code);

        OperationalUser user = repository.findByEmail(email).get();

        user.setPasswordHash(passwordEncoder.encode(newPassword));
        user.setResetCode(null);
        user.setResetCodeExpiresAt(null);

        repository.save(user);
    }

    private void sendEmail(String to, String code) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Orbitank - Recuperação de Senha");
        message.setText("Seu código de recuperação é: " + code + "\n\nEle expira em 15 minutos.");
        mailSender.send(message);
    }
}