package br.com.orbitank.repository;

import br.com.orbitank.entity.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorRepository
        extends JpaRepository<Sensor, Long> {
}