package com.example.ClinicaVetSENA.controller;

import com.example.ClinicaVetSENA.entity.Paciente;
import com.example.ClinicaVetSENA.service.ClienteService;
import com.example.ClinicaVetSENA.service.PacienteService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/pacientes")
    public String getAll(Model model) {
        model.addAttribute("pacientes", pacienteService.getPacientes());
        return "pacientes";
    }

    @GetMapping("/pacientes/nuevo")
    public String getPacientes(Model model) {
        Paciente paciente = new Paciente();
        model.addAttribute("paciente", paciente);
        return "crear_paciente";
    }

    @PostMapping("/pacientes")
    public String saveUpdate(@ModelAttribute("paciente") Paciente paciente, Model model) {
        try {
            // Verificar si el cliente existe
            if (clienteService.getCliente(paciente.getCliente().getId()).isPresent()) {
                // Guardar el paciente si el cliente existe
                pacienteService.saveOrUpdate(paciente);
                return "redirect:/pacientes";
            } else {
                // Cliente no encontrado, mostrar mensaje de error
                model.addAttribute("error", "Cliente no encontrado. Verifique el ID del cliente.");
                return "crear_paciente";
            }
        } catch (Exception e) {
            // Capturar la excepción y mostrar mensaje de error
            model.addAttribute("error", "Error al guardar el paciente. Verifique los datos.");
            return "crear_paciente";
        }
    }

    @GetMapping("/pacientes/editar/{id}")
    public String getById(@PathVariable Long id, Model model) {
        model.addAttribute("paciente", pacienteService.getPaciente(id));
        return "editar_paciente";
    }

    @PostMapping("/pacientes/{id}")
    public String updatePaciente(@PathVariable Long id, @ModelAttribute("paciente") Paciente paciente) {
        Optional<Paciente> pacienteExistente = pacienteService.getPaciente(id);

        if (pacienteExistente.isPresent()) {
            Paciente pacienteActualizado = pacienteExistente.get();
            pacienteActualizado.setNombre(paciente.getNombre());
            pacienteActualizado.setEspecie(paciente.getEspecie());
            pacienteActualizado.setRaza(paciente.getRaza());
            pacienteActualizado.setSexo(paciente.getSexo());
            pacienteActualizado.setColor(paciente.getColor());
            pacienteActualizado.setPelaje(paciente.getPelaje());
            pacienteActualizado.setFechaNacimiento(paciente.getFechaNacimiento());

            pacienteService.saveOrUpdate(pacienteActualizado);
        }

        return "redirect:/pacientes";
    }

    @GetMapping("/pacientes/{id}")
    public String deletePaciente(@PathVariable Long id) {
        pacienteService.delete(id);
        return "redirect:/pacientes";
    }

    @GetMapping("/pacientes/buscar")
    public String buscarPacientes(@RequestParam(value = "search", required = false) String search, Model model) {
        if (search != null && !search.isEmpty()) {
            // Realizar la búsqueda solo si se proporciona un valor en el parámetro "search"
            List<Paciente> resultados = pacienteService.buscarPacientes(search);
            model.addAttribute("pacientes", resultados);
        } else {
            // Si no se proporciona un valor en "search", mostrar todos los pacientes
            List<Paciente> todosLosPacientes = pacienteService.getPacientes();
            model.addAttribute("pacientes", todosLosPacientes);
        }

        return "resultados_busqueda";
    }
}
