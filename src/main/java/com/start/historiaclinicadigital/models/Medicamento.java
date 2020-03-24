package com.start.historiaclinicadigital.models;

import javax.persistence.*;

@Entity
public class Medicamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "registro_id")
    private RegistroEnfermeria registro;

    private String nombre;
    private String dosis;

    public Medicamento(){}

    public Medicamento(String nombre, String dosis) {
        this.nombre = nombre;
        this.dosis = dosis;
    }

    public long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDosis() {
        return dosis;
    }

    public void setDosis(String dosis) {
        this.dosis = dosis;
    }

    public RegistroEnfermeria getRegistro() {
        return registro;
    }

    public void setRegistro(RegistroEnfermeria registro) {
        this.registro = registro;
    }
}
