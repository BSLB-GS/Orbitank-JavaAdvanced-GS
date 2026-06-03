package br.com.orbitank.controller;

import br.com.orbitank.dto.Request.ForgotPasswordRequest;
import br.com.orbitank.dto.Request.LoginRequest;
import br.com.orbitank.dto.Request.ResetPasswordRequest;
import br.com.orbitank.dto.Request.VerifyCodeRequest;
import br.com.orbitank.dto.Response.LoginResponse;
import br.com.orbitank.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(service.login(request));
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<Void> forgotPassword(@RequestBody ForgotPasswordRequest request) {
        service.forgotPassword(request.getEmail());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/verify-reset-code")
    public ResponseEntity<Void> verifyResetCode(@RequestBody VerifyCodeRequest request) {
        service.verifyResetCode(request.getEmail(), request.getCode());
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/reset-password")
    public ResponseEntity<Void> resetPassword(@RequestBody ResetPasswordRequest request) {
        service.resetPassword(request.getEmail(), request.getCode(), request.getNewPassword());
        return ResponseEntity.ok().build();
    }
}