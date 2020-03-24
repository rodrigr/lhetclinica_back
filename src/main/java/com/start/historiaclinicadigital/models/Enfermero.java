package com.start.historiaclinicadigital.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Enfermero extends Persona {

    @OneToMany(mappedBy = "enfermero",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<RegistroEnfermeria> registros = new HashSet<>();

    public Enfermero() {
    }

    public Enfermero(String nombre, String apellido, String email, int documento, String password) {
        super(nombre, apellido, email, documento, password);
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
}
