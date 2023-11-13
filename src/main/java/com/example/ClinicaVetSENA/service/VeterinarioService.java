package com.example.ClinicaVetSENA.service;

import com.example.ClinicaVetSENA.entity.Veterinario;
import com.example.ClinicaVetSENA.repository.VeterinarioRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VeterinarioService {
    
    @Autowired
    private VeterinarioRepository veterinarioRepository;
    
    public List<Veterinario> getVeterinarios(){
        return veterinarioRepository.findAll();
    }
    
    public Optional<Veterinario> getVeterinario(Long id){
        return veterinarioRepository.findById(id);
    }
    
    public void saveOrUpdate(Veterinario veterinario){
        veterinarioRepository.save(veterinario);
    }
    
    public void delete(Long id){
        veterinarioRepository.deleteById(id);
    }
}
