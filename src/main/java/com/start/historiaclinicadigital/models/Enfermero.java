package com.start.historiaclinicadigital.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.*;
import java.util.stream.Collectors;

@Entity
public class Enfermero extends Persona {

    private boolean activo;

    @OneToMany(mappedBy = "enfermero",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<RegistroEnfermeria> registros = new HashSet<>();

    public Enfermero() {
    }

    public Enfermero(String nombre, String apellido, String email, int documento, String password, String telefono) {
        super(nombre, apellido, email, documento, password,telefono);
        this.activo = false;
    }

    public Set<RegistroEnfermeria> getRegistros() {
        return registros;
    }

    public void addRegistros(RegistroEnfermeria registro){
        registro.setEnfermero(this);
        this.registros.add(registro);
    }

    public List<Paciente> getPacientes(){
        return this.registros.stream().map(RegistroEnfermeria::getPaciente).collect(Collectors.toList());
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Map<String,Object> EnfermeroDTO(){
        Map<String,Object> dto = new LinkedHashMap<>();
        dto.put("id",this.getId());
        dto.put("nombre",this.getNombre());
        dto.put("apellido",this.getApellido());
        dto.put("email",this.getEmail());
        dto.put("telefono",this.getTelefono());
        dto.put("documento",this.getDocumento());
        return dto;
    }
}
