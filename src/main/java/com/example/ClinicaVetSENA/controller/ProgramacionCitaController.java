package com.example.ClinicaVetSENA.controller;

import com.example.ClinicaVetSENA.entity.ProgramacionCita;
import com.example.ClinicaVetSENA.service.PacienteService;
import com.example.ClinicaVetSENA.service.ProgramacionCitaService;
import com.example.ClinicaVetSENA.service.RecepcionistaService;
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
public class ProgramacionCitaController {

    @Autowired
    private ProgramacionCitaService programacionCitaService;

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private VeterinarioService veterinarioService;

    @Autowired
    private RecepcionistaService recepcionistaService;

    @GetMapping("/programacioncitas")
    public String getAll(Model model) {
        model.addAttribute("programacioncitas", programacionCitaService.getProgramacionCitas());
        return "programacioncitas";
    }

    // ------------------------------------------------------------
    @GetMapping("/programacioncitas/nuevo")
    public String getProgramacionCitas(Model model) {
        ProgramacionCita programacionCita = new ProgramacionCita();
        model.addAttribute("programacioncita", programacionCita);
        return "crear_programacioncita";
    }

    @PostMapping("/programacioncitas")
    public String saveUpdate(@ModelAttribute("programacioncita") ProgramacionCita programacionCita, Model model) {
        try {
            // Verificar si el paciente existe
            if (pacienteService.getPaciente(programacionCita.getPaciente().getId()).isPresent()) {
                // Verificar si el veterinario existe
                if (veterinarioService.getVeterinario(programacionCita.getVeterinario().getId()).isPresent()) {
                    // Verificar si el recepcionista existe
                    if (recepcionistaService.getRecepcionista(programacionCita.getRecepcionista().getId()).isPresent()) {
                        programacionCitaService.saveOrUpdate(programacionCita);
                        return "redirect:/programacioncitas";
                    } else {
                        model.addAttribute("error", "Recepcionista no encontrado. Verifique el ID del Recepcionista.");
                        return "crear_programacioncita";
                    }
                } else {
                    model.addAttribute("error", "Veterinario no encontrado. Verifique el ID del Veterinario.");
                    return "crear_programacioncita";
                }
            } else {
                model.addAttribute("error", "Paciente no encontrado. Verifique el ID del Paciente.");
                return "crear_programacioncita";
            }
        } catch (Exception e) {
            model.addAttribute("error", "Error al guardar la Programación de Cita. Verifique los datos.");
            return "crear_programacioncita";
        }
    }
    
    // ------------------------------------------------------------------------
    
    @GetMapping("/programacioncitas/editar/{id}")
    public String getById(@PathVariable Long id, Model model){
        model.addAttribute("programacioncita", programacionCitaService.getProgramacionCita(id));
        return "editar_programacioncita";
    }
    
    @PostMapping("/programacioncitas/{id}")
    public String updateProgramacionCita(@PathVariable Long id, @ModelAttribute("programacioncita")ProgramacionCita programacionCita){
        Optional<ProgramacionCita> programacionCitaExistente = programacionCitaService.getProgramacionCita(id);
        
        if(programacionCitaExistente.isPresent()){
            ProgramacionCita programacionCitaActualizado = programacionCitaExistente.get();
            programacionCitaActualizado.setFecha_cita(programacionCita.getFecha_cita());
            programacionCitaActualizado.setPaciente(programacionCita.getPaciente());
            programacionCitaActualizado.setVeterinario(programacionCita.getVeterinario());
            programacionCitaActualizado.setRecepcionista(programacionCita.getRecepcionista());
            
            programacionCitaService.saveOrUpdate(programacionCitaActualizado);
        }
        return "redirect:/programacioncitas";
    }
    
    @GetMapping("/programacioncitas/{id}")
    public String deleteProgramacionCita(@PathVariable Long id){
        programacionCitaService.delete(id);
        return "redirect:/programacioncitas";
    }

    /*@GetMapping("/{programacionCitaId}")
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
    }*/
}
