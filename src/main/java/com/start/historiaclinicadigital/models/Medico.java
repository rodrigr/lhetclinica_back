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
public class Medico extends Persona {
    private int matricula;

    @OneToMany(mappedBy = "medico",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<HistoriaClinica> historiaClinicas = new HashSet<>();

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

    public Set<HistoriaClinica> getHistoriasClinicas() {
        return historiaClinicas;
    }

    public void addHistoriaClinica(HistoriaClinica historiaClinica) {
        historiaClinica.setMedico(this);
        this.historiaClinicas.add(historiaClinica);
    }

    public List<Paciente> getPacientes(){
        return this.historiaClinicas.stream().map(HistoriaClinica::getPaciente).collect(Collectors.toList());
    }
}
