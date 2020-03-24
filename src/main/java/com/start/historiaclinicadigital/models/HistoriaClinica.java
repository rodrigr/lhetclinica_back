package com.start.historiaclinicadigital.models;

import java.util.Date;
import javax.persistence.Entity;

@Entity
public class HistoriaClinica {
    private Date fecha_hora;
    private String diagnostico;
    private float temperatura;
    private String sintomas;
    private String tratamiento;
    private String observaciones;

    public HistoriaClinica() {
    }

    public HistoriaClinica(Date fecha_hora, String diagnostico, float temperatura, String sintomas, String tratamiento, String observaciones) {
        this.fecha_hora = fecha_hora;
        this.diagnostico = diagnostico;
        this.temperatura = temperatura;
        this.sintomas = sintomas;
        this.tratamiento = tratamiento;
        this.observaciones = observaciones;
    }

    public Date getFecha_hora() {
        return fecha_hora;
    }

    public void setFecha_hora(Date fecha_hora) {
        this.fecha_hora = fecha_hora;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public float getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(float temperatura) {
        this.temperatura = temperatura;
    }

    public String getSintomas() {
        return sintomas;
    }

    public void setSintomas(String sintomas) {
        this.sintomas = sintomas;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
