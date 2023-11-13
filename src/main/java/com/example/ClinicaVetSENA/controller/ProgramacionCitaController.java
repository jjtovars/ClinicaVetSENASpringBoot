package com.example.ClinicaVetSENA.controller;

import com.example.ClinicaVetSENA.entity.ProgramacionCita;
import com.example.ClinicaVetSENA.service.ProgramacionCitaService;
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
@RequestMapping("/programacioncitas")
public class ProgramacionCitaController {
    
    @Autowired
    private ProgramacionCitaService programacionCitaService;
    
    @GetMapping
    public List<ProgramacionCita> getAll(){
        return programacionCitaService.getProgramacionCitas();
    }
    
    @GetMapping("/{programacionCitaId}")
    public Optional<ProgramacionCita> getById(@PathVariable("programacionCitaId")Long programacionCitaId){
        return programacionCitaService.getProgramacionCita(programacionCitaId);
    }
    
    @PostMapping
    public void saveUpdate(@RequestBody ProgramacionCita programacionCita){
        programacionCitaService.saveOrUpdate(programacionCita);
    }
    
    @DeleteMapping("/{programacionCitaId}")
    public void delete(@PathVariable("programacionCitaId")Long programacionCitaId){
        programacionCitaService.delete(programacionCitaId);
    }
}
