package com.start.historiaclinicadigital.models;

import javax.persistence.Entity;

@Entity
public class Admin extends Persona{

    public Admin() {
    }

    public Admin(String nombre, String apellido, String email, int documento, String password) {
        super(nombre, apellido, email, documento, password);
    }
}
