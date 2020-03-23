package com.start.historiaclinicadigital.repositories;

import com.start.historiaclinicadigital.models.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface MedicoRepository extends JpaRepository<Medico, Long> {
}
