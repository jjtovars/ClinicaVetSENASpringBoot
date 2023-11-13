package com.example.ClinicaVetSENA.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
@Entity
public class Cliente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50)
    private int identificacion;
    @Column(length = 100)
    private String nombres;
    @Column(length = 100)
    private String apellidos;
    @Column(length = 50)
    private String departamento;
    @Column(length = 50)
    private String ciudad;
    @Column(length = 150)
    private String direccion;
    @Column(length = 25)
    private String telefono;
    @Column(unique = true, length = 50)
    private String email;
    
    
    @JsonManagedReference
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Paciente> pacientes = new ArrayList<>();
}


