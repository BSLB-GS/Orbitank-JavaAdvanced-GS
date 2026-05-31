package br.com.orbitank.service;

import br.com.orbitank.dto.Request.IotTelemetryRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class IotTelemetryService {

    public void processTelemetry(IotTelemetryRequest request) {
        log.info("📡 Telemetria recebida! Estação: {} | Dispositivo: {}", request.getStationCode(), request.getDeviceId());
        log.info("Detalhes da leitura: Água: {}% | H2: {}% | O2: {}% | Temp: {}°C",
                request.getWaterLevelPercent(),
                request.getHydrogenLevelPercent(),
                request.getOxygenLevelPercent(),
                request.getTemperatureCelsius());

    }
}