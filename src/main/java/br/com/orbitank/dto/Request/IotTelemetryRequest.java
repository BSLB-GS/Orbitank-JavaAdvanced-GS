package br.com.orbitank.dto.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IotTelemetryRequest {
    private String deviceId;
    private Long stationCode;
    private Long timestamp;
    private Double iceLevelPercent;
    private Double waterLevelPercent;
    private Double hydrogenLevelPercent;
    private Double oxygenLevelPercent;
    private Double energyLevelPercent;
    private Double temperatureCelsius;
    private Double humidityPercent;
    private Boolean electrolysisActive;
    private Boolean electrolysisBlocked;
    private String blockReason;
    private Double hydrogenGeneratedPercent;
    private Double oxygenGeneratedPercent;
    private Boolean emergencyMode;
    private String moduleStatus;
    private String riskLevel;
    private Boolean alertActive;
    private String alertType;
    private String alertMessage;
    private String alertSeverity;
}