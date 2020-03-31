package com.start.historiaclinicadigital.controllers;

import com.start.historiaclinicadigital.dtos.FormularioHC;
import com.start.historiaclinicadigital.dtos.FormularioPaciente;
import com.start.historiaclinicadigital.dtos.FormularioRegistro;
import com.start.historiaclinicadigital.models.*;
import com.start.historiaclinicadigital.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api")
public class AppController {
    @Autowired
    MedicoRepository medicoRepository;
    @Autowired
    EnfermeroRepository enfermeroRepository;
    @Autowired
    PacienteRepository pacienteRepository;
    @Autowired
    HistoriaClinicaRepository historiaClinicaRepository;
    @Autowired
    RegistroEnfermeriaRepository registroEnfermeriaRepository;
    @Autowired
    AnamnesisRepository anamnesisRepository;
    @Autowired
    ContactoEmergenciaRepository contactoEmergenciaRepository;

    @GetMapping("/pacientes")
    public ResponseEntity<Map<String,Object>> getPacientes(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ResponseEntity<Map<String,Object>> responseEntity;
        Map<String, Object> dto = new LinkedHashMap<>();
        if(isGuest(authentication)){
            responseEntity = new ResponseEntity<>(makeMap("error", "unauthorized"), HttpStatus.UNAUTHORIZED);
        }else{
            dto.put("status", "authorized");
            dto.put("authorities", authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority));
            dto.put("user", authentication.getName());
            if (checkAuthority("MEDICO", authentication)) {
                Medico medico = medicoRepository.findByEmail(authentication.getName()).get();
                dto.put("nombre",medico.getNombre());
                dto.put("apellido",medico.getApellido());
                dto.put("matrícula",medico.getMatricula());
                dto.put("pacientes",medico.getPacientes().stream().map(Paciente::PacienteDTO));
                responseEntity = new ResponseEntity<>(dto,HttpStatus.OK);
            }else if (checkAuthority("ENFERMERO", authentication)) {
                Enfermero enfermero = enfermeroRepository.findByEmail(authentication.getName()).get();
                dto.put("nombre",enfermero.getNombre());
                dto.put("apellido",enfermero.getApellido());
                dto.put("pacientes",enfermero.getPacientes().stream().map(Paciente::PacienteDTO));
                responseEntity = new ResponseEntity<>(dto,HttpStatus.OK);
            } else{
                responseEntity = new ResponseEntity<>(makeMap("error","unauthorized"),HttpStatus.UNAUTHORIZED);
            }
        }

        return responseEntity;
    }

    @GetMapping("/pacientes/{pacienteId}/hc")
    public ResponseEntity<Map<String,Object>> getHistoriaClinicas(@PathVariable Long pacienteId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ResponseEntity<Map<String,Object>> responseEntity;
        Map<String, Object> dto = new LinkedHashMap<>();
        if(isGuest(authentication)){
            responseEntity = new ResponseEntity<>(makeMap("error", "unauthorized"), HttpStatus.UNAUTHORIZED);
        }else{
            dto.put("status", "authorized");
            dto.put("authorities", authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority));
            dto.put("user", authentication.getName());
            if (checkAuthority("MEDICO", authentication)) {
                Medico medico = medicoRepository.findByEmail(authentication.getName()).get();
                Optional<Paciente> paciente = pacienteRepository.findById(pacienteId);
                if(!paciente.isPresent()){
                    responseEntity = new ResponseEntity<>(makeMap("error","paciente no encontrado"),HttpStatus.NOT_FOUND);
                }else if(!medico.getPacientes().contains(paciente.get())){
                    responseEntity = new ResponseEntity<>(makeMap("error","unauthorized"),HttpStatus.UNAUTHORIZED);
                } else{
                    dto.put("paciente",paciente.get().PacienteDTO());
                    dto.put("historiasClinicas",paciente.get().getHistoriaClinica().stream().map(HistoriaClinica::makeHistoriaClinicaDTO));
                    responseEntity = new ResponseEntity<>(dto,HttpStatus.OK);
                }
            }else{
                responseEntity = new ResponseEntity<>(makeMap("error","unauthorized"),HttpStatus.UNAUTHORIZED);
            }
        }
        return responseEntity;
    }

    @GetMapping("/pacientes/{pacienteId}/registros")
    public ResponseEntity<Map<String,Object>> getRegistros(@PathVariable Long pacienteId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ResponseEntity<Map<String,Object>> responseEntity;
        Map<String, Object> dto = new LinkedHashMap<>();
        if(isGuest(authentication)){
            responseEntity = new ResponseEntity<>(makeMap("error", "unauthorized"), HttpStatus.UNAUTHORIZED);
        }else{
            dto.put("status", "authorized");
            dto.put("authorities", authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority));
            dto.put("user", authentication.getName());
            if (checkAuthority("ENFERMERO", authentication)) {
                Enfermero enfermero = enfermeroRepository.findByEmail(authentication.getName()).get();
                Optional<Paciente> paciente = pacienteRepository.findById(pacienteId);
                if(!paciente.isPresent()){
                    responseEntity = new ResponseEntity<>(makeMap("error","paciente no encontrado"),HttpStatus.NOT_FOUND);
                }else if(!enfermero.getPacientes().contains(paciente.get())){
                    responseEntity = new ResponseEntity<>(makeMap("error","unauthorized"),HttpStatus.UNAUTHORIZED);
                } else{
                    dto.put("paciente",paciente.get().PacienteDTO());
                    dto.put("registros",paciente.get().getRegistros()); //falta DTO
                    responseEntity = new ResponseEntity<>(dto,HttpStatus.OK);
                }
            } else if (checkAuthority("MEDICO", authentication)) {
                Medico medico = medicoRepository.findByEmail(authentication.getName()).get();
                Optional<Paciente> paciente = pacienteRepository.findById(pacienteId);
                if(!paciente.isPresent()){
                    responseEntity = new ResponseEntity<>(makeMap("error","paciente no encontrado"),HttpStatus.NOT_FOUND);
                }else if(!medico.getPacientes().contains(paciente.get())){
                    responseEntity = new ResponseEntity<>(makeMap("error","unauthorized"),HttpStatus.UNAUTHORIZED);
                } else{
                    dto.put("paciente",paciente.get().PacienteDTO());
                    dto.put("registros",paciente.get().getRegistros().stream().map(RegistroEnfermeria::makeRegistroEnfermeriaDTO));
                    responseEntity = new ResponseEntity<>(dto,HttpStatus.OK);
                }
            }
            else{
                responseEntity = new ResponseEntity<>(makeMap("error","unauthorized"),HttpStatus.UNAUTHORIZED);
            }
        }
        return responseEntity;
    }

    @PostMapping("/pacientes")
    public ResponseEntity<Map<String,Object>> addPaciente(@RequestBody FormularioPaciente formularioPaciente){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ResponseEntity<Map<String,Object>> responseEntity;
        Map<String, Object> dto = new LinkedHashMap<>();
        if(isGuest(authentication)){
            responseEntity = new ResponseEntity<>(makeMap("error", "unauthorized"), HttpStatus.UNAUTHORIZED);
        }else{
            dto.put("authorities", authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority));
            dto.put("user", authentication.getName());
            if (checkAuthority("MEDICO",authentication) || checkAuthority("ENFERMERO",authentication) ){
                if(formularioPaciente.checkForNullOrEmpty()){
                    responseEntity = new ResponseEntity<>(makeMap("error", "hay campos vacíos o nulos"), HttpStatus.FORBIDDEN);
                }else{
                    Paciente paciente = new Paciente(
                            formularioPaciente.getNombre(),
                            formularioPaciente.getApellido(),
                            formularioPaciente.getEmail(),
                            formularioPaciente.getDocumento(),
                            formularioPaciente.getFecha_nacimiento(),
                            formularioPaciente.getDireccion(),
                            formularioPaciente.getSexo(),
                            formularioPaciente.getTelefono()
                    );
                    Anamnesis anamnesis = new Anamnesis(
                            formularioPaciente.isViaje(),
                            formularioPaciente.getFecha_viaje(),
                            formularioPaciente.getDestino_viaje(),
                            formularioPaciente.isEmbarazo(),
                            formularioPaciente.getSemanas_gestacion(),
                            formularioPaciente.getEmbarazos_previos(),
                            formularioPaciente.getAntecedentes_personales(),
                            formularioPaciente.getAntecedentes_familiares(),
                            formularioPaciente.getMedicacion_regular(),
                            formularioPaciente.getTrabajo(),
                            formularioPaciente.getConvivientes(),
                            formularioPaciente.getObservaciones(),
                            formularioPaciente.isObra_social(),
                            formularioPaciente.getNombre_obra_social(),
                            formularioPaciente.getGrupo_sanguineo(),
                            paciente
                    );
                    pacienteRepository.save(paciente);
                    anamnesisRepository.save(anamnesis);
                    if(!formularioPaciente.checkNullContactoEmergencia()){
                        ContactoEmergencia contactoEmergencia = new ContactoEmergencia(
                                formularioPaciente.getNombreEmergencia(),
                                formularioPaciente.getApellidoEmergencia(),
                                formularioPaciente.getEmailEmergencia(),
                                formularioPaciente.getTelefonoEmergencia(),
                                formularioPaciente.getTelefono2Emergencia(),
                                formularioPaciente.getRelacion(),
                                formularioPaciente.getDireccionEmergencia(),
                                paciente
                        );
                        contactoEmergenciaRepository.save(contactoEmergencia);
                    }
                    dto.put("status", "success");
                    dto.put("pacienteId", paciente.getId());
                    responseEntity = new ResponseEntity<>(dto, HttpStatus.CREATED);
                }

            }else{
                responseEntity = new ResponseEntity<>(makeMap("error", "unauthorized"), HttpStatus.UNAUTHORIZED);
            }
        }
        return responseEntity;
    }

    @PostMapping("/pacientes/{pacienteId}/hc")
    public ResponseEntity<Map<String,Object>> addHC(@PathVariable long pacienteId, @RequestBody FormularioHC formularioHC){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ResponseEntity<Map<String,Object>> responseEntity;
        Map<String, Object> dto = new LinkedHashMap<>();
        if(isGuest(authentication)){
            responseEntity = new ResponseEntity<>(makeMap("error", "unauthorized"), HttpStatus.UNAUTHORIZED);
        }else{
            dto.put("authorities", authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority));
            dto.put("user", authentication.getName());
            if(checkAuthority("MEDICO",authentication)){
                Medico medico = medicoRepository.findByEmail(authentication.getName()).orElse(null);
                Paciente paciente = pacienteRepository.findById(pacienteId).orElse(null);
                if(paciente == null){
                    responseEntity = new ResponseEntity<>(makeMap("error", "el paciente no existe"), HttpStatus.NOT_FOUND);
                }else if(medico == null){
                    responseEntity = new ResponseEntity<>(makeMap("error", "unauthorized"), HttpStatus.UNAUTHORIZED);
                }else {
                    if(formularioHC.checkForNullOrEmpty()){
                        responseEntity = new ResponseEntity<>(makeMap("error", "hay campos vacíos o nulos"), HttpStatus.FORBIDDEN);
                    } else {
                        HistoriaClinica historiaClinica = new HistoriaClinica(
                                LocalDateTime.now(),
                                formularioHC.getDiagnostico(),
                                formularioHC.getTemperatura(),
                                formularioHC.getSintomas(),
                                formularioHC.getTratamiento(),
                                formularioHC.getObservaciones(),
                                medico,
                                paciente
                        );
                        historiaClinicaRepository.save(historiaClinica);
                        dto.put("status", "success");
                        responseEntity = new ResponseEntity<>(dto, HttpStatus.CREATED);
                    }
                }
            }else{
                responseEntity = new ResponseEntity<>(makeMap("error", "unauthorized"), HttpStatus.UNAUTHORIZED);
            }
        }
        return responseEntity;
    }

    @PostMapping("/pacientes/{pacienteId}/registros")
    public ResponseEntity<Map<String,Object>> addRegistro(@PathVariable long pacienteId, @RequestBody FormularioRegistro formularioRegistro){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ResponseEntity<Map<String,Object>> responseEntity;
        Map<String, Object> dto = new LinkedHashMap<>();
        if(isGuest(authentication)){
            responseEntity = new ResponseEntity<>(makeMap("error", "unauthorized"), HttpStatus.UNAUTHORIZED);
        }else{
            dto.put("authorities", authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority));
            dto.put("user", authentication.getName());
            if(checkAuthority("ENFERMERO",authentication)){
                Enfermero enfermero = enfermeroRepository.findByEmail(authentication.getName()).orElse(null);
                Paciente paciente = pacienteRepository.findById(pacienteId).orElse(null);
                if(paciente == null){
                    responseEntity = new ResponseEntity<>(makeMap("error", "el paciente no existe"), HttpStatus.NOT_FOUND);
                }else if(enfermero == null){
                    responseEntity = new ResponseEntity<>(makeMap("error", "unauthorized"), HttpStatus.UNAUTHORIZED);
                }else {
                    if(formularioRegistro.checkForNullOrEmpty()){
                        responseEntity = new ResponseEntity<>(makeMap("error", "hay campos vacíos o nulos"), HttpStatus.FORBIDDEN);
                    }else{
                        RegistroEnfermeria registroEnfermeria = new RegistroEnfermeria(
                                LocalDateTime.now(),
                                formularioRegistro.getTension_arterial(),
                                formularioRegistro.getFrecuencia_cardiaca(),
                                formularioRegistro.getFrecuencia_respiratoria(),
                                formularioRegistro.getTemperatura(),
                                formularioRegistro.getObservaciones(),
                                enfermero,
                                paciente
                        );
                        registroEnfermeriaRepository.save(registroEnfermeria);
                        dto.put("status", "success");
                        responseEntity = new ResponseEntity<>(dto, HttpStatus.CREATED);
                    }
                }
            }else{
                responseEntity = new ResponseEntity<>(makeMap("error", "unauthorized"), HttpStatus.UNAUTHORIZED);
            }
        }
        return responseEntity;
    }

    @GetMapping("/all/pacientes")
    public ResponseEntity<Map<String,Object>> getAllPacientes(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ResponseEntity<Map<String,Object>> responseEntity;
        Map<String, Object> dto = new LinkedHashMap<>();
        if(isGuest(authentication)){
            responseEntity = new ResponseEntity<>(makeMap("error", "unauthorized"), HttpStatus.UNAUTHORIZED);
        }else{
            dto.put("status", "authorized");
            dto.put("authorities", authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority));
            dto.put("user", authentication.getName());
            if (checkAuthority("MEDICO", authentication) || checkAuthority("ENFERMERO", authentication)) {
                dto.put("pacientes",pacienteRepository.findAll().stream().map(Paciente::PacienteDTO));
                responseEntity = new ResponseEntity<>(dto,HttpStatus.OK);
            }else{
                responseEntity = new ResponseEntity<>(makeMap("error","unauthorized"),HttpStatus.UNAUTHORIZED);
            }
        }

        return responseEntity;
    }

    private Map<String, Object> makeMap(String key, Object value) {
        Map<String, Object> map = new HashMap<>();
        map.put(key, value);
        return map;
    }

    private boolean isGuest(Authentication authentication) {
        return authentication == null || authentication instanceof AnonymousAuthenticationToken;
    }

    private boolean checkAuthority(String authority, Authentication authentication){
        return authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(toList()).contains(authority);
    }
}
