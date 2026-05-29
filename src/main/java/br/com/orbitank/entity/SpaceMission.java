package br.com.orbitank.entity;

import br.com.orbitank.enums.MissionStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "tb_space_mission")

public class SpaceMission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O código da missão é obrigatório")
    @Pattern(
            regexp = "^[A-Z0-9-]+$",
            message = "O código da missão deve conter apenas letras maiúsculas, números e hifens"
    )
    @Column(nullable = false, unique = true)
    private String missionCode;

    @NotBlank(message = "O destino é obrigatório")
    @Size(max = 100, message = "O destino deve ter no máximo 100 caracteres")
    @Column(nullable = false, length = 100)
    private String destination;

    @NotNull(message = "A data prevista de lançamento é obrigatória")
    @Future(message = "A data de lançamento deve estar no futuro")
    @Column(nullable = false)
    private LocalDateTime scheduledLaunchDate;

    @NotNull(message = "O status da missão é obrigatório")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MissionStatus status;
}