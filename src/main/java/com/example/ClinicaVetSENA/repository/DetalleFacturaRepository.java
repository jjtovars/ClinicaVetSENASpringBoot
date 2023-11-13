package com.example.ClinicaVetSENA.repository;

import com.example.ClinicaVetSENA.entity.DetalleFactura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface DetalleFacturaRepository extends JpaRepository<DetalleFactura, Long>{
    
}
