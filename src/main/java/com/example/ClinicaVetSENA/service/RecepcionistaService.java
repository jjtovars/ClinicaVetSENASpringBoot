package com.example.ClinicaVetSENA.service;

import com.example.ClinicaVetSENA.entity.Recepcionista;
import com.example.ClinicaVetSENA.repository.RecepcionistaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecepcionistaService {
    
    @Autowired
    private RecepcionistaRepository recepcionistaRepository;
    
    public List<Recepcionista> getRecepcionistas(){
        return recepcionistaRepository.findAll();
    }
    
    public Optional<Recepcionista> getRecepcionista(Long id){
        return recepcionistaRepository.findById(id);
    }
    
    public void saveOrUpdate(Recepcionista recepcionista){
        recepcionistaRepository.save(recepcionista);
    }
    
    public void delete(Long id){
        recepcionistaRepository.deleteById(id);
    }
}
