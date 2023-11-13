package com.example.ClinicaVetSENA.controller;

import com.example.ClinicaVetSENA.entity.Producto;
import com.example.ClinicaVetSENA.service.ProductoService;
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
@RequestMapping("/productos")
public class ProductoController {
    
    @Autowired
    private ProductoService productoService;


    @GetMapping
    public List<Producto> getAll() {
        return productoService.getProductos();
    }

    @GetMapping("/{productoId}")
    public  Optional<Producto> getById(@PathVariable("productoId") Long productoId) {
        return productoService.getProducto(productoId);
    }

    @PostMapping
    public void saveUpdate(@RequestBody Producto producto) {
        productoService.saveOrUpdate(producto);
    }

    @DeleteMapping("/{productoId}")
    public void delete(@PathVariable("productoId") Long productoId) {
        productoService.delete(productoId);
    }
}