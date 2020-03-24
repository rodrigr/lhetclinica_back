package com.start.historiaclinicadigital.repositories;

import com.start.historiaclinicadigital.models.Enfermero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.swing.text.html.Option;
import java.util.Optional;

@RepositoryRestResource
public interface EnfermeroRepository extends JpaRepository<Enfermero,Long> {
    Optional<Enfermero> findByEmail(String email);
}
