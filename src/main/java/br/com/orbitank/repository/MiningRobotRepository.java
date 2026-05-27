package br.com.orbitank.repository;

import br.com.orbitank.entity.MiningRobot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MiningRobotRepository
        extends JpaRepository<MiningRobot, Long> {
}