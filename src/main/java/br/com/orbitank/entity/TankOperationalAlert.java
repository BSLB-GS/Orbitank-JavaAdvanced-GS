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
@DiscriminatorValue("TANK")
public class TankOperationalAlert extends OperationalAlert {

    @Column(name="tank_identifier",length=50)
    private String tankIdentifier;

    @Column(name="resource_type",length=50)
    private String resourceType;
}