package com.start.historiaclinicadigital.models;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

@Entity
public class RegistroEnfermeria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="enfermero_id")
    private Enfermero enfermero;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="paciente_id")
    private Paciente paciente;

    @OneToMany(mappedBy = "registro", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Medicamento> medicamentos = new HashSet<>();

    private LocalDateTime fecha_hora;
    private float tension_arterial;
    private float frecuencia_cardiaca;
    private float frecuencia_respiratoria;
    private float temperatura;
    private String observaciones;

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

    public Enfermero getEnfermero() {
        return enfermero;
    }

    public void setEnfermero(Enfermero enfermero) {
        this.enfermero = enfermero;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Set<Medicamento> getMedicamentos() {
        return medicamentos;
    }

    public void addMedicamento (Medicamento medicamento){
        medicamento.setRegistro(this);
        this.medicamentos.add(medicamento);
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


    //DTOs
    public Map<String,Object> makeRegistroEnfermeriaDTO(){
        Map<String,Object> dto = new LinkedHashMap<>();
        dto.put("id",this.getId());
        dto.put("fechaHora",this.getFecha_hora());
        dto.put("tensionArterial",this.getTension_arterial());
        dto.put("frecuenciaCardiaca",this.getFrecuencia_cardiaca());
        dto.put("frecuenciaRespiratoria",this.getFrecuencia_respiratoria());
        dto.put("temperatura",this.getTemperatura());
        dto.put("observaciones",this.getObservaciones());
        return dto;
    }
}
