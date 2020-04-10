package com.start.historiaclinicadigital.dtos;

import com.start.historiaclinicadigital.enums.PCR;

public class FormularioHC {
    private String diagnostico;
    private double temperatura;
    private String sintomas;
    private String tratamiento;
    private String observaciones;
    private PCR pcr;
    private float globulos_blancos;
    private float globulos_rojos;
    private float plaquetas;
    private float eritrosedimentacion;

    public FormularioHC(String diagnostico, double temperatura, String sintomas, String tratamiento, String observaciones, PCR pcr, float globulos_blancos, float globulos_rojos, float plaquetas, float eritrosedimentacion) {
        this.diagnostico = diagnostico;
        this.temperatura = temperatura;
        this.sintomas = sintomas;
        this.tratamiento = tratamiento;
        this.observaciones = observaciones;
        this.pcr = pcr;
        this.globulos_blancos = globulos_blancos;
        this.globulos_rojos = globulos_rojos;
        this.plaquetas = plaquetas;
        this.eritrosedimentacion = eritrosedimentacion;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
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

    public PCR getPcr() {
        return pcr;
    }

    public void setPcr(PCR pcr) {
        this.pcr = pcr;
    }

    public float getGlobulos_blancos() {
        return globulos_blancos;
    }

    public void setGlobulos_blancos(float globulos_blancos) {
        this.globulos_blancos = globulos_blancos;
    }

    public float getGlobulos_rojos() {
        return globulos_rojos;
    }

    public void setGlobulos_rojos(float globulos_rojos) {
        this.globulos_rojos = globulos_rojos;
    }

    public float getPlaquetas() {
        return plaquetas;
    }

    public void setPlaquetas(float plaquetas) {
        this.plaquetas = plaquetas;
    }

    public float getEritrosedimentacion() {
        return eritrosedimentacion;
    }

    public void setEritrosedimentacion(float eritrosedimentacion) {
        this.eritrosedimentacion = eritrosedimentacion;
    }

    public boolean checkForNullOrEmpty(){
        return this.getDiagnostico() == null ||
                this.getDiagnostico().isEmpty() ||
                this.getTemperatura() == 0 ||
                this.getSintomas() == null ||
                this.getSintomas().isEmpty() ||
                this.getTratamiento() == null ||
                this.getTratamiento().isEmpty() ||
                this.getPcr() == null;
    }

    public boolean checkNullHemograma(){
        return this.getGlobulos_blancos() == 0 ||
                this.getGlobulos_rojos() == 0 ||
                this.getPlaquetas() == 0;
    }

    public  boolean checkNullEritrosedimentacion(){
        return this.getEritrosedimentacion() == 0;
    }
}
