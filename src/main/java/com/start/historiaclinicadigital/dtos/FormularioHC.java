package com.start.historiaclinicadigital.dtos;

public class FormularioHC {
    private String diagnostico;
    private double temperatura;
    private String sintomas;
    private String tratamiento;
    private String observaciones;

    public FormularioHC(String diagnostico, double temperatura, String sintomas, String tratamiento, String observaciones) {
        this.diagnostico = diagnostico;
        this.temperatura = temperatura;
        this.sintomas = sintomas;
        this.tratamiento = tratamiento;
        this.observaciones = observaciones;
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

    public boolean checkForNullOrEmpty(){
        if(this.getDiagnostico() == null ||
                this.getDiagnostico().isEmpty() ||
                this.getTemperatura() == 0 ||
                this.getSintomas() == null ||
                this.getSintomas().isEmpty() ||
                this.getTratamiento() == null ||
                this.getTratamiento().isEmpty()
        ){
            return true;
        }

        return false;
    }
}
