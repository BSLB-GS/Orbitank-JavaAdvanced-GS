package br.com.orbitank.entity;

import br.com.orbitank.enums.ResourceType;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "tb_resource_tank")

public class ResourceTank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Positive(message = "A capacidade máxima deve ser maior que zero")
    @Column(nullable = false)
    private Double maxCapacity;

    @NotNull
    @PositiveOrZero(message = "O volume não pode ser negativo")
    @Column(nullable = false)
    private Double currentVolume;

    @NotNull
    @PositiveOrZero(message = "A pressão não pode ser negativa")
    @Column(nullable = false)
    private Double currentPressure;

    @NotNull
    @DecimalMin(
            value = "-273.15",
            message = "A temperatura não pode ser inferior ao zero absoluto"
    )
    @Column(nullable = false)
    private Double currentTemperature;

    @NotNull(message = "O tipo de recurso é obrigatório")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ResourceType resourceType;
}