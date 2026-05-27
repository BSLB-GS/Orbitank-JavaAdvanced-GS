package br.com.orbitank.repository;

import br.com.orbitank.entity.SpaceMission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpaceMissionRepository
        extends JpaRepository<SpaceMission, Long> {
}