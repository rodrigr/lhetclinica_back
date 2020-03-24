package com.start.historiaclinicadigital.repositories;

import com.start.historiaclinicadigital.models.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface MedicamentoRepository extends JpaRepository<Medicamento,Long> {
}
