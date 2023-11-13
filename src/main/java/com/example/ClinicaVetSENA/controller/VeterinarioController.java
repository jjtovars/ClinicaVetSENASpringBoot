package com.example.ClinicaVetSENA.controller;

import com.example.ClinicaVetSENA.entity.Veterinario;
import com.example.ClinicaVetSENA.service.VeterinarioService;
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
@RequestMapping("/veterinarios")
public class VeterinarioController {
    
    @Autowired
    private VeterinarioService veterinarioService;
    
    @GetMapping
    public List<Veterinario> getAll(){
        return veterinarioService.getVeterinarios();
    }
    
    @GetMapping("/{veterinarioId}")
    public Optional<Veterinario> getById(@PathVariable("veterinarioId")Long veterinarioId){
        return veterinarioService.getVeterinario(veterinarioId);
    }
    
    @PostMapping
    public void saveUpdate(@RequestBody Veterinario veterinario){
        veterinarioService.saveOrUpdate(veterinario);
    }
    
    @DeleteMapping("/{veterinarioId}")
    public void delete(@PathVariable("veterinarioId")Long veterinarioId){
        veterinarioService.delete(veterinarioId);
    }
}
