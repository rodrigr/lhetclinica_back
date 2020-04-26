package com.start.historiaclinicadigital.repositories;

import com.start.historiaclinicadigital.models.Admin;
import com.start.historiaclinicadigital.models.Administrativo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdministrativoRepository extends JpaRepository<Administrativo,Long> {
    Optional<Administrativo> findByEmail(String email);
}
