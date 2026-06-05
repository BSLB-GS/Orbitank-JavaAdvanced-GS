package br.com.orbitank.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("SENSOR")
public class SensorOperationalAlert extends OperationalAlert {

    @Column(name="sensor_identifier",length=50)
    private String sensorIdentifier;

    @Column(name="sensor_type",length=50)
    private String sensorType;
}