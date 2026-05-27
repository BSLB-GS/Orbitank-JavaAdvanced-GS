package br.com.orbitank.repository;

import br.com.orbitank.entity.OperationalAlert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationalAlertRepository
        extends JpaRepository<OperationalAlert, Long> {
}