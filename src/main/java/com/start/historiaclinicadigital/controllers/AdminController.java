package com.start.historiaclinicadigital.controllers;

import com.start.historiaclinicadigital.models.Enfermero;
import com.start.historiaclinicadigital.models.Medico;
import com.start.historiaclinicadigital.repositories.AdminRepository;
import com.start.historiaclinicadigital.repositories.EnfermeroRepository;
import com.start.historiaclinicadigital.repositories.MedicoRepository;
import com.start.historiaclinicadigital.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    MedicoRepository medicoRepository;
    @Autowired
    EnfermeroRepository enfermeroRepository;

    @GetMapping("/alta/medicos")
    public ResponseEntity<Map<String,Object>> getMedicosAlta(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ResponseEntity<Map<String,Object>> responseEntity;
        Map<String, Object> dto = new LinkedHashMap<>();
        if(isGuest(authentication)){
            responseEntity = new ResponseEntity<>(makeMap("error", "unauthorized"), HttpStatus.UNAUTHORIZED);
        } else if(!this.checkAuthority("ADMIN",authentication)){
            responseEntity = new ResponseEntity<>(makeMap("error", "unauthorized"), HttpStatus.UNAUTHORIZED);
        }else{
            dto.put("status", "authorized");
            dto.put("authorities", authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority));
            dto.put("user", authentication.getName());
            dto.put("medicos", medicoRepository.findAll().stream().filter(medico -> !medico.isActivo()).map(Medico::MedicoDTO));
            responseEntity = new ResponseEntity<>(dto,HttpStatus.OK);
        }
        return responseEntity;
    }

    @GetMapping("/alta/enfermeros")
    public ResponseEntity<Map<String,Object>> getEnfermerosAlta(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ResponseEntity<Map<String,Object>> responseEntity;
        Map<String, Object> dto = new LinkedHashMap<>();
        if(isGuest(authentication)){
            responseEntity = new ResponseEntity<>(makeMap("error", "unauthorized"), HttpStatus.UNAUTHORIZED);
        } else if(!this.checkAuthority("ADMIN",authentication)){
            responseEntity = new ResponseEntity<>(makeMap("error", "unauthorized"), HttpStatus.UNAUTHORIZED);
        }else{
            dto.put("status", "authorized");
            dto.put("authorities", authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority));
            dto.put("user", authentication.getName());
            dto.put("enfermeros", enfermeroRepository.findAll().stream().filter(medico -> !medico.isActivo()).map(Enfermero::EnfermeroDTO));
            responseEntity = new ResponseEntity<>(dto,HttpStatus.OK);
        }
        return responseEntity;
    }

    @PatchMapping("/alta/medicos/{medicoId}")
    public ResponseEntity<Map<String,Object>> altaMedico(@PathVariable long medicoId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ResponseEntity<Map<String,Object>> responseEntity;
        Map<String, Object> dto = new LinkedHashMap<>();
        if(isGuest(authentication)){
            responseEntity = new ResponseEntity<>(makeMap("error", "unauthorized"), HttpStatus.UNAUTHORIZED);
        } else if(!this.checkAuthority("ADMIN",authentication)){
            responseEntity = new ResponseEntity<>(makeMap("error", "unauthorized"), HttpStatus.UNAUTHORIZED);
        }else{
            dto.put("status", "success");
            dto.put("authorities", authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority));
            dto.put("user", authentication.getName());
            Medico medico = medicoRepository.findById(medicoId).orElse(null);
            if(medico == null){
                responseEntity = new ResponseEntity<>(makeMap("error", "el medico no existe"), HttpStatus.NOT_FOUND);
            }else if(medico.isActivo()){
                responseEntity = new ResponseEntity<>(makeMap("error", "el medico no ya está activo"), HttpStatus.CONFLICT);
            }
            else{
                medico.setActivo(true);
                medicoRepository.save(medico);
                responseEntity = new ResponseEntity<>(dto,HttpStatus.OK);
            }

        }
        return responseEntity;
    }

    @PatchMapping("/alta/enfermeros/{enfermeroId}")
    public ResponseEntity<Map<String,Object>> altaEnfermero(@PathVariable long enfermeroId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ResponseEntity<Map<String,Object>> responseEntity;
        Map<String, Object> dto = new LinkedHashMap<>();
        if(isGuest(authentication)){
            responseEntity = new ResponseEntity<>(makeMap("error", "unauthorized"), HttpStatus.UNAUTHORIZED);
        } else if(!this.checkAuthority("ADMIN",authentication)){
            responseEntity = new ResponseEntity<>(makeMap("error", "unauthorized"), HttpStatus.UNAUTHORIZED);
        }else{
            dto.put("status", "success");
            dto.put("authorities", authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority));
            dto.put("user", authentication.getName());
            Enfermero enfermero = enfermeroRepository.findById(enfermeroId).orElse(null);
            if(enfermero == null){
                responseEntity = new ResponseEntity<>(makeMap("error", "el enfermero no existe"), HttpStatus.NOT_FOUND);
            }else if(enfermero.isActivo()){
                responseEntity = new ResponseEntity<>(makeMap("error", "el enfermero ya está activo"), HttpStatus.CONFLICT);
            }
            else{
                enfermero.setActivo(true);
                enfermeroRepository.save(enfermero);
                responseEntity = new ResponseEntity<>(dto,HttpStatus.OK);
            }

        }
        return responseEntity;
    }

    @PatchMapping("/baja/medicos/{medicoId}")
    public ResponseEntity<Map<String,Object>> bajaMedico(@PathVariable long medicoId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ResponseEntity<Map<String,Object>> responseEntity;
        Map<String, Object> dto = new LinkedHashMap<>();
        if(isGuest(authentication)){
            responseEntity = new ResponseEntity<>(makeMap("error", "unauthorized"), HttpStatus.UNAUTHORIZED);
        } else if(!this.checkAuthority("ADMIN",authentication)){
            responseEntity = new ResponseEntity<>(makeMap("error", "unauthorized"), HttpStatus.UNAUTHORIZED);
        }else{
            dto.put("status", "success");
            dto.put("authorities", authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority));
            dto.put("user", authentication.getName());
            Medico medico = medicoRepository.findById(medicoId).orElse(null);
            if(medico == null){
                responseEntity = new ResponseEntity<>(makeMap("error", "el medico no existe"), HttpStatus.NOT_FOUND);
            }else if(!medico.isActivo()){
                responseEntity = new ResponseEntity<>(makeMap("error", "el medico no está activo"), HttpStatus.CONFLICT);
            } else{
                medico.setActivo(false);
                medicoRepository.save(medico);
                responseEntity = new ResponseEntity<>(dto,HttpStatus.OK);
            }

        }
        return responseEntity;
    }

    @PatchMapping("/baja/enfermeros/{enfermeroId}")
    public ResponseEntity<Map<String,Object>> bajaEnfermero(@PathVariable long enfermeroId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ResponseEntity<Map<String,Object>> responseEntity;
        Map<String, Object> dto = new LinkedHashMap<>();
        if(isGuest(authentication)){
            responseEntity = new ResponseEntity<>(makeMap("error", "unauthorized"), HttpStatus.UNAUTHORIZED);
        } else if(!this.checkAuthority("ADMIN",authentication)){
            responseEntity = new ResponseEntity<>(makeMap("error", "unauthorized"), HttpStatus.UNAUTHORIZED);
        }else{
            dto.put("status", "success");
            dto.put("authorities", authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority));
            dto.put("user", authentication.getName());
            Enfermero enfermero = enfermeroRepository.findById(enfermeroId).orElse(null);
            if(enfermero == null){
                responseEntity = new ResponseEntity<>(makeMap("error", "el enfermero no existe"), HttpStatus.NOT_FOUND);
            }else if(!enfermero.isActivo()){
                responseEntity = new ResponseEntity<>(makeMap("error", "el enfermero no está activo"), HttpStatus.CONFLICT);
            }
            else{
                enfermero.setActivo(false);
                enfermeroRepository.save(enfermero);
                responseEntity = new ResponseEntity<>(dto,HttpStatus.OK);
            }

        }
        return responseEntity;
    }

    @DeleteMapping("/eliminar/medicos/{medicoId}")
    public ResponseEntity<Map<String,Object>> eliminarMedico(@PathVariable long medicoId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ResponseEntity<Map<String,Object>> responseEntity;
        Map<String, Object> dto = new LinkedHashMap<>();
        if(isGuest(authentication)){
            responseEntity = new ResponseEntity<>(makeMap("error", "unauthorized"), HttpStatus.UNAUTHORIZED);
        } else if(!this.checkAuthority("ADMIN",authentication)){
            responseEntity = new ResponseEntity<>(makeMap("error", "unauthorized"), HttpStatus.UNAUTHORIZED);
        }else {
            dto.put("status", "success");
            dto.put("authorities", authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority));
            dto.put("user", authentication.getName());
            Medico medico = medicoRepository.findById(medicoId).orElse(null);
            if(medico == null){
                responseEntity = new ResponseEntity<>(makeMap("error", "el medico no existe"), HttpStatus.NOT_FOUND);
            }else if(medico.isActivo()){
                responseEntity = new ResponseEntity<>(makeMap("error", "el médico está activo"), HttpStatus.FORBIDDEN);
            } else{
                medicoRepository.delete(medico);
                responseEntity = new ResponseEntity<>(dto,HttpStatus.OK);
            }
        }
        return responseEntity;
    }

    @DeleteMapping("/eliminar/enfermeros/{enfermeroId}")
    public ResponseEntity<Map<String,Object>> eliminarEnfermero(@PathVariable long enfermeroId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ResponseEntity<Map<String,Object>> responseEntity;
        Map<String, Object> dto = new LinkedHashMap<>();
        if(isGuest(authentication)){
            responseEntity = new ResponseEntity<>(makeMap("error", "unauthorized"), HttpStatus.UNAUTHORIZED);
        } else if(!this.checkAuthority("ADMIN",authentication)){
            responseEntity = new ResponseEntity<>(makeMap("error", "unauthorized"), HttpStatus.UNAUTHORIZED);
        }else {
            dto.put("status", "success");
            dto.put("authorities", authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority));
            dto.put("user", authentication.getName());
            Enfermero enfermero = enfermeroRepository.findById(enfermeroId).orElse(null);
            if(enfermero == null){
                responseEntity = new ResponseEntity<>(makeMap("error", "el enfermero no existe"), HttpStatus.NOT_FOUND);
            }else if(enfermero.isActivo()){
                responseEntity = new ResponseEntity<>(makeMap("error", "el enfermero está activo"), HttpStatus.FORBIDDEN);
            } else{
                enfermeroRepository.delete(enfermero);
                responseEntity = new ResponseEntity<>(dto,HttpStatus.OK);
            }
        }
        return responseEntity;
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
