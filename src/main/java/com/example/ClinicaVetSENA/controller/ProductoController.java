package com.example.ClinicaVetSENA.controller;

import com.example.ClinicaVetSENA.entity.Producto;
import com.example.ClinicaVetSENA.service.ProductoService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class ProductoController {
    
    @Autowired
    private ProductoService productoService;

    // -------------Endpoint para mostrar todos los productos-----------------
    @GetMapping("/productos")
    public String getAll(Model model) {
        model.addAttribute("productos", productoService.getProductos());
        return "productos";
    }
    
    // ------------------------- Trae los campos en blanco para poder llenarlos y con @PostMapping poder guardarlos -------------------
    
    @GetMapping("/productos/nuevo")
    public String getProducto(Model model){
        Producto producto = new Producto();
        model.addAttribute("producto", producto);
        return "crear_producto";
    }
    
    @PostMapping("/productos")
    public String saveUpdate(@ModelAttribute("producto")Producto producto){
        productoService.saveOrUpdate(producto);
        return "redirect:/productos";
    }
    
    // ------------------------------------------------------
    
    @GetMapping("/productos/editar/{id}")
    public String getById(@PathVariable Long id, Model model){
        model.addAttribute("producto", productoService.getProducto(id));
        return "editar_producto";
    }
    
    @PostMapping("/productos/{id}")
    public String updateProducto(@PathVariable Long id, @ModelAttribute("producto")Producto producto){
        Optional<Producto> productoExistente = productoService.getProducto(id);
        
        if(productoExistente.isPresent()){
            Producto productoActualizado = productoExistente.get();
            productoActualizado.setCodigo(producto.getCodigo());
            productoActualizado.setCategoria(producto.getCategoria());
            productoActualizado.setNombre(producto.getNombre());
            productoActualizado.setDescripcion(producto.getDescripcion());
            productoActualizado.setPrecio(producto.getPrecio());
            
            productoService.saveOrUpdate(productoActualizado);
        }
        return "redirect:/productos";
    }
    
    @GetMapping("/productos/{id}")
    public String deleteProducto(@PathVariable Long id){
        productoService.delete(id);
        return "redirect:/productos";
    }
    
    

    /*@GetMapping("/{productoId}")
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
    }*/
}