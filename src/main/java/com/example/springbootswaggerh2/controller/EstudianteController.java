package com.example.springbootswaggerh2.controller;

import com.example.springbootswaggerh2.model.Estudiante;
import com.example.springbootswaggerh2.service.EstudianteService;
import com.example.springbootswaggerh2.service.EstudianteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/estudiantes")
@Api(value = "Estudiante service", description = "Service to manage estudiantes")
public class EstudianteController {

    @Autowired
    EstudianteService estudianteService;

    @PostMapping()
    @ApiOperation(value = "Creacion de estudiantes", response = Estudiante.class)
    @ResponseStatus(value = HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(code = 201, response = Estudiante.class, message = "Estudiante created successfully"),
            @ApiResponse(code = 400, message = "Invalid estduainte request"),
            @ApiResponse(code = 401, message = "You are not authorized to access the resource"),
            @ApiResponse(code = 403, message = "You are forbidden to create estudiantes"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public Estudiante createEstudiantes(@Valid @RequestBody Estudiante estudiante) {
        return estudianteService.createEstudiante(estudiante);
    }

    @GetMapping()
    @ApiOperation(value = "View list of estudiantes", response = Estudiante.class, responseContainer = "List" )
    @ResponseStatus(value = HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved estudiante list", response = Estudiante.class, responseContainer = "List"),
            @ApiResponse(code = 401, message = "You are not authorized to access the resource"),
            @ApiResponse(code = 403, message = "You are forbidden to view estudiante list"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public List<Estudiante> getEstudiantes() {
        return estudianteService.getEstudiantes();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "View estudiante details by id", response = Estudiante.class)
    @ResponseStatus(value = HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(code = 200, response = Estudiante.class, message = "Successfully retrieved estudiante by id"),
            @ApiResponse(code = 400, message = "Invalid request"),
            @ApiResponse(code = 401, message = "You are not authorized to access the resource"),
            @ApiResponse(code = 403, message = "You are forbidden to view estudiante by id"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public Estudiante getEstudianteById(@PathVariable(value = "id") Integer id) {
        return estudianteService.getEstudianteById(id);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update estudiante details by id", response = Estudiante.class)
    @ResponseStatus(value = HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(code = 200, response = Estudiante.class, message = "Successfully updated estudiante by id"),
            @ApiResponse(code = 400, message = "Invalid request"),
            @ApiResponse(code = 401, message = "You are not authorized to access the resource"),
            @ApiResponse(code = 403, message = "You are forbidden to update estudiante by id"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public Estudiante updateEstudianteById(@Valid @RequestBody Estudiante estudiante, @PathVariable(value = "id") Integer id) {
        return estudianteService.updateEstudianteById(id, estudiante);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete estudiante details by id", response = Estudiante.class)
    @ResponseStatus(value = HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(code = 200, response = Estudiante.class, message = "Successfully deleted estudiante by id"),
            @ApiResponse(code = 400, message = "Invalid request"),
            @ApiResponse(code = 401, message = "You are not authorized to access the resource"),
            @ApiResponse(code = 403, message = "You are forbidden to delete estudiante by id"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public ResponseEntity<?> deleteEstudianteById(@PathVariable(value = "id") Integer id) {
        estudianteService.deleteEstudianteById(id);
        return ResponseEntity.ok().build();
    }
}
