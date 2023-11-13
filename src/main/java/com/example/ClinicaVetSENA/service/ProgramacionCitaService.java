package com.example.ClinicaVetSENA.service;

import com.example.ClinicaVetSENA.entity.ProgramacionCita;
import com.example.ClinicaVetSENA.repository.ProgramacionCitaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProgramacionCitaService {
    
    @Autowired
    private ProgramacionCitaRepository programacionCitaRepository;
    
    public List<ProgramacionCita> getProgramacionCitas(){
        return programacionCitaRepository.findAll();
    }
    
    public Optional<ProgramacionCita> getProgramacionCita(Long id){
        return programacionCitaRepository.findById(id);
    }
    
    public void saveOrUpdate(ProgramacionCita programacionCita){
        programacionCitaRepository.save(programacionCita);
    }
    
    public void delete(Long id){
        programacionCitaRepository.deleteById(id);
    }
}
