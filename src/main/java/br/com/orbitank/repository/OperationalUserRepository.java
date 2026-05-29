package br.com.orbitank.repository;

import br.com.orbitank.entity.OperationalUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationalUserRepository
        extends JpaRepository<OperationalUser, Long> {
}