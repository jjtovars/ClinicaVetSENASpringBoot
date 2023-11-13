package com.example.ClinicaVetSENA.repository;

import com.example.ClinicaVetSENA.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
    
}
