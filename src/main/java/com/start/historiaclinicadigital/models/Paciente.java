package com.start.historiaclinicadigital.models;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class Paciente extends Persona {

    private Date fecha_nacimiento;
    private String direccion;
    private String sexo;
    private String telefono;

    public Paciente(){};

    public Paciente(String nombre, String apellido, String email, int documento, String password, Date fecha_nacimiento, String direccion, String sexo, String telefono) {
        super(nombre, apellido, email, documento, password);
        this.fecha_nacimiento = fecha_nacimiento;
        this.direccion = direccion;
        this.sexo = sexo;
        this.telefono = telefono;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
