package br.com.orbitank.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@DiscriminatorValue("SENSOR")
public class SensorOperationalAlert extends OperationalAlert {

    @Column(name="sensor_identifier",length=50)
    private String sensorIdentifier;

    @Column(name="sensor_type",length=50)
    private String sensorType;
}