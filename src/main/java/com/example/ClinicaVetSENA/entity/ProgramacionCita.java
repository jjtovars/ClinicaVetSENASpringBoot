package com.example.ClinicaVetSENA.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.Data;

@Data
@Entity
public class ProgramacionCita {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime fecha_creacion = LocalDateTime.now().withNano(0);
    private LocalDateTime fecha_cita;
    
    @JsonIgnoreProperties("programacionCitas")
    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;
    
    @ManyToOne
    @JoinColumn(name = "veterinario_id")
    private Veterinario veterinario;
    
    @ManyToOne
    @JoinColumn(name = "recepcionista_id")
    private Recepcionista recepcionista;
               
}
