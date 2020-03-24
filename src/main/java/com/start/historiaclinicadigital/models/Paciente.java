package com.start.historiaclinicadigital.models;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Paciente extends Persona {


    private Date fecha_nacimiento;
    private String direccion;
    private String sexo;
    private String telefono;

    @OneToMany(mappedBy = "paciente", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<ContactoEmergencia> contactoEmergencia = new HashSet<>();
    @OneToMany(mappedBy = "paciente", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<HistoriaClinica> historiaClinica = new HashSet<>();
    @OneToMany(mappedBy = "paciente", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<RegistroEnfermeria> registros = new HashSet<>();
    @OneToOne(mappedBy = "paciente")
    private Anamnesis anamnesis;

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

    public Set<ContactoEmergencia> getContactoEmergencia() {
        return contactoEmergencia;
    }

    public Set<HistoriaClinica> getHistoriaClinica() {
        return historiaClinica;
    }

    public Set<RegistroEnfermeria> getRegistros() {
        return registros;
    }

    public void addContactoEmergencia(ContactoEmergencia contactoEmergencia){
        contactoEmergencia.setPaciente(this);
        this.contactoEmergencia.add(contactoEmergencia);
    }

    public void addHistoriaClinica(HistoriaClinica historiaClinica){
        historiaClinica.setPaciente(this);
        this.historiaClinica.add(historiaClinica);
    }

    public void addRegistro(RegistroEnfermeria registro){
        registro.setPaciente(this);
        this.registros.add(registro);
    }

    public Anamnesis getAnamnesis() {
        return anamnesis;
    }

    public void setAnamnesis(Anamnesis anamnesis) {
        this.anamnesis = anamnesis;
    }
}