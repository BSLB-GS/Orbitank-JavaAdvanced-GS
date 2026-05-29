package br.com.orbitank.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SensorDTO {

    private Long id;

    private String identifier;

    private String location;

    private String sensorType;

    private String status;

    private Long lunarStationId;
}