package com.example.ClinicaVetSENA.controller;

import com.example.ClinicaVetSENA.dto.DetalleFacturaRequest;
import com.example.ClinicaVetSENA.dto.FacturaRequest;
import com.example.ClinicaVetSENA.entity.Cliente;
import com.example.ClinicaVetSENA.entity.DetalleFactura;
import com.example.ClinicaVetSENA.entity.Factura;
import com.example.ClinicaVetSENA.entity.Producto;
import com.example.ClinicaVetSENA.entity.Recepcionista;
import com.example.ClinicaVetSENA.service.ClienteService;
import com.example.ClinicaVetSENA.service.FacturaService;
import com.example.ClinicaVetSENA.service.ProductoService;
import com.example.ClinicaVetSENA.service.RecepcionistaService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/facturas")
public class FacturaController {

    @Autowired
    private FacturaService facturaService;

    @Autowired
    private ProductoService productoService;
    
    @Autowired
    private ClienteService clienteService;
    
    @Autowired
    private RecepcionistaService recepcionistaService;
    
    // Endpoint para obtener todas las facturas
    @GetMapping
    public List<Factura> getAll() {
        return facturaService.getFacturas();
    }
    
    // Endpoint para obtener una factura por su ID
    @GetMapping("/{facturaId}")
    public Optional<Factura> getById(@PathVariable("facturaId") Long facturaId) {
        return facturaService.getFactura(facturaId);
    }

    // Método privado para calcular el valor total de una factura basado en los detalles
    private double calcularValorTotal(List<DetalleFactura> detalles) {
        double valorTotal = 0.0;
        for (DetalleFactura detalle : detalles) {
            Producto producto = detalle.getProducto();
            double precio = producto.getPrecio();
            int cantidad = detalle.getCantidad();
            valorTotal += precio * cantidad;
        }
        return valorTotal;
    }

    // Endpoint para guardar o actualizar una factura
    @PostMapping
    public void saveUpdate(@RequestBody FacturaRequest facturaRequest) {
        Factura nuevaFactura = new Factura();
        nuevaFactura.setFecha_creacion(facturaRequest.getFecha_creacion());
        nuevaFactura.setMedio_pago(facturaRequest.getMedio_pago());
        
        // Obtener el cliente por ID
        Cliente cliente = clienteService.getCliente(facturaRequest.getClienteId()).orElse(null);
        if(cliente != null){
            nuevaFactura.setCliente(cliente);
        }
        
        // Obtener el recepcionista por ID
        Recepcionista recepcionista = recepcionistaService.getRecepcionista(facturaRequest.getRecepcionistaId()).orElse(null);
        if(recepcionista != null){
            nuevaFactura.setRecepcionista(recepcionista);
        }
        
        // Crear una lista de detalles de factura
        List<DetalleFactura> detalles = new ArrayList<>();
        
        // Iterar sobre los detalles de la solicitud
        for (DetalleFacturaRequest detalleRequest : facturaRequest.getDetalles()) {
            // Obtener el producto por ID
            Producto producto = productoService.getProducto(detalleRequest.getProductoId()).orElse(null);
            if (producto != null) {
                DetalleFactura detalle = new DetalleFactura();
                detalle.setFactura(nuevaFactura);
                detalle.setProducto(producto);
                detalle.setCantidad(detalleRequest.getCantidad());
                detalles.add(detalle);
            }
        }
        
        // Establecer los detalles en la factura
        nuevaFactura.setDetalles(detalles);

        // Calcular el valor total de la factura
        double valorTotal = calcularValorTotal(detalles);
        
        // Establecer el valor total en la factura
        nuevaFactura.setValorTotal(valorTotal);

        // Guardar o actualizar la factura
        facturaService.saveOrUpdate(nuevaFactura);
    }
    
    // Endpoint para eliminar una factura por su ID
    @DeleteMapping("/{facturaId}")
    public void delete(@PathVariable("facturaId") Long facturaId) {
        facturaService.delete(facturaId);
    }
}
