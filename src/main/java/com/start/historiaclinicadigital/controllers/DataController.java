package com.start.historiaclinicadigital.controllers;

import com.start.historiaclinicadigital.models.HistoriaClinica;
import com.start.historiaclinicadigital.models.Paciente;
import com.start.historiaclinicadigital.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/data")
public class DataController {
    @Autowired
    PacienteRepository pacienteRepository;

    @GetMapping("/pacientes")
    public ResponseEntity<Map<String,Object>> getPacientesForData(@RequestParam String start, @RequestParam String end){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ResponseEntity<Map<String,Object>> responseEntity;
        Map<String, Object> dto = new LinkedHashMap<>();
        LocalDate startDate;
        LocalDate endDate;
        try{
            startDate = LocalDate.parse(start);
            endDate = LocalDate.parse(end);
        }catch(Exception e){
            return new ResponseEntity<>(makeMap("error", e.getMessage()), HttpStatus.UNAUTHORIZED);
        }

        if(isGuest(authentication)){
            responseEntity = new ResponseEntity<>(makeMap("error", "unauthorized"), HttpStatus.UNAUTHORIZED);
        }else{
            dto.put("status", "authorized");
            dto.put("authorities", authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority));
            dto.put("user", authentication.getName());
            if (checkAuthority("DATA_ANALYST", authentication) || checkAuthority("ADMIN", authentication)) {
                dto.put("pacientes",pacienteRepository.findAll().stream().map(p -> this.pacienteDTOforData(p,startDate,endDate)).filter(p -> p.get("historiasClinicas") != null));
                responseEntity = new ResponseEntity<>(dto,HttpStatus.OK);
            }else{
                responseEntity = new ResponseEntity<>(makeMap("error","unauthorized"),HttpStatus.UNAUTHORIZED);
            }
        }
        return responseEntity;
    }

    private Map<String,Object> pacienteDTOforData(Paciente paciente, LocalDate startDate, LocalDate endDate){
        Map<String,Object> dto = new LinkedHashMap<>();
        dto.put("sexo",paciente.getSexo());
        dto.put("fechaNacimiento",paciente.getFecha_nacimiento());
        if(paciente.getAnamnesis() != null)
            dto.put("anamnesis",paciente.getAnamnesis().AnamnesisDTO());
        else
            dto.put("anamnesis",null);
        if(paciente.getHistoriaClinica().size() > 0){
            List<Map<String,Object>> hcDTOs = paciente.getHistoriaClinica().stream().filter(hc ->  hc.getFecha_hora().toLocalDate().isAfter(startDate) &&  hc.getFecha_hora().toLocalDate().isBefore(endDate)).map(HistoriaClinica::makeHistoriaClinicaDTO).collect(toList());
            if(hcDTOs.size() > 0){
                dto.put("historiasClinicas", hcDTOs);
            }else{
                dto.put("historiasClinicas", null);
            }
        }else{
            dto.put("historiasClinicas",null);
        }

        return dto;
    }

     /*==================================
    ====================================
          Métodos secundarios
    ===================================
    =================================*/

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
