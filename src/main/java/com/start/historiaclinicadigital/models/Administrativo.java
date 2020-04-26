package com.start.historiaclinicadigital.models;

import javax.persistence.Entity;
import java.util.LinkedHashMap;
import java.util.Map;

@Entity
public class Administrativo extends Persona {
    private boolean activo;

    public Administrativo(){}

    public Administrativo(String nombre, String apellido, String email, int documento, String password, String telefono) {
        super(nombre, apellido, email, documento, password,telefono);
        this.activo = false;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Map<String,Object> administrativoDTO(){
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
