package com.example.ClinicaVetSENA.controller;

import com.example.ClinicaVetSENA.entity.Recepcionista;
import com.example.ClinicaVetSENA.service.RecepcionistaService;
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
@RequestMapping("/recepcionistas")
public class RecepcionistaController {
    
    @Autowired
    private RecepcionistaService recepcionistaService;
    
    @GetMapping
    public List<Recepcionista> getAll(){
        return recepcionistaService.getRecepcionistas();
    }
    
    @GetMapping("/{recepcionistaId}")
    public Optional<Recepcionista> getById(@PathVariable("recepcionistaId")Long recepcionistaId){
        return recepcionistaService.getRecepcionista(recepcionistaId);
    }
    
    @PostMapping
    public void saveUpdate(@RequestBody Recepcionista recepcionista){
        recepcionistaService.saveOrUpdate(recepcionista);
    }
    
    @DeleteMapping("/{recepcionistaId}")
    public void delete(@PathVariable("recepcionistaId")Long recepcionistaId){
        recepcionistaService.delete(recepcionistaId);
    }
}
