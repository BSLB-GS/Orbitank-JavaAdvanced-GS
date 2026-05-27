package br.com.orbitank.repository;

import br.com.orbitank.entity.ResourceTank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceTankRepository
        extends JpaRepository<ResourceTank, Long> {
}