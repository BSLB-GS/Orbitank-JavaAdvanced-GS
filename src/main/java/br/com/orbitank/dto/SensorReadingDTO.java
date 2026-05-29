package br.com.orbitank.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SensorReadingDTO {

    private Long id;

    private Long sensorId;

    private Double readingValue;

    private LocalDateTime timestamp;
}