package com.start.historiaclinicadigital.models;

import javax.persistence.Entity;

@Entity
public class Medico extends Persona {
    int matricula;

    public Medico(){}

    public Medico(String nombre, String apellido, String email, int documento, int matricula, String password) {
        super(nombre, apellido, email, documento,password);
        this.matricula = matricula;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }
}
