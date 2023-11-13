package com.example.ClinicaVetSENA.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import lombok.Data;

@Data
@Entity
public class DetalleFactura {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "factura_id")
    private Factura factura;
    
    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;
    
    private int cantidad;
    
}
