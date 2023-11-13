package com.example.ClinicaVetSENA.service;

import com.example.ClinicaVetSENA.entity.Producto;
import com.example.ClinicaVetSENA.repository.ProductoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {
    
    @Autowired
    ProductoRepository productoRepository;

    public List<Producto> getProductos(){
        return productoRepository.findAll();
    }

    public Optional<Producto> getProducto(Long id){
        return productoRepository.findById(id);
    }

    public void saveOrUpdate(Producto producto){
        productoRepository.save(producto);
    }

    public void delete(Long id){
        productoRepository.deleteById(id);
    }
}
