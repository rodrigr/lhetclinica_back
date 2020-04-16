package com.start.historiaclinicadigital.models;

import javax.persistence.Entity;
import java.util.LinkedHashMap;
import java.util.Map;

@Entity
public class Admin extends Persona{

    public Admin() {
    }

    public Admin(String nombre, String apellido, String email, int documento, String password) {
        super(nombre, apellido, email, documento, password);
    }

    public Map<String,Object> adminDTO(){
        Map<String,Object> dto = new LinkedHashMap<>();
        dto.put("id",this.getId());
        dto.put("nombre",this.getNombre());
        dto.put("apellido",this.getApellido());
        dto.put("email",this.getEmail());
        dto.put("documento",this.getDocumento());
        return dto;
    }
}
