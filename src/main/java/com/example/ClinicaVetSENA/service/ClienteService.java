package com.example.ClinicaVetSENA.service;

import com.example.ClinicaVetSENA.entity.Cliente;
import com.example.ClinicaVetSENA.repository.ClienteRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    
    @Autowired
    ClienteRepository clienteRepository;
    
    public List<Cliente> getClientes(){
        return clienteRepository.findAll();
    }
    
    public Optional<Cliente> getCliente(Long id){
        return clienteRepository.findById(id);
    }
    
    public void saveOrUpdate(Cliente cliente){
        clienteRepository.save(cliente);
    }
    
    public void delete(Long id){
        clienteRepository.deleteById(id);
    }
}
