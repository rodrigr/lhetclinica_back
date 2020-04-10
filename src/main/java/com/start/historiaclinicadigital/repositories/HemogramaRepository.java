package com.start.historiaclinicadigital.repositories;

import com.start.historiaclinicadigital.models.Enfermero;
import com.start.historiaclinicadigital.models.Hemograma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface HemogramaRepository extends JpaRepository<Hemograma,Long> {
}
