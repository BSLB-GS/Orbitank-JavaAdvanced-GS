package br.com.orbitank.dto.Response;

import br.com.orbitank.entity.Sensor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SensorReadingResponse {
    private Long id;
    private Sensor sensor;
    private Double readingValue;
    private LocalDateTime timestamp;
}