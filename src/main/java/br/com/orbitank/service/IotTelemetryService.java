package br.com.orbitank.service;

import br.com.orbitank.dto.Request.IotTelemetryRequest;
import br.com.orbitank.entity.*;
import br.com.orbitank.enums.*;
import br.com.orbitank.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class IotTelemetryService {

    private final LunarStationRepository lunarStationRepository;
    private final ResourceTankRepository tankRepository;
    private final SensorRepository sensorRepository;
    private final SensorReadingRepository sensorReadingRepository;
    private final OperationalAlertRepository alertRepository;
    private final AuditLogRepository auditLogRepository;
    private final OperationalUserRepository userRepository;

    @Transactional
    public void processTelemetry(IotTelemetryRequest request) {
        log.info("📡 Iniciando processamento de telemetria da Estação Código: {}", request.getStationCode());

        LunarStation station = lunarStationRepository.findByStationCode(request.getStationCode())
                .orElseThrow(() -> new RuntimeException("Estação não encontrada com o código: " + request.getStationCode()));

        boolean importantEventTriggered = false;
        StringBuilder auditDescription = new StringBuilder("Telemetria recebida. ");


        if (Boolean.TRUE.equals(request.getEmergencyMode())) {
            station.setStatus(StationStatus.EMERGENCY_MODE);
            importantEventTriggered = true;
            auditDescription.append("ESTADO DE EMERGÊNCIA ATIVADO. ");
        } else {
            try {
                if (request.getModuleStatus() != null) {
                    station.setStatus(StationStatus.valueOf(request.getModuleStatus()));
                }
            } catch (IllegalArgumentException e) {
                log.warn("Status de módulo desconhecido recebido: {}", request.getModuleStatus());
            }
        }
        lunarStationRepository.save(station);


        updateTanks(station.getId(), ResourceType.LUNAR_ICE, request.getIceLevelPercent());
        updateTanks(station.getId(), ResourceType.LIQUID_WATER, request.getWaterLevelPercent());
        updateTanks(station.getId(), ResourceType.HYDROGEN, request.getHydrogenLevelPercent());
        updateTanks(station.getId(), ResourceType.OXYGEN, request.getOxygenLevelPercent());

        LocalDateTime readingTime = LocalDateTime.now();
        if (request.getTimestamp() != null) {
            readingTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(request.getTimestamp()), ZoneId.systemDefault());
        }

        saveSensorReading(station.getId(), SensorType.ENERGY, request.getEnergyLevelPercent(), readingTime);
        saveSensorReading(station.getId(), SensorType.TEMPERATURE, request.getTemperatureCelsius(), readingTime);
        saveSensorReading(station.getId(), SensorType.HUMIDITY, request.getHumidityPercent(), readingTime);
        saveSensorReading(station.getId(), SensorType.TANK_LEVEL, request.getWaterLevelPercent(), readingTime); // Exemplo unificado

        if (Boolean.TRUE.equals(request.getAlertActive())) {
            OperationalAlert alert = OperationalAlert.builder()
                    .lunarStation(station)
                    .source("Módulo IoT ESP32 (Disp: " + request.getDeviceId() + ")")
                    .message(request.getAlertMessage() != null && !request.getAlertMessage().isEmpty() ? request.getAlertMessage() : "Alerta acionado via telemetria")
                    .active(true)
                    .triggeredAt(readingTime)
                    .severity(AlertSeverity.valueOf(request.getAlertSeverity() != null ? request.getAlertSeverity() : "WARNING"))
                    .build();
            alertRepository.save(alert);

            importantEventTriggered = true;
            auditDescription.append("Alerta disparado: ").append(alert.getMessage());
        }

        if (importantEventTriggered) {
            registerAuditLog(station, auditDescription.toString());
        }

        log.info("✅ Processamento da Estação {} concluído com sucesso!", station.getStationCode());
    }

    private void updateTanks(Long stationId, ResourceType type, Double percent) {
        if (percent == null) return;

        List<ResourceTank> tanks = tankRepository.findByLunarStationIdAndResourceType(stationId, type);
        for (ResourceTank tank : tanks) {
            double newVolume = tank.getMaxCapacity() * (percent / 100.0);
            tank.setCurrentVolume(newVolume);
            tankRepository.save(tank);
        }
    }

    private void saveSensorReading(Long stationId, SensorType type, Double value, LocalDateTime timestamp) {
        if (value == null) return;

        sensorRepository.findAll().stream()
                .filter(s -> s.getLunarStation().getId().equals(stationId) && s.getSensorType() == type)
                .findFirst()
                .ifPresent(sensor -> {
                    SensorReading reading = SensorReading.builder()
                            .sensor(sensor)
                            .readingValue(value)
                            .timestamp(timestamp)
                            .build();
                    sensorReadingRepository.save(reading);
                });
    }

    private void registerAuditLog(LunarStation station, String description) {
        userRepository.findById(1L).ifPresent(user -> {
            AuditLog logEntry = AuditLog.builder()
                    .user(user)
                    .action("PROCESSAMENTO_TELEMETRIA")
                    .description(description)
                    .createdAt(LocalDateTime.now())
                    .build();
            auditLogRepository.save(logEntry);
        });
    }
}