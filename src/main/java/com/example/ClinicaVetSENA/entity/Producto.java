package com.example.ClinicaVetSENA.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Producto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50)
    private String codigo;
    @Column(length = 50)
    private String categoria;
    @Column(length = 100)
    private String nombre;
    @Column(length = 100)
    private String descripcion;
    private double precio;
}
