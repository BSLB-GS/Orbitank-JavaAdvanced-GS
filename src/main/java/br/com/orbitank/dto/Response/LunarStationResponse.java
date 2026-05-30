package br.com.orbitank.dto.Response;

import br.com.orbitank.enums.StationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LunarStationResponse {
    private Long id;
    private Long stationCode;
    private String name;
    private String location;
    private StationStatus status;
}