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
public class OperationalAlertDTO {

    private Long id;

    private String source;

    private String message;

    private Boolean active;

    private LocalDateTime triggeredAt;

    private LocalDateTime resolvedAt;

    private String severity;

    private Long lunarStationId;

    private Long resolvedByUserId;
}