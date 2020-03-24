package com.start.historiaclinicadigital.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class RegistroEnfermeria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    LocalDateTime fecha_hora;
    float tension_arterial;
    float frecuencia_cardiaca;
    float frecuencia_respiratoria;
    float temperatura;
    String observaciones;

    public RegistroEnfermeria(){}

    public RegistroEnfermeria(LocalDateTime fecha_hora, float tension_arterial, float frecuencia_cardiaca, float frecuencia_respiratoria, float temperatura, String observaciones) {
        this.fecha_hora = fecha_hora;
        this.tension_arterial = tension_arterial;
        this.frecuencia_cardiaca = frecuencia_cardiaca;
        this.frecuencia_respiratoria = frecuencia_respiratoria;
        this.temperatura = temperatura;
        this.observaciones = observaciones;
    }

    public long getId() {
        return id;
    }

    public LocalDateTime getFecha_hora() {
        return fecha_hora;
    }

    public void setFecha_hora(LocalDateTime fecha_hora) {
        this.fecha_hora = fecha_hora;
    }

    public float getTension_arterial() {
        return tension_arterial;
    }

    public void setTension_arterial(float tension_arterial) {
        this.tension_arterial = tension_arterial;
    }

    public float getFrecuencia_cardiaca() {
        return frecuencia_cardiaca;
    }

    public void setFrecuencia_cardiaca(float frecuencia_cardiaca) {
        this.frecuencia_cardiaca = frecuencia_cardiaca;
    }

    public float getFrecuencia_respiratoria() {
        return frecuencia_respiratoria;
    }

    public void setFrecuencia_respiratoria(float frecuencia_respiratoria) {
        this.frecuencia_respiratoria = frecuencia_respiratoria;
    }

    public float getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(float temperatura) {
        this.temperatura = temperatura;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
