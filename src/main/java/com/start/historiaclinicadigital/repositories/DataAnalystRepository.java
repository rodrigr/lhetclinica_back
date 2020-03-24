package com.start.historiaclinicadigital.repositories;

import com.start.historiaclinicadigital.models.DataAnalyst;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface DataAnalystRepository extends JpaRepository<DataAnalyst,Long>{
}
