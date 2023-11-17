package com.example.ClinicaVetSENA.controller;

import com.example.ClinicaVetSENA.entity.Cliente;
import com.example.ClinicaVetSENA.service.ClienteService;
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
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/clientes")
    public String getAll(Model model) {
        model.addAttribute("clientes", clienteService.getClientes());
        return "clientes";
    }

    @GetMapping("/clientes/nuevo")
    public String mostrarCliente(Model model) {
        Cliente cliente = new Cliente();
        model.addAttribute("cliente", cliente);
        return "crear_cliente";
    }

    @PostMapping("/clientes")
    public String saveUpdate(@ModelAttribute("cliente") Cliente cliente) {
        clienteService.saveOrUpdate(cliente);
        return "redirect:/clientes";
    }

    @GetMapping("/clientes/editar/{id}")
    public String getById(@PathVariable Long id, Model model) {
        model.addAttribute("cliente", clienteService.getCliente(id));
        return "editar_cliente";
    }

    @PostMapping("/clientes/{id}")
    public String updateCliente(@PathVariable Long id, @ModelAttribute("cliente") Cliente cliente) {
        Optional<Cliente> clienteExistente = clienteService.getCliente(id);

        if (clienteExistente.isPresent()) {
            Cliente clienteActualizado = clienteExistente.get();
            clienteActualizado.setIdentificacion(cliente.getIdentificacion());
            clienteActualizado.setNombres(cliente.getNombres());
            clienteActualizado.setApellidos(cliente.getApellidos());
            clienteActualizado.setDepartamento(cliente.getDepartamento());
            clienteActualizado.setCiudad(cliente.getCiudad());
            clienteActualizado.setDireccion(cliente.getDireccion());
            clienteActualizado.setTelefono(cliente.getTelefono());
            clienteActualizado.setEmail(cliente.getEmail());

            clienteService.saveOrUpdate(clienteActualizado);
        }

        return "redirect:/clientes";
    }

    @GetMapping("/clientes/{id}")
    public String deleteCliente(@PathVariable Long id) {
        clienteService.delete(id);
        return "redirect:/clientes";
    }
}
