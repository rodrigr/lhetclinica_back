package com.start.historiaclinicadigital.dtos;

public class FormularioContacto {
    private String nombreEmergencia;
    private String apellidoEmergencia;
    private String emailEmergencia;
    private String telefonoEmergencia;
    private String telefono2Emergencia;
    private String relacion;
    private String direccionEmergencia;

    public FormularioContacto(String nombreEmergencia, String apellidoEmergencia, String emailEmergencia, String telefonoEmergencia, String telefono2Emergencia, String relacion, String direccionEmergencia) {
        this.nombreEmergencia = nombreEmergencia;
        this.apellidoEmergencia = apellidoEmergencia;
        this.emailEmergencia = emailEmergencia;
        this.telefonoEmergencia = telefonoEmergencia;
        this.telefono2Emergencia = telefono2Emergencia;
        this.relacion = relacion;
        this.direccionEmergencia = direccionEmergencia;
    }

    public String getNombreEmergencia() {
        return nombreEmergencia;
    }

    public void setNombreEmergencia(String nombreEmergencia) {
        this.nombreEmergencia = nombreEmergencia;
    }

    public String getApellidoEmergencia() {
        return apellidoEmergencia;
    }

    public void setApellidoEmergencia(String apellidoEmergencia) {
        this.apellidoEmergencia = apellidoEmergencia;
    }

    public String getEmailEmergencia() {
        return emailEmergencia;
    }

    public void setEmailEmergencia(String emailEmergencia) {
        this.emailEmergencia = emailEmergencia;
    }

    public String getTelefonoEmergencia() {
        return telefonoEmergencia;
    }

    public void setTelefonoEmergencia(String telefonoEmergencia) {
        this.telefonoEmergencia = telefonoEmergencia;
    }

    public String getTelefono2Emergencia() {
        return telefono2Emergencia;
    }

    public void setTelefono2Emergencia(String telefono2Emergencia) {
        this.telefono2Emergencia = telefono2Emergencia;
    }

    public String getRelacion() {
        return relacion;
    }

    public void setRelacion(String relacion) {
        this.relacion = relacion;
    }

    public String getDireccionEmergencia() {
        return direccionEmergencia;
    }

    public void setDireccionEmergencia(String direccionEmergencia) {
        this.direccionEmergencia = direccionEmergencia;
    }

    public boolean checkForNullOrEmpty(){
        return this.getNombreEmergencia() == null || this.getNombreEmergencia().isEmpty() ||
                this.getApellidoEmergencia() == null || this.getApellidoEmergencia().isEmpty() ||
                this.getTelefonoEmergencia() == null || this.getTelefonoEmergencia().isEmpty() ||
                this.getRelacion() == null || this.getRelacion().isEmpty();
    }
}
