package br.com.orbitank.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResourceTankDTO {

    private Long id;

    private Double maxCapacity;

    private Double currentVolume;

    private Double currentPressure;

    private Double currentTemperature;

    private String resourceType;

    private String status;

    private Long lunarStationId;
}