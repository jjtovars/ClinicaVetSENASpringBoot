package com.example.ClinicaVetSENA.repository;

import com.example.ClinicaVetSENA.entity.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

// Interfaz de repositorio que extiende JpaRepository para operaciones CRUD en la entidad Factura
// Se utiliza el tipo Long para representar el tipo de dato de la clave primaria de la entidad Factura
@Service
public interface FacturaRepository extends JpaRepository<Factura, Long>{
    
}
