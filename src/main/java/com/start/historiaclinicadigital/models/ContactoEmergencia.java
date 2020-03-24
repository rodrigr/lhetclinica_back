package com.start.historiaclinicadigital.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class ContactoEmergencia extends Persona {

    private String telefono;
    private String telefono2;
    private String mail;
    private String relacion;
    private String direccion;



    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="Paciente_id")
    private Paciente paciente;



    public ContactoEmergencia(){};

    public ContactoEmergencia(String nombre, String apellido, String email, int documento, String password, String telefono, String telefono2, String mail, String relacion, String direccion) {
        super(nombre, apellido, email, documento, password);
        this.telefono = telefono;
        this.telefono2 = telefono2;
        this.mail = mail;
        this.relacion = relacion;
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTelefono2() {
        return telefono2;
    }

    public void setTelefono2(String telefono2) {
        this.telefono2 = telefono2;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getRelacion() {
        return relacion;
    }

    public void setRelacion(String relacion) {
        this.relacion = relacion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
}
