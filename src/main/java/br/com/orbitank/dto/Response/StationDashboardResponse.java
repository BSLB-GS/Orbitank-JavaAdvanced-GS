package br.com.orbitank.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StationDashboardResponse {
    private String stationName;
    private String stationStatus;
    private Double iceAvailable;
    private Double waterAvailable;
    private Double hydrogenAvailable;
    private Double oxygenAvailable;
    private Long activeRobots;
    private Long totalRobots;
    private Long activeSensors;
    private Long totalSensors;
    private Long criticalAlerts;
    private Long missionsAwaitingFuel;
}