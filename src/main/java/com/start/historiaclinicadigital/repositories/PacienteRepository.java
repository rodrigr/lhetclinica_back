package com.start.historiaclinicadigital.repositories;

import com.start.historiaclinicadigital.models.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PacienteRepository extends JpaRepository<Paciente,Long>{
}
