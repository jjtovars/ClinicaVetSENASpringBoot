package com.example.ClinicaVetSENA.service;

import com.example.ClinicaVetSENA.entity.Factura;
import com.example.ClinicaVetSENA.repository.FacturaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// Servicio que gestiona operaciones relacionadas con la entidad Factura
@Service
public class FacturaService {
    
    // Inyección de dependencia del repositorio FacturaRepository
    @Autowired
    FacturaRepository facturaRepository;
    
    // Método para obtener todas las facturas existentes
    public List<Factura> getFacturas(){
        return facturaRepository.findAll();
    }
    
    // Método para obtener una factura por su identificador único
    public Optional<Factura> getFactura(Long id){
        return facturaRepository.findById(id);
    }
    
    // Método para guardar o actualizar una factura en la base de datos
    public void saveOrUpdate(Factura factura){
        facturaRepository.save(factura);
    }
    
    // Método para eliminar una factura por su identificador único
    public void delete(Long id){
        facturaRepository.deleteById(id);
    }
}

