package com.example.ClinicaVetSENA.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

// DTO (Data Transfer Object) para representar las solicitudes relacionadas con la entidad Factura
@Data
public class FacturaRequest {
    
    // Fecha de creación de la factura (se establece automáticamente al momento de la creación)
    private LocalDateTime fecha_creacion = LocalDateTime.now().withNano(0);
    
    // Método de pago utilizado para la factura
    private String medio_pago;
    
    // Identificador del cliente asociado a la factura
    private Long clienteId;
    
    // Identificador del recepcionista asociado a la factura
    private Long recepcionistaId;
    
    // Lista de detalles de la factura, cada uno representado por un objeto DetalleFacturaRequest
    private List<DetalleFacturaRequest> detalles;
}
