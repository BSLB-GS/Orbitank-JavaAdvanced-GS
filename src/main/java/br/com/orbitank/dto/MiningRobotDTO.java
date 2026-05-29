package br.com.orbitank.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MiningRobotDTO {

    private Long id;

    private String identification;

    private Double currentVolume;

    private Double cargoCapacity;

    private Double currentIceCargo;

    private Double batteryLevel;

    private String status;

    private Long lunarStationId;
}