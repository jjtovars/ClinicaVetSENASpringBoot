package com.example.ClinicaVetSENA.entity;

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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
@Entity
public class Factura {
       
    /* 
    Identificador unico de la factura con sus principales atributos. El id y la fecha de creacion se
    establecen automaticamente
    */  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime fecha_creacion = LocalDateTime.now().withNano(0);
    @Column(length = 25)
    private String medio_pago;
    private double valorTotal;
    
    // Relacion con la entidad cliente
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    
    // Relacion con la entidad recepcionista
    @ManyToOne
    @JoinColumn(name = "recepcionista_id")
    private Recepcionista recepcionista;
    
    // Relación OneToMany con la entidad DetalleFactura mediante el mapeo con la propiedad "factura"
    @JsonManagedReference
    @OneToMany(mappedBy = "factura", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleFactura> detalles = new ArrayList<>();


}
