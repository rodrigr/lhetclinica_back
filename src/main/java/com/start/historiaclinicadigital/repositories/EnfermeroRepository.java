package com.start.historiaclinicadigital.repositories;

import com.start.historiaclinicadigital.models.Enfermero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface EnfermeroRepository extends JpaRepository<Enfermero,Long> {
}
