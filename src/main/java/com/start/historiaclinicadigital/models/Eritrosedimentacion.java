package com.start.historiaclinicadigital.models;

import javax.persistence.*;

@Entity
public class Eritrosedimentacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private float valor;

    @OneToOne
    @JoinColumn(name = "hc_id")
    private HistoriaClinica historiaClinica;

    public Eritrosedimentacion() {
    }

    public Eritrosedimentacion(float valor, HistoriaClinica historiaClinica) {
        this.valor = valor;
        this.historiaClinica = historiaClinica;
    }

    public long getId() {
        return id;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public HistoriaClinica getHistoriaClinica() {
        return historiaClinica;
    }

    public void setHistoriaClinica(HistoriaClinica historiaClinica) {
        this.historiaClinica = historiaClinica;
    }
}
