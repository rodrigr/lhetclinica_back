package com.start.historiaclinicadigital.models;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

@Entity
public class Anamnesis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @OneToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

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

    public Anamnesis() {
    }

    public Anamnesis(boolean viaje, LocalDate fecha_viaje, String destino_viaje, boolean embarazo, int semanas_gestacion, int embarazos_previos, String antecedentes_personales, String antecedentes_familiares, String medicacion_regular, String trabajo, int convivientes, String observaciones, boolean obra_social, String nombre_obra_social, String grupo_sanguineo,Paciente paciente) {
        this.viaje = viaje;
        this.fecha_viaje = fecha_viaje;
        this.destino_viaje = destino_viaje;
        this.embarazo = embarazo;
        this.semanas_gestacion = semanas_gestacion;
        this.embarazos_previos = embarazos_previos;
        this.antecedentes_personales = antecedentes_personales;
        this.antecedentes_familiares = antecedentes_familiares;
        this.medicacion_regular = medicacion_regular;
        this.trabajo = trabajo;
        this.convivientes = convivientes;
        this.observaciones = observaciones;
        this.paciente = paciente;
        this.obra_social = obra_social;
        this.nombre_obra_social = nombre_obra_social;
        this.grupo_sanguineo = grupo_sanguineo;
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

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public long getId() {
        return id;
    }

    public boolean isObra_social() {
        return obra_social;
    }

    public void setObra_social(boolean obra_social) {
        this.obra_social = obra_social;
    }

    public String getGrupo_sanguineo() {
        return grupo_sanguineo;
    }

    public void setGrupo_sanguineo(String grupo_sanguineo) {
        this.grupo_sanguineo = grupo_sanguineo;
    }

    public String getNombre_obra_social() {
        return nombre_obra_social;
    }

    public void setNombre_obra_social(String nombre_obra_social) {
        this.nombre_obra_social = nombre_obra_social;
    }

    //DTO
    public Map<String,Object> AnamnesisDTO(){
        Map<String,Object> dto = new LinkedHashMap<>();
        dto.put("id",this.getId());
        dto.put("viaje",this.isViaje());
        dto.put("fechaViaje",this.getFecha_viaje());
        dto.put("destinoViaje",this.getDestino_viaje());
        dto.put("embarazo",this.isEmbarazo());
        dto.put("semanasGestacion",this.getSemanas_gestacion());
        dto.put("embarazosPrevios",this.getEmbarazos_previos());
        dto.put("antecedentesPersonales",this.getAntecedentes_personales());
        dto.put("antecedentesFamiliares",this.getAntecedentes_familiares());
        dto.put("medicacionRegular",this.getMedicacion_regular());
        dto.put("convivientes",this.getConvivientes());
        dto.put("obraSocial", this.isObra_social());
        dto.put("nombreObraSocial", this.getNombre_obra_social());
        dto.put("grupoSanguineo",this.getGrupo_sanguineo());
        dto.put("observaciones",this.getObservaciones());

        return dto;

    }


}
