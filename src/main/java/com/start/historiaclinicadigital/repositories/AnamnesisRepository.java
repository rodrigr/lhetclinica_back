package com.start.historiaclinicadigital.repositories;

import com.start.historiaclinicadigital.models.Anamnesis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AnamnesisRepository extends JpaRepository<Anamnesis,Long>{
}
