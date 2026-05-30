package br.com.orbitank.dto.Request;

import br.com.orbitank.enums.ResourceType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResourceTankRequest {

    @NotNull(message = "O ID da estação lunar é obrigatório")
    private Long lunarStationId;

    @NotNull
    @Positive(message = "A capacidade máxima deve ser maior que zero")
    private Double maxCapacity;

    @NotNull
    @PositiveOrZero(message = "O volume não pode ser negativo")
    private Double currentVolume;

    @NotNull
    @PositiveOrZero(message = "A pressão não pode ser negativa")
    private Double currentPressure;

    @NotNull
    @DecimalMin(value = "-273.15", message = "A temperatura não pode ser inferior ao zero absoluto")
    private Double currentTemperature;

    @NotNull(message = "O tipo de recurso é obrigatório")
    private ResourceType resourceType;
}