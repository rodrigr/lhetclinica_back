package com.start.historiaclinicadigital.models;

import com.start.historiaclinicadigital.enums.Sexo;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Entity
public class Paciente extends Persona {


    private LocalDate fecha_nacimiento;
    private String direccion;
    @Enumerated(EnumType.STRING)
    private Sexo sexo;

    @OneToOne(mappedBy = "paciente")
    private Anamnesis anamnesis;
    @OneToMany(mappedBy = "paciente", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<ContactoEmergencia> contactoEmergencia = new HashSet<>();
    @OneToMany(mappedBy = "paciente", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<HistoriaClinica> historiaClinica = new HashSet<>();
    @OneToMany(mappedBy = "paciente", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<RegistroEnfermeria> registros = new HashSet<>();

    public Paciente(){};

    public Paciente(String nombre, String apellido, String email, int documento, LocalDate fecha_nacimiento, String direccion, Sexo sexo, String telefono) {
        super(nombre, apellido, email, telefono,documento);
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

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
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

    public long ultimoRegistro(Set<RegistroEnfermeria> registroEnfermeriaSet){
        long maxId = registroEnfermeriaSet
                .stream()
                .max(Comparator.comparing(RegistroEnfermeria::getId))
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
        dto.put("fechaNacimiento",this.getFecha_nacimiento());
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
        if(this.getRegistros().size() > 0){
            dto.put("registro",this.getRegistros()
                    .stream()
                    .filter(registroEnfermeria -> registroEnfermeria.getId() == ultimoRegistro(this.getRegistros())).findFirst().get().makeRegistroEnfermeriaDTO()
            );
        }else{
            dto.put("registro",null);
        }

    return dto;
    }

    public Map<String,Object> pacienteDTOforAdministrativos(){
        Map<String,Object> dto = new LinkedHashMap<>();
        dto.put("id",this.getId());
        dto.put("nombre",this.getNombre());
        dto.put("apellido",this.getApellido());
        dto.put("documento",this.getDocumento());
        dto.put("fechaNacimiento",this.getFecha_nacimiento());
        dto.put("email",this.getEmail());
        dto.put("sexo",this.getSexo());
        dto.put("direccion",this.getDireccion());
        dto.put("telefono",this.getTelefono());
        if(this.getContactoEmergencia().size() > 0){
            dto.put("contactosEmergencia",this.getContactoEmergencia()
                    .stream()
                    .map(ContactoEmergencia::ContactoEmergenciaDTO)
                    .collect(Collectors.toList())

            );
        }else{
            dto.put("contactosEmergencia",null);
        }

        return dto;
    }


}
