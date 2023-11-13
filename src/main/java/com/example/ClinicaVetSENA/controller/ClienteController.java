package com.example.ClinicaVetSENA.controller;

import com.example.ClinicaVetSENA.entity.Cliente;
import com.example.ClinicaVetSENA.service.ClienteService;
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
@RequestMapping("/clientes")
public class ClienteController {
    
    @Autowired
    private ClienteService clienteService;
    
    @GetMapping
    public List<Cliente> getAll(){
        return clienteService.getClientes();
    }
    
    @GetMapping("/{clienteId}")
    public Optional<Cliente> getById(@PathVariable("clienteId")Long clienteId){
        return clienteService.getCliente(clienteId);
    }
    
    @PostMapping
    public void saveUpdate(@RequestBody Cliente cliente){
        System.out.println("Cliente recibido: " + cliente);
        clienteService.saveOrUpdate(cliente);
    }
    
    @DeleteMapping("/{clienteId}")
    public void delete(@PathVariable("clienteId")Long id){
        clienteService.delete(id);
    }
    
    
}
