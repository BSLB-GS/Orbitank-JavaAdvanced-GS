package br.com.orbitank.repository;

import br.com.orbitank.entity.SensorReading;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorReadingRepository
        extends JpaRepository<SensorReading, Long> {
}