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
public class StationConfigurationDTO {

    private Long id;

    private Long lunarStationId;

    private Double minimumWaterLevel;

    private Double minimumHydrogenLevel;

    private Double minimumOxygenLevel;

    private Double minimumEnergyLevel;

    private Double maximumTankPressure;

    private Double maximumTemperature;

    private Double minimumRobotBattery;

    private LocalDateTime updatedAt;
}