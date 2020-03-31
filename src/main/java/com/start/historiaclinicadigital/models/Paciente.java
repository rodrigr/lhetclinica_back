package com.start.historiaclinicadigital.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Entity
public class Paciente extends Persona {


    private LocalDate fecha_nacimiento;
    private String direccion;
    private String sexo;
    private String telefono;

    @OneToOne(mappedBy = "paciente")
    private Anamnesis anamnesis;
    @OneToMany(mappedBy = "paciente", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<ContactoEmergencia> contactoEmergencia = new HashSet<>();
    @OneToMany(mappedBy = "paciente", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<HistoriaClinica> historiaClinica = new HashSet<>();
    @OneToMany(mappedBy = "paciente", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<RegistroEnfermeria> registros = new HashSet<>();

    public Paciente(){};

    public Paciente(String nombre, String apellido, String email, int documento, LocalDate fecha_nacimiento, String direccion, String sexo, String telefono) {
        super(nombre, apellido, email, documento, telefono);
        this.fecha_nacimiento = fecha_nacimiento;
        this.direccion = direccion;
        this.sexo = sexo;
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

    public Set<ContactoEmergencia> getContactoEmergencia() {
        return contactoEmergencia;
    }

    public Set<HistoriaClinica> getHistoriaClinica() {
        return historiaClinica;
    }

    public Set<RegistroEnfermeria> getRegistros() {
        return registros;
    }

    public void addContactoEmergencia(ContactoEmergencia contactoEmergencia){
        contactoEmergencia.setPaciente(this);
        this.contactoEmergencia.add(contactoEmergencia);
    }

    public void addHistoriaClinica(HistoriaClinica historiaClinica){
        historiaClinica.setPaciente(this);
        this.historiaClinica.add(historiaClinica);
    }

    public void addRegistro(RegistroEnfermeria registro){
        registro.setPaciente(this);
        this.registros.add(registro);
    }

    public Anamnesis getAnamnesis() {
        return anamnesis;
    }

    public void setAnamnesis(Anamnesis anamnesis) {
        this.anamnesis = anamnesis;
    }

    //DTO
    public long ultimaHistoriaClinica(Set<HistoriaClinica> historiaClinicaSet){
        long maxId = historiaClinicaSet
                .stream()
                .max(Comparator.comparing(HistoriaClinica::getId))
                .get()
                .getId();
        return maxId;
    }

    public Map<String,Object> HistoriaClinicaDTO(){
        Map<String,Object> dto = new LinkedHashMap<>();
        dto.put("historiasClinicas", this.getHistoriaClinica()
                        .stream()
                        .map(HistoriaClinica::makeHistoriaClinicaDTO)
                        .collect(Collectors.toList())
                );
        return dto;
    }

    public Map<String,Object> RegistroEnfermeriaDTO(){
        Map<String,Object> dto = new LinkedHashMap<>();
        dto.put("registrosEnfermeria", this.getRegistros()
                .stream()
                .map(RegistroEnfermeria::makeRegistroEnfermeriaDTO)
                .collect(Collectors.toList())
        );
        return dto;
    }

    public Map<String,Object> PacienteDTO(){
        Map<String,Object> dto = new LinkedHashMap<>();
        dto.put("id",this.getId());
        dto.put("nombre",this.getNombre());
        dto.put("apellido",this.getApellido());
        dto.put("documento",this.getDocumento());
        dto.put("email",this.getEmail());
        dto.put("sexo",this.getSexo());
        dto.put("direccion",this.getDireccion());
        dto.put("telefono",this.getTelefono());
        if(this.getAnamnesis() != null)
            dto.put("anamnesis",this.getAnamnesis().AnamnesisDTO());
        else
            dto.put("anamnesis",null);
        if(this.getContactoEmergencia().size() > 0){
            dto.put("contactosEmergencia",this.getContactoEmergencia()
                    .stream()
                    .map(ContactoEmergencia::ContactoEmergenciaDTO)
                    .collect(Collectors.toList())

            );
        }else{
            dto.put("contactosEmergencia",null);
        }
        if(this.getHistoriaClinica().size() > 0){
            dto.put("estado",this.getHistoriaClinica()
                    .stream()
                    .filter(historiaClinica -> historiaClinica.getId() == ultimaHistoriaClinica(this.getHistoriaClinica())).findFirst().get().makeHistoriaClinicaDTO()
            );
        }else{
            dto.put("estado",null);
        }

    return dto;
    }

    public Map<String,Object> pacienteDTOforData(){
        Map<String,Object> dto = new LinkedHashMap<>();
        dto.put("id",this.getId());
        dto.put("sexo",this.getSexo());
        if(this.getAnamnesis() != null)
            dto.put("anamnesis",this.getAnamnesis().AnamnesisDTO());
        else
            dto.put("anamnesis",null);
        if(this.getHistoriaClinica().size() > 0){
            dto.put("historiasClinicas", this.getHistoriaClinica().stream().map(HistoriaClinica::makeHistoriaClinicaDTO));
        }else{
            dto.put("historiasClinicas",null);
        }

        return dto;
    }




}
