package com.example.ClinicaVetSENA.dto;

import lombok.Data;

// DTO (Data Transfer Object) para representar los detalles de una factura en las solicitudes
@Data
public class DetalleFacturaRequest {
    
    // Identificador del producto asociado al detalle de la factura
    private Long productoId;
    
    // Cantidad de productos en el detalle de la factura
    private int cantidad;
}
