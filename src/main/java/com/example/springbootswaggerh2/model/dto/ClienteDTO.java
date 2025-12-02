package com.example.springbootswaggerh2.model.dto;

import com.example.springbootswaggerh2.model.Cliente;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class ClienteDTO  {
    private String nombreCompleto;
    private Long id;

    public ClienteDTO(Cliente cliente) {
        // Copiar campos necesarios
        this.setId(cliente.getId());
        this.nombreCompleto = cliente.getNombre() + " " + cliente.getApellidoPaterno() + " " + cliente.getApellidoMaterno();
    }
}
