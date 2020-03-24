package com.start.historiaclinicadigital.models;

import javax.persistence.Entity;

@Entity
public class Enfermero extends Persona {

    public Enfermero() {
    }

    public Enfermero(String nombre, String apellido, String email, int documento, String password) {
        super(nombre, apellido, email, documento, password);
    }
}
