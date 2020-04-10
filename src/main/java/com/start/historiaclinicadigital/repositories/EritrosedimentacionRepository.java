package com.start.historiaclinicadigital.repositories;

import com.start.historiaclinicadigital.models.Eritrosedimentacion;
import com.start.historiaclinicadigital.models.Hemograma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface EritrosedimentacionRepository extends JpaRepository<Eritrosedimentacion,Long> {
}
