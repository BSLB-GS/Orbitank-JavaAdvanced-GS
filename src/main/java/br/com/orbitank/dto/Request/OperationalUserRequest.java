package br.com.orbitank.dto.Request;

import br.com.orbitank.enums.UserRole;
import br.com.orbitank.enums.UserStatus;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OperationalUserRequest {

    @NotBlank(message = "O nome do usuário é obrigatório")
    @Size(min = 3, max = 100)
    private String fullName;

    @NotBlank(message = "O e-mail é obrigatório")
    @Email
    private String email;

    @NotBlank(message = "A senha é obrigatória")
    private String passwordHash;

    @NotNull
    private UserRole role;

    @NotNull
    private UserStatus status;

    @NotNull
    @PastOrPresent
    private LocalDateTime createdAt;

    private LocalDateTime lastLoginAt;
}