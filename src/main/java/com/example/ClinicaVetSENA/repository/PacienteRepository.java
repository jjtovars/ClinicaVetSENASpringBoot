package com.example.ClinicaVetSENA.repository;

import com.example.ClinicaVetSENA.entity.Paciente;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long>{
    List<Paciente> findByNombreContainingIgnoreCaseOrIdOrEspecieContainingIgnoreCase(String nombre, Long id, String especie);
    //List<Paciente> findByNombreContainingIgnoreCaseOrIdContainingIgnoreCaseOrEspecieContainingIgnoreCase(String nombre, String id, String especie);
}

