package com.example.ClinicaVetSENA.controller;

import com.example.ClinicaVetSENA.entity.Paciente;
import com.example.ClinicaVetSENA.service.PacienteService;
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
@RequestMapping("/pacientes")
public class PacienteController {
    
    @Autowired
    private PacienteService pacienteService;
    
    @GetMapping
    public List<Paciente> getAll(){
        return pacienteService.getPacientes();
    }
    
    @GetMapping("/{pacienteId}")
    public Optional<Paciente> getById(@PathVariable("pacienteId")Long pacienteId){
        return pacienteService.getPaciente(pacienteId);
    }
    
    @PostMapping
    public void saveUpdate(@RequestBody Paciente paciente){
        pacienteService.saveOrUpdate(paciente);
    }
    
    @DeleteMapping("/{pacienteId}")
    public void delete(@PathVariable("pacienteId")Long pacienteId){
        pacienteService.delete(pacienteId);
    }
}
