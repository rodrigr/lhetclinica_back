package com.start.historiaclinicadigital.dtos;

import java.time.LocalDate;

public class FormularioPaciente {
    //Datos Personales
    private String nombre;
    private String apellido;
    private String email;
    private int documento;
    private LocalDate fecha_nacimiento;
    private String direccion;
    private String sexo;
    private String telefono;

    //Anamnesis
    private boolean viaje;
    private LocalDate fecha_viaje;
    private String destino_viaje;
    private boolean embarazo;
    private int semanas_gestacion;
    private int embarazos_previos;
    private String antecedentes_personales;
    private String antecedentes_familiares;
    private String medicacion_regular;
    private String trabajo;
    private int convivientes;
    private String observaciones;
    private boolean obra_social;
    private String nombre_obra_social;
    private String grupo_sanguineo;

    //Contacto de Emergencia
    private String nombreEmergencia;
    private String apellidoEmergencia;
    private String emailEmergencia;
    private String telefonoEmergencia;
    private String telefono2Emergencia;
    private String relacion;
    private String direccionEmergencia;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getDocumento() {
        return documento;
    }

    public void setDocumento(int documento) {
        this.documento = documento;
    }

    public LocalDate getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(LocalDate fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public boolean isViaje() {
        return viaje;
    }

    public void setViaje(boolean viaje) {
        this.viaje = viaje;
    }

    public LocalDate getFecha_viaje() {
        return fecha_viaje;
    }

    public void setFecha_viaje(LocalDate fecha_viaje) {
        this.fecha_viaje = fecha_viaje;
    }

    public String getDestino_viaje() {
        return destino_viaje;
    }

    public void setDestino_viaje(String destino_viaje) {
        this.destino_viaje = destino_viaje;
    }

    public boolean isEmbarazo() {
        return embarazo;
    }

    public void setEmbarazo(boolean embarazo) {
        this.embarazo = embarazo;
    }

    public int getSemanas_gestacion() {
        return semanas_gestacion;
    }

    public void setSemanas_gestacion(int semanas_gestacion) {
        this.semanas_gestacion = semanas_gestacion;
    }

    public int getEmbarazos_previos() {
        return embarazos_previos;
    }

    public void setEmbarazos_previos(int embarazos_previos) {
        this.embarazos_previos = embarazos_previos;
    }

    public String getAntecedentes_personales() {
        return antecedentes_personales;
    }

    public void setAntecedentes_personales(String antecedentes_personales) {
        this.antecedentes_personales = antecedentes_personales;
    }

    public String getAntecedentes_familiares() {
        return antecedentes_familiares;
    }

    public void setAntecedentes_familiares(String antecedentes_familiares) {
        this.antecedentes_familiares = antecedentes_familiares;
    }

    public String getMedicacion_regular() {
        return medicacion_regular;
    }

    public void setMedicacion_regular(String medicacion_regular) {
        this.medicacion_regular = medicacion_regular;
    }

    public String getTrabajo() {
        return trabajo;
    }

    public void setTrabajo(String trabajo) {
        this.trabajo = trabajo;
    }

    public int getConvivientes() {
        return convivientes;
    }

    public void setConvivientes(int convivientes) {
        this.convivientes = convivientes;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public boolean isObra_social() {
        return obra_social;
    }

    public void setObra_social(boolean obra_social) {
        this.obra_social = obra_social;
    }

    public String getNombre_obra_social() {
        return nombre_obra_social;
    }

    public void setNombre_obra_social(String nombre_obra_social) {
        this.nombre_obra_social = nombre_obra_social;
    }

    public String getGrupo_sanguineo() {
        return grupo_sanguineo;
    }

    public void setGrupo_sanguineo(String grupo_sanguineo) {
        this.grupo_sanguineo = grupo_sanguineo;
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
}
