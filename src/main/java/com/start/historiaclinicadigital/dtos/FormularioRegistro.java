package com.start.historiaclinicadigital.dtos;

import java.time.LocalDateTime;

public class FormularioRegistro {
    private LocalDateTime fecha_hora;
    private float tension_arterial;
    private float frecuencia_cardiaca;
    private float frecuencia_respiratoria;
    private float temperatura;
    private String observaciones;

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
