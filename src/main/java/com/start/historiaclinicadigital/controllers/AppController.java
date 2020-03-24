package com.start.historiaclinicadigital.controllers;

import com.start.historiaclinicadigital.models.Enfermero;
import com.start.historiaclinicadigital.models.Medico;
import com.start.historiaclinicadigital.models.Paciente;
import com.start.historiaclinicadigital.repositories.EnfermeroRepository;
import com.start.historiaclinicadigital.repositories.MedicoRepository;
import com.start.historiaclinicadigital.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/pacientes")
    public ResponseEntity<Map<String,Object>> getPacientes(Authentication authentication){
        ResponseEntity<Map<String,Object>> responseEntity;
        Map<String, Object> dto = new LinkedHashMap<>();
        if(isGuest(authentication)){
            responseEntity = new ResponseEntity<>(makeMap("error", "unauthorized"), HttpStatus.UNAUTHORIZED);
        }else{
            dto.put("status", "authorized");
            dto.put("authorities", authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority));
            dto.put("user", authentication.getName());
            if (authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(toList()).contains("MEDICO")) {
                Medico medico = medicoRepository.findByEmail(authentication.getName()).get();
                dto.put("pacientes",medico.getPacientes().stream().map(Paciente::PacienteDTO));
                responseEntity = new ResponseEntity<>(dto,HttpStatus.OK);
            }else if (authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(toList()).contains("ENFERMERO")) {
                Enfermero enfermero = enfermeroRepository.findByEmail(authentication.getName()).get();
                dto.put("pacientes",enfermero.getPacientes().stream().map(Paciente::PacienteDTO));
                responseEntity = new ResponseEntity<>(dto,HttpStatus.OK);
            } else{
                responseEntity = new ResponseEntity<>(makeMap("error","unauthorized"),HttpStatus.UNAUTHORIZED);
            }
        }

        return responseEntity;
    }

    @GetMapping("/pacientes/{pacienteId}/hc")
    public ResponseEntity<Map<String,Object>> getHistoriaClinicas(@PathVariable Long pacienteId, Authentication authentication){
        ResponseEntity<Map<String,Object>> responseEntity;
        Map<String, Object> dto = new LinkedHashMap<>();
        if(isGuest(authentication)){
            responseEntity = new ResponseEntity<>(makeMap("error", "unauthorized"), HttpStatus.UNAUTHORIZED);
        }else{
            dto.put("status", "authorized");
            dto.put("authorities", authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority));
            dto.put("user", authentication.getName());
            if (authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(toList()).contains("MEDICO")) {
                Medico medico = medicoRepository.findByEmail(authentication.getName()).get();
                Optional<Paciente> paciente = pacienteRepository.findById(pacienteId);
                if(!paciente.isPresent()){
                    responseEntity = new ResponseEntity<>(makeMap("error","paciente no encontrado"),HttpStatus.NOT_FOUND);
                }else if(!medico.getPacientes().contains(paciente.get())){
                    responseEntity = new ResponseEntity<>(makeMap("error","unauthorized"),HttpStatus.UNAUTHORIZED);
                } else{
                    dto.put("paciente",paciente.get().PacienteDTO());
                    dto.put("historiasClinicas",paciente.get().getHistoriaClinica()); //falta DTO
                    responseEntity = new ResponseEntity<>(dto,HttpStatus.OK);
                }
            }else{
                responseEntity = new ResponseEntity<>(makeMap("error","unauthorized"),HttpStatus.UNAUTHORIZED);
            }
        }
        return responseEntity;
    }

    @GetMapping("/pacientes/{pacienteId}/registros")
    public ResponseEntity<Map<String,Object>> getRegistros(@PathVariable Long pacienteId, Authentication authentication){
        ResponseEntity<Map<String,Object>> responseEntity;
        Map<String, Object> dto = new LinkedHashMap<>();
        if(isGuest(authentication)){
            responseEntity = new ResponseEntity<>(makeMap("error", "unauthorized"), HttpStatus.UNAUTHORIZED);
        }else{
            dto.put("status", "authorized");
            dto.put("authorities", authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority));
            dto.put("user", authentication.getName());
            if (authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(toList()).contains("ENFERMERO")) {
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
            } else if (authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(toList()).contains("MEDICO")) {
                Medico medico = medicoRepository.findByEmail(authentication.getName()).get();
                Optional<Paciente> paciente = pacienteRepository.findById(pacienteId);
                if(!paciente.isPresent()){
                    responseEntity = new ResponseEntity<>(makeMap("error","paciente no encontrado"),HttpStatus.NOT_FOUND);
                }else if(!medico.getPacientes().contains(paciente.get())){
                    responseEntity = new ResponseEntity<>(makeMap("error","unauthorized"),HttpStatus.UNAUTHORIZED);
                } else{
                    dto.put("paciente",paciente.get().PacienteDTO());
                    dto.put("registros",paciente.get().getRegistros()); //falta DTO
                    responseEntity = new ResponseEntity<>(dto,HttpStatus.OK);
                }
            }
            else{
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
}
