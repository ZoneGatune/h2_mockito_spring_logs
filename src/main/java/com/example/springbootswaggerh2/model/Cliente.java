package com.example.springbootswaggerh2.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "CLIENTE")
@ApiModel(description = "Cliente atributos")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "Auto generated cliente id")
	private long id;
	
	@Column(name = "NOMBRE")
    @NotBlank
    @NotNull
	@ApiModelProperty(notes = "Cliente nombre")
	private String nombre;
	
	@Column(name = "APELLIDO_PATERNO")
    @NotNull
	@ApiModelProperty(notes = "Cliente apellido paterno")
	private String apellidoPaterno;
	
	@Column(name = "APELLIDO_MATERNO")
    @NotBlank
    @NotNull
	@ApiModelProperty(notes = "Cliente apellido Materno")
	private String apellidoMaterno;

    @Column(name = "FECHA_CREACION")
    @ApiModelProperty(notes = "Cliente Fecha creacion")
    private Date fechaCreacion;

    @Column(name = "ESTADO")
    @ApiModelProperty(notes = "cliente estado")
    private Boolean estado;

    public Cliente() {
        this.fechaCreacion = new Date();
        this.estado = true;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }



    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellidoPaterno=" + apellidoPaterno +
                ", apellidoMaterno='" + apellidoMaterno + '\'' +
                ", fechaCreacion=" + fechaCreacion +
                ", estado=" + estado +
                '}';
    }
}
