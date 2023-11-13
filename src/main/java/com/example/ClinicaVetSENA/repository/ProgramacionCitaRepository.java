package com.example.ClinicaVetSENA.repository;

import com.example.ClinicaVetSENA.entity.ProgramacionCita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgramacionCitaRepository extends JpaRepository<ProgramacionCita, Long>{
    
}
