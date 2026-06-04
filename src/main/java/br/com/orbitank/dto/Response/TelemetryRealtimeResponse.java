package br.com.orbitank.dto.Response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TelemetryRealtimeResponse {
    private Long stationCode;
    private Double iceLevelPercent;
    private Double waterLevelPercent;
    private Double hydrogenLevelPercent;
    private Double oxygenLevelPercent;
    private Double energyLevelPercent;
    private Double temperatureCelsius;
    private Double humidityPercent;
    private String moduleStatus;
    private String riskLevel;
    private Boolean alertActive;
    private String alertType;
    private String alertMessage;
    private String alertSeverity;
}