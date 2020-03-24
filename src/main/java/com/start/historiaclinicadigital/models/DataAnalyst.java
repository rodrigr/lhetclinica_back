package com.start.historiaclinicadigital.models;

import javax.persistence.Entity;

@Entity
public class DataAnalyst extends Persona {

    public DataAnalyst(String nombre, String apellido, String email, int documento, String password) {
        super(nombre, apellido, email, documento, password);
    }


}
