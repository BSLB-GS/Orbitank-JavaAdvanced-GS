package br.com.orbitank.dto.Request;

import br.com.orbitank.entity.Sensor;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SensorReadingRequest {

    @NotNull(message = "O sensor é obrigatório")
    private Sensor sensor;

    @NotNull(message = "O valor da leitura é obrigatório")
    private Double readingValue;

    @NotNull
    @PastOrPresent(message = "A data/hora da leitura não pode estar no futuro")
    private LocalDateTime timestamp;
}