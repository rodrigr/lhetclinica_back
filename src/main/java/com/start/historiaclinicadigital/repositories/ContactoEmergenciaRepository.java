package com.start.historiaclinicadigital.repositories;

import com.start.historiaclinicadigital.models.ContactoEmergencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ContactoEmergenciaRepository extends JpaRepository<ContactoEmergencia,Long>{
}
