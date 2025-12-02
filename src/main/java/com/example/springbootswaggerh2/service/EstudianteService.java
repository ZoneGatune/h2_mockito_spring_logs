package com.example.springbootswaggerh2.service;

import com.example.springbootswaggerh2.model.Employee;
import com.example.springbootswaggerh2.model.Estudiante;

import java.util.List;

public interface EstudianteService {

    public Estudiante createEstudiante(Estudiante estudiante);
    public Estudiante getEstudianteById(Integer id);
    public List<Estudiante> getEstudiantes();
    public Estudiante updateEstudianteById(Integer id, Estudiante estudiante);
    public void deleteEstudianteById(Integer id);

}