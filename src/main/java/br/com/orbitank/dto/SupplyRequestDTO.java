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
public class SupplyRequestDTO {

    private Long id;

    private Long missionId;

    private Double requestedWaterVolume;

    private Double requestedH2Volume;

    private Double requestedO2Volume;

    private LocalDateTime requestDate;

    private String denialReason;

    private String status;

    private Long requestedByUserId;
}