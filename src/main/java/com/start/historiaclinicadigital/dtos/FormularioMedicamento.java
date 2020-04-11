package com.start.historiaclinicadigital.dtos;

public class FormularioMedicamento {
    private String nombre;
    private String dosis;

    public FormularioMedicamento(String nombre, String dosis) {
        this.nombre = nombre;
        this.dosis = dosis;
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

    public boolean checkForNullOrEmpty(){
        return this.getDosis() == null ||
                this.getDosis().isEmpty() ||
                this.getNombre() == null ||
                this.getDosis().isEmpty();
    }
}
