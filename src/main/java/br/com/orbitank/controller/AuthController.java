package br.com.orbitank.controller;

import br.com.orbitank.dto.Request.LoginRequest;
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
    public ResponseEntity<LoginResponse> login(
            @RequestBody LoginRequest request
    ) {

        return ResponseEntity.ok(
                service.login(request)
        );
    }
}