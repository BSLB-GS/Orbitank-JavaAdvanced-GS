package br.com.orbitank.controller;

import br.com.orbitank.dto.Request.LunarStationRequest;
import br.com.orbitank.dto.Response.*;
import br.com.orbitank.service.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stations")
@RequiredArgsConstructor
public class LunarStationController {

    private final LunarStationService service;
    private final SensorReadingService sensorReadingService;
    private final OperationalAlertService operationalAlertService;
    private final ResourceTankService resourceTankService;
    private final MiningRobotService miningRobotService;

    @GetMapping
    public ResponseEntity<List<LunarStationResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LunarStationResponse> findById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/{id}/dashboard")
    public ResponseEntity<StationDashboardResponse> getDashboard(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(service.getDashboard(id));
    }

    @GetMapping("/{id}/sensors/latest-readings")
    public ResponseEntity<List<LatestSensorReadingResponse>> getLatestSensorReadings(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(sensorReadingService.getLatestReadingsByStation(id));
    }

    // ESP32
    @GetMapping("/code/{stationCode}")
    public ResponseEntity<LunarStationResponse> findByStationCode(
            @PathVariable Long stationCode
    ) {
        return ResponseEntity.ok(service.findByStationCode(stationCode));
    }

    @PostMapping
    public ResponseEntity<LunarStationResponse> create(
            @RequestBody @Valid LunarStationRequest request
    ) {
        return ResponseEntity.ok(service.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LunarStationResponse> update(
            @PathVariable Long id,
            @RequestBody @Valid LunarStationRequest request
    ) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id
    ) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/alerts")
    public ResponseEntity<List<OperationalAlertResponse>> getStationAlerts(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(operationalAlertService.findAlertsByStationId(id));
    }

    @GetMapping("/{id}/alerts/critical")
    public ResponseEntity<List<OperationalAlertResponse>> getStationCriticalAlerts(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(operationalAlertService.findCriticalAlertsByStationId(id));
    }

    @GetMapping("/{id}/tanks")
    public ResponseEntity<List<ResourceTankResponse>> getStationTanks(@PathVariable Long id) {
        return ResponseEntity.ok(resourceTankService.findByStationId(id));
    }

    @GetMapping("/{id}/robots")
    public ResponseEntity<List<MiningRobotResponse>> getStationRobots(@PathVariable Long id) {
        return ResponseEntity.ok(miningRobotService.findByStationId(id));
    }
}