package br.com.orbitank.repository;

import br.com.orbitank.entity.LunarStation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LunarStationRepository
        extends JpaRepository<LunarStation, Long> {
}