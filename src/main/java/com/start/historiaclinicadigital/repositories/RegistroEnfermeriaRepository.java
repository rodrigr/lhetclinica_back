package com.start.historiaclinicadigital.repositories;

import com.start.historiaclinicadigital.models.RegistroEnfermeria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface RegistroEnfermeriaRepository extends JpaRepository<RegistroEnfermeria,Long> {
}
