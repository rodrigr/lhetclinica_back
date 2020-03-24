package com.start.historiaclinicadigital.models;
import javax.persistence.*;
import java.util.Date;
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
    private Date fecha_viaje;
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

    public Anamnesis() {
    }

    public Anamnesis(boolean viaje, Date fecha_viaje, String destino_viaje, boolean embarazo, int semanas_gestacion, int embarazos_previos, String antecedentes_personales, String antecedentes_familiares, String medicacion_regular, String trabajo, int convivientes, String observaciones) {
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
    }

    public boolean isViaje() {
        return viaje;
    }

    public void setViaje(boolean viaje) {
        this.viaje = viaje;
    }

    public Date getFecha_viaje() {
        return fecha_viaje;
    }

    public void setFecha_viaje(Date fecha_viaje) {
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
        dto.put("observaciones",this.getObservaciones());

        return dto;

    }


}
