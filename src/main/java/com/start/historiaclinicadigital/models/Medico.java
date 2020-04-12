package com.start.historiaclinicadigital.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.*;
import java.util.stream.Collectors;

@Entity
public class Medico extends Persona {
    private int matricula;
    private boolean activo;

    @OneToMany(mappedBy = "medico",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<HistoriaClinica> historiaClinicas = new HashSet<>();

    public Medico(){}

    public Medico(String nombre, String apellido, String email, int documento, int matricula, String password, String telefono) {
        super(nombre, apellido, email, documento,password, telefono);
        this.matricula = matricula;
        this.activo = false;
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

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Map<String,Object> MedicoDTO(){
        Map<String,Object> dto = new LinkedHashMap<>();
        dto.put("id",this.getId());
        dto.put("nombre",this.getNombre());
        dto.put("apellido",this.getApellido());
        dto.put("email",this.getEmail());
        dto.put("telefono",this.getTelefono());
        dto.put("matricula",this.getMatricula());

        return dto;
    }
}
