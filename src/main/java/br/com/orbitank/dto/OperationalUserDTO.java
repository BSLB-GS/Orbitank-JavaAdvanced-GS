package br.com.orbitank.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OperationalUserDTO {

    private Long id;

    private String fullName;

    private String email;

    private String role;

    private String status;

    private LocalDateTime createdAt;

    private LocalDateTime lastLoginAt;
}