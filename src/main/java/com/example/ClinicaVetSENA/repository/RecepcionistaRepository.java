package com.example.ClinicaVetSENA.repository;

import com.example.ClinicaVetSENA.entity.Recepcionista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecepcionistaRepository extends JpaRepository<Recepcionista, Long>{
    
}
