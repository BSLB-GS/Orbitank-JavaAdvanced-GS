package br.com.orbitank.repository;

import br.com.orbitank.entity.MiningRobot;
import br.com.orbitank.enums.RobotStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MiningRobotRepository extends JpaRepository<MiningRobot, Long> {
    long countByLunarStationId(Long stationId);
    long countByLunarStationIdAndStatus(Long stationId, RobotStatus status);
}