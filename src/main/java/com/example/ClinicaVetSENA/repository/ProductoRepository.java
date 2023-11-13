package com.example.ClinicaVetSENA.repository;

import com.example.ClinicaVetSENA.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long>{
    
    
}
