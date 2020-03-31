package com.start.historiaclinicadigital.dtos;

import java.time.LocalDateTime;

public class FormularioRegistro {
    private float tension_arterial;
    private float frecuencia_cardiaca;
    private float frecuencia_respiratoria;
    private float temperatura;
    private String observaciones;

    public FormularioRegistro(float tension_arterial, float frecuencia_cardiaca, float frecuencia_respiratoria, float temperatura, String observaciones) {
        this.tension_arterial = tension_arterial;
        this.frecuencia_cardiaca = frecuencia_cardiaca;
        this.frecuencia_respiratoria = frecuencia_respiratoria;
        this.temperatura = temperatura;
        this.observaciones = observaciones;
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

    public boolean checkForNullOrEmpty(){
        if(this.getTension_arterial() == 0 ||
        this.getFrecuencia_cardiaca() == 0 ||
        this.getFrecuencia_respiratoria() == 0 ||
        this.getTemperatura() == 0){
            return true;
        }

        return false;
    }
}
