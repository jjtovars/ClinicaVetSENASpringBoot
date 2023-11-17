package com.example.ClinicaVetSENA.service;

import com.example.ClinicaVetSENA.entity.Paciente;
import com.example.ClinicaVetSENA.repository.PacienteRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PacienteService {
    
    @Autowired
    private PacienteRepository pacienteRepository;
    
    public List<Paciente> getPacientes(){
        return pacienteRepository.findAll();
    }
    
    public Optional<Paciente> getPaciente(Long id){
        return pacienteRepository.findById(id);
    }
    
    public List<Paciente> buscarPacientes(String term) {
        Long id;
        try {
            id = Long.parseLong(term);
        } catch (NumberFormatException e) {
            id = null;
        }

        return pacienteRepository.findByNombreContainingIgnoreCaseOrIdOrEspecieContainingIgnoreCase(term, id, term);
    }
    
    public void saveOrUpdate(Paciente paciente){
        pacienteRepository.save(paciente);
    }
    
    public void delete(Long id){
        pacienteRepository.deleteById(id);
    }
}
