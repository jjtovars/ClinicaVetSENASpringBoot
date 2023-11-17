package com.example.ClinicaVetSENA.controller;

import com.example.ClinicaVetSENA.entity.Recepcionista;
import com.example.ClinicaVetSENA.service.RecepcionistaService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class RecepcionistaController {
    
    @Autowired
    private RecepcionistaService recepcionistaService;
    
    @GetMapping("/recepcionistas")
    public String getAll(Model model){
        model.addAttribute("recepcionistas", recepcionistaService.getRecepcionistas());
        return "recepcionistas";
    }
    
    @GetMapping("/recepcionistas/nuevo")
    public String getRecepcionista(Model model){
        Recepcionista recepcionista = new Recepcionista();
        model.addAttribute("recepcionista", recepcionista);
        return "crear_recepcionista";
    }
    
    @PostMapping("/recepcionistas")
    public String saveUpdate(@ModelAttribute("recepcionista")Recepcionista recepcionista){
        recepcionistaService.saveOrUpdate(recepcionista);
        return "redirect:/recepcionistas";
    }
    
    @GetMapping("/recepcionistas/editar/{id}")
    public String getById(@PathVariable Long id, Model model){
        model.addAttribute("recepcionista", recepcionistaService.getRecepcionista(id));
        return "editar_recepcionista";
    }
    
    @PostMapping("/recepcionistas/{id}")
    public String updateRecepcionista(@PathVariable Long id, @ModelAttribute("recepcionista")Recepcionista recepcionista){
        Optional<Recepcionista> recepcionistaExistente = recepcionistaService.getRecepcionista(id);
        
        if(recepcionistaExistente.isPresent()){
            Recepcionista recepcionistaActualizado = recepcionistaExistente.get();
            recepcionistaActualizado.setIdentificacion(recepcionista.getIdentificacion());
            recepcionistaActualizado.setNombres(recepcionista.getNombres());
            recepcionistaActualizado.setApellidos(recepcionista.getApellidos());
            recepcionistaActualizado.setDireccion(recepcionista.getDireccion());
            recepcionistaActualizado.setTelefono(recepcionista.getTelefono());
            recepcionistaActualizado.setCorreo(recepcionista.getCorreo());
            
            recepcionistaService.saveOrUpdate(recepcionistaActualizado);
        }
        return "redirect:/recepcionistas";
    }
    
    @GetMapping("/recepcionistas/{id}")
    public String deleteRecepcionista(@PathVariable Long id){
        recepcionistaService.delete(id);
        return "redirect:/recepcionistas";
    }
    
    /*@GetMapping("/{recepcionistaId}")
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
    }*/
}
