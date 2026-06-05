package br.com.orbitank.dto.Request;

import lombok.Builder;

@Builder
public record IotTelemetryRequest(
        String deviceId,
        String stationCode,
        Long timestamp,
        Double iceLevelPercent,
        Double waterLevelPercent,
        Double hydrogenLevelPercent,
        Double oxygenLevelPercent,
        Double energyLevelPercent,
        Double temperatureCelsius,
        Double humidityPercent,
        Boolean electrolysisActive,
        Boolean electrolysisBlocked,
        String blockReason,
        Double hydrogenGeneratedPercent,
        Double oxygenGeneratedPercent,
        Boolean emergencyMode,
        String moduleStatus,
        String riskLevel,
        Boolean alertActive,
        String alertType,
        String alertMessage,
        String alertSeverity
) {}