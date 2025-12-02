package com.example.springbootswaggerh2.service;

import com.example.springbootswaggerh2.exception.ResourceNotFoundException;
import com.example.springbootswaggerh2.model.Estudiante;
import com.example.springbootswaggerh2.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstudianteServiceImpl implements EstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Override
    public Estudiante createEstudiante(Estudiante estudiante) {
        return estudianteRepository.save(estudiante);
    }

    @Override
    public Estudiante getEstudianteById(Integer id) {
        return estudianteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Estudiantes", "id", "estudianteId"));
    }

    @Override
    public List<Estudiante> getEstudiantes() {
        return estudianteRepository.findAll();
    }

    @Override
    public Estudiante updateEstudianteById(Integer id, Estudiante estudiante) {
        Estudiante student = this.getEstudianteById(id);
        student.setApellido(estudiante.getApellido());
        student.setNombre(estudiante.getNombre());
        student.setEmail(estudiante.getEmail());
        student.setPromedio(estudiante.getPromedio());
        student.setCreditos(estudiante.getCreditos());
        student.setSemestre(estudiante.getSemestre());
        return estudianteRepository.save(student);
    }

    @Override
    public void deleteEstudianteById(Integer id) {
        Estudiante student = this.getEstudianteById(id);
        estudianteRepository.delete(student);
    }
}
