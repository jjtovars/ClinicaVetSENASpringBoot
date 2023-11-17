package com.example.ClinicaVetSENA.controller;

import com.example.ClinicaVetSENA.entity.Veterinario;
import com.example.ClinicaVetSENA.service.VeterinarioService;
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
public class VeterinarioController {
    
    @Autowired
    private VeterinarioService veterinarioService;
    
    @GetMapping("/veterinarios")
    public String getAll(Model model){
        model.addAttribute("veterinarios", veterinarioService.getVeterinarios());
        return "veterinarios";
    }
    
    //---------------------------------------------------------------------------
    
    @GetMapping("/veterinarios/nuevo")
    public String getVeterinario(Model model){
        Veterinario veterinario = new Veterinario();
        model.addAttribute("veterinario", veterinario);
        return "crear_veterinario";
    }
    
    @PostMapping("/veterinarios")
    public String saveUpdate(@ModelAttribute("veterinario")Veterinario veterinario){
        veterinarioService.saveOrUpdate(veterinario);
        return "redirect:/veterinarios";
    }
    
    //--------------------------------------------------------------------------
    
    @GetMapping("/veterinarios/editar/{id}")
    private String getById(@PathVariable Long id, Model model){
        model.addAttribute("veterinario", veterinarioService.getVeterinario(id));
        return "editar_veterinario";
    }
    
    @PostMapping("/veterinarios/{id}")
    public String updateVeterinario(@PathVariable Long id, @ModelAttribute("veterinario")Veterinario veterinario){
        Optional<Veterinario> veterinarioExistente = veterinarioService.getVeterinario(id);
        
        if(veterinarioExistente.isPresent()){
            Veterinario veterinarioActualizado = veterinarioExistente.get();
            veterinarioActualizado.setIdentificacion(veterinario.getIdentificacion());
            veterinarioActualizado.setNombres(veterinario.getNombres());
            veterinarioActualizado.setApellidos(veterinario.getApellidos());
            veterinarioActualizado.setDireccion(veterinario.getDireccion());
            veterinarioActualizado.setTelefono(veterinario.getTelefono());
            veterinarioActualizado.setEspecialidad(veterinario.getEspecialidad());
            veterinarioActualizado.setEmail(veterinario.getEmail());
            
            veterinarioService.saveOrUpdate(veterinarioActualizado);
        }
        return "redirect:/veterinarios";
    }
    
    @GetMapping("/veterinarios/{id}")
    public String deleteVeterinario(@PathVariable Long id){
        veterinarioService.delete(id);
        return "redirect:/veterinarios";
    }
    
    /*@GetMapping("/{veterinarioId}")
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
    }*/
}
