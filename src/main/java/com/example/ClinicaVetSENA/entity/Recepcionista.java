package com.example.ClinicaVetSENA.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
@Entity
public class Recepcionista {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int identificacion;
    @Column(length = 100)
    private String nombres;
    @Column(length = 100)
    private String apellidos;
    @Column(length = 150)
    private String direccion;
    @Column(length = 25)
    private String telefono;
    @Column(length = 100, unique = true)
    private String correo;
    
    @JsonIgnore
    @OneToMany(mappedBy = "recepcionista", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProgramacionCita> programacionCitas = new ArrayList<>();
}
