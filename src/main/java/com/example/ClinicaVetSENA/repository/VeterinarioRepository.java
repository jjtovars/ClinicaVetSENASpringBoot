package com.example.ClinicaVetSENA.repository;

import com.example.ClinicaVetSENA.entity.Veterinario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeterinarioRepository extends JpaRepository<Veterinario, Long>{
    
}
