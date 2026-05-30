package br.com.orbitank.repository;

import br.com.orbitank.entity.OperationalAlert;
import br.com.orbitank.enums.AlertSeverity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationalAlertRepository extends JpaRepository<OperationalAlert, Long> {
    long countByLunarStationIdAndSeverityAndActiveTrue(Long stationId, AlertSeverity severity);
}