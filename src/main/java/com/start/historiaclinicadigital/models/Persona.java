package com.start.historiaclinicadigital.models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    private String nombre;
    private String apellido;
    private String email;
    private int documento;
    private String password;
    private String telefono;

    public Persona (){}

    public Persona(String nombre, String apellido, String email, int documento, String password, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.documento = documento;
        this.password = password;
        this.telefono = telefono;
    }

    public Persona(String nombre, String apellido, String email, int documento, String password) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.documento = documento;
        this.password = password;
    }

    public Persona(String nombre, String apellido, String email, int documento) {

        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.documento = documento;
    }

    public Persona(String nombre, String apellido, String email) {

        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
    }

    public Persona(String nombre, String apellido, String email, String telefono, int documento) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.documento = documento;
    }

    public long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getDocumento() {
        return documento;
    }

    public void setDocumento(int documento) {
        this.documento = documento;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelefono() { return telefono; }

    public void setTelefono(String telefono) { this.telefono = telefono; }
}
