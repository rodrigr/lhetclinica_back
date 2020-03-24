package com.start.historiaclinicadigital.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

@Entity
public class ContactoEmergencia extends Persona {

    private String telefono;
    private String telefono2;
    private String relacion;
    private String direccion;



    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="Paciente_id")
    private Paciente paciente;



    public ContactoEmergencia(){};

    public ContactoEmergencia(String nombre, String apellido, String email, String telefono, String telefono2, String mail, String relacion, String direccion) {
        super(nombre, apellido, email);
        this.telefono = telefono;
        this.telefono2 = telefono2;
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

    //DTO

    public Map<String,Object> ContactoEmergenciaDTO(){
        Map<String,Object> dto = new LinkedHashMap<>();
        dto.put("id",this.getId());
        dto.put("nombre",this.getNombre());
        dto.put("apellido",this.getApellido());
        dto.put("email",this.getEmail());
        dto.put("telefono",this.getTelefono());
        dto.put("telefono2",this.getTelefono2());
        dto.put("relacion",this.getRelacion());
        dto.put("direccio",this.getDireccion());

        return dto;

    }

}
