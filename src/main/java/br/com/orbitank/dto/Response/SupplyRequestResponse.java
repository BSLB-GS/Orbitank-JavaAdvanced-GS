package br.com.orbitank.dto.Response;

import br.com.orbitank.entity.SpaceMission;
import br.com.orbitank.enums.SupplyRequestStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SupplyRequestResponse {
    private Long id;
    private SpaceMission mission;
    private Double requestedWaterVolume;
    private Double requestedH2Volume;
    private Double requestedO2Volume;
    private LocalDateTime requestDate;
    private String denialReason;
    private SupplyRequestStatus status;
}