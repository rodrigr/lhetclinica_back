package com.start.historiaclinicadigital.models;

import com.start.historiaclinicadigital.enums.PCR;

import java.time.LocalDateTime;
import java.util.HashSet;
import javax.persistence.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
@Entity
public class HistoriaClinica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    private LocalDateTime fecha_hora;
    private String motivo_consulta;
    @Enumerated(EnumType.STRING)
    private PCR diagnostico;
    private double temperatura;
    private String sintomas;
    private String tratamiento;
    private String observaciones;
    @OneToMany(mappedBy = "historiaClinica", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Medicamento> medicamentos = new HashSet<>();
    @OneToOne(mappedBy = "historiaClinica")
    private Hemograma hemograma;
    @OneToOne(mappedBy = "historiaClinica")
    private Eritrosedimentacion eritrosedimentacion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="medico_id")
    private Medico medico;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="Paciente_id")
    private Paciente paciente;



    public HistoriaClinica() {
    }

    public HistoriaClinica(LocalDateTime fecha_hora, String motivo_consulta, PCR diagnostico, double temperatura, String sintomas, String tratamiento, String observaciones, Medico medico, Paciente paciente) {
        this.fecha_hora = fecha_hora;
        this.motivo_consulta = motivo_consulta;
        this.diagnostico = diagnostico;
        this.temperatura = temperatura;
        this.sintomas = sintomas;
        this.tratamiento = tratamiento;
        this.observaciones = observaciones;
        this.medico = medico;
        this.paciente = paciente;
    }

    public LocalDateTime getFecha_hora() {
        return fecha_hora;
    }

    public void setFecha_hora(LocalDateTime fecha_hora) {
        this.fecha_hora = fecha_hora;
    }

    public String getMotivo_consulta() {
        return motivo_consulta;
    }

    public void setMotivo_consulta(String motivo_consulta) {
        this.motivo_consulta = motivo_consulta;
    }

    public PCR getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(PCR diagnostico) {
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

    public Set<Medicamento> getMedicamentos() {
        return medicamentos;
    }

    public void addMedicamento (Medicamento medicamento){
        medicamento.setHistoriaClinica(this);
        this.medicamentos.add(medicamento);
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
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

    public Hemograma getHemograma() {
        return hemograma;
    }

    public void setHemograma(Hemograma hemograma) {
        this.hemograma = hemograma;
    }

    public Eritrosedimentacion getEritrosedimentacion() {
        return eritrosedimentacion;
    }

    public void setEritrosedimentacion(Eritrosedimentacion eritrosedimentacion) {
        this.eritrosedimentacion = eritrosedimentacion;
    }

    //DTOs
    public Map<String,Object> makeHistoriaClinicaDTO(){
        Map<String,Object> dto = new LinkedHashMap<>();
        dto.put("id",this.getId());
        dto.put("fechaHora",this.getFecha_hora());
        dto.put("motivoConsulta", this.getMotivo_consulta());
        dto.put("diagnostico",this.getDiagnostico());
        dto.put("temperatura",this.getTemperatura());
        dto.put("sintomas",this.getSintomas());
        dto.put("tratamiento",this.getTratamiento());
        dto.put("observaciones",this.getObservaciones());
        dto.put("medicamentos", this.getMedicamentos() != null ? this.getMedicamentos().stream().map(Medicamento::medicamentoDTO) : null);
        dto.put("hemograma", this.getHemograma() != null ? this.getHemograma().hemogramaDTO() : null);
        dto.put("eritrosedimentacion", this.getEritrosedimentacion() != null ? this.getEritrosedimentacion().getValor() : null);
        return dto;
    }


}
