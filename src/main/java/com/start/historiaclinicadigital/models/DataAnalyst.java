package com.start.historiaclinicadigital.models;

import javax.persistence.Entity;
import java.util.LinkedHashMap;
import java.util.Map;

@Entity
public class DataAnalyst extends Persona {

    private boolean activo;

    public  DataAnalyst(){}

    public DataAnalyst(String nombre, String apellido, String email, int documento, String password) {
        super(nombre, apellido, email, documento, password);
        this.activo = false;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Map<String,Object> dataAnalystDTO(){
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
