package com.start.historiaclinicadigital.repositories;

import com.start.historiaclinicadigital.models.HistoriaClinica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface HistoriaClinicaRepository  extends JpaRepository<HistoriaClinica, Long>  {
}
