package com.start.historiaclinicadigital.controllers;

import com.start.historiaclinicadigital.models.Administrativo;
import com.start.historiaclinicadigital.models.DataAnalyst;
import com.start.historiaclinicadigital.models.Enfermero;
import com.start.historiaclinicadigital.models.Medico;
import com.start.historiaclinicadigital.repositories.*;
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
    @Autowired
    AdministrativoRepository administrativoRepository;
    @Autowired
    DataAnalystRepository dataAnalystRepository;

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
            dto.put("enfermeros", enfermeroRepository.findAll().stream().filter(enfermero -> !enfermero.isActivo()).map(Enfermero::EnfermeroDTO));
            responseEntity = new ResponseEntity<>(dto,HttpStatus.OK);
        }
        return responseEntity;
    }

    @GetMapping("/alta/administrativos")
    public ResponseEntity<Map<String,Object>> getAdministrativosAlta(){
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
            dto.put("administrativos", administrativoRepository.findAll().stream().filter(administrativo -> !administrativo.isActivo()).map(Administrativo::administrativoDTO));
            responseEntity = new ResponseEntity<>(dto,HttpStatus.OK);
        }
        return responseEntity;
    }

    @GetMapping("/alta/datas")
    public ResponseEntity<Map<String,Object>> getDatasAlta(){
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
            dto.put("datas", dataAnalystRepository.findAll().stream().filter(data -> !data.isActivo()).map(DataAnalyst::dataAnalystDTO));
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

    @PatchMapping("/alta/administrativos/{administrativoId}")
    public ResponseEntity<Map<String,Object>> altaAdministrativo(@PathVariable long administrativoId){
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
            Administrativo administrativo = administrativoRepository.findById(administrativoId).orElse(null);
            if(administrativo == null){
                responseEntity = new ResponseEntity<>(makeMap("error", "el administrativo no existe"), HttpStatus.NOT_FOUND);
            }else if(administrativo.isActivo()){
                responseEntity = new ResponseEntity<>(makeMap("error", "el administrativo ya está activo"), HttpStatus.CONFLICT);
            }
            else{
                administrativo.setActivo(true);
                administrativoRepository.save(administrativo);
                responseEntity = new ResponseEntity<>(dto,HttpStatus.OK);
            }

        }
        return responseEntity;
    }

    @PatchMapping("/alta/datas/{dataId}")
    public ResponseEntity<Map<String,Object>> altaData(@PathVariable long dataId){
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
            DataAnalyst dataAnalyst = dataAnalystRepository.findById(dataId).orElse(null);
            if(dataAnalyst == null){
                responseEntity = new ResponseEntity<>(makeMap("error", "el data analyst no existe"), HttpStatus.NOT_FOUND);
            }else if(dataAnalyst.isActivo()){
                responseEntity = new ResponseEntity<>(makeMap("error", "el data analyst ya está activo"), HttpStatus.CONFLICT);
            }
            else{
                dataAnalyst.setActivo(true);
                dataAnalystRepository.save(dataAnalyst);
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

    @PatchMapping("/baja/administrativos/{administrativoId}")
    public ResponseEntity<Map<String,Object>> bajaAdministrativo(@PathVariable long administrativoId){
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
            Administrativo administrativo = administrativoRepository.findById(administrativoId).orElse(null);
            if(administrativo == null){
                responseEntity = new ResponseEntity<>(makeMap("error", "el administrativo no existe"), HttpStatus.NOT_FOUND);
            }else if(!administrativo.isActivo()){
                responseEntity = new ResponseEntity<>(makeMap("error", "el administrativo no está activo"), HttpStatus.CONFLICT);
            }
            else{
                administrativo.setActivo(false);
                administrativoRepository.save(administrativo);
                responseEntity = new ResponseEntity<>(dto,HttpStatus.OK);
            }

        }
        return responseEntity;
    }

    @PatchMapping("/baja/datas/{dataId}")
    public ResponseEntity<Map<String,Object>> bajaData(@PathVariable long dataId){
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
            DataAnalyst dataAnalyst = dataAnalystRepository.findById(dataId).orElse(null);
            if(dataAnalyst == null){
                responseEntity = new ResponseEntity<>(makeMap("error", "el data analyst no existe"), HttpStatus.NOT_FOUND);
            }else if(!dataAnalyst.isActivo()){
                responseEntity = new ResponseEntity<>(makeMap("error", "el data analyst no está activo"), HttpStatus.CONFLICT);
            }
            else{
                dataAnalyst.setActivo(false);
                dataAnalystRepository.save(dataAnalyst);
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

    @DeleteMapping("/eliminar/administrativos/{administrativoId}")
    public ResponseEntity<Map<String,Object>> eliminarAdministrativo(@PathVariable long administrativoId){
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
            Administrativo administrativo = administrativoRepository.findById(administrativoId).orElse(null);
            if(administrativo == null){
                responseEntity = new ResponseEntity<>(makeMap("error", "el administrativo no existe"), HttpStatus.NOT_FOUND);
            }else if(administrativo.isActivo()){
                responseEntity = new ResponseEntity<>(makeMap("error", "el administrativo está activo"), HttpStatus.FORBIDDEN);
            } else{
                administrativoRepository.delete(administrativo);
                responseEntity = new ResponseEntity<>(dto,HttpStatus.OK);
            }
        }
        return responseEntity;
    }

    @DeleteMapping("/eliminar/datas/{dataId}")
    public ResponseEntity<Map<String,Object>> eliminarData(@PathVariable long dataId){
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
            DataAnalyst dataAnalyst = dataAnalystRepository.findById(dataId).orElse(null);
            if(dataAnalyst == null){
                responseEntity = new ResponseEntity<>(makeMap("error", "el data analyst no existe"), HttpStatus.NOT_FOUND);
            }else if(dataAnalyst.isActivo()){
                responseEntity = new ResponseEntity<>(makeMap("error", "el data analyst está activo"), HttpStatus.FORBIDDEN);
            } else{
                dataAnalystRepository.delete(dataAnalyst);
                responseEntity = new ResponseEntity<>(dto,HttpStatus.OK);
            }
        }
        return responseEntity;
    }

    @GetMapping("/activos/medicos")
    public ResponseEntity<Map<String,Object>> getAllMedicos(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ResponseEntity<Map<String,Object>> responseEntity;
        Map<String, Object> dto = new LinkedHashMap<>();
        if(isGuest(authentication)){
            responseEntity = new ResponseEntity<>(makeMap("error", "unauthorized"), HttpStatus.UNAUTHORIZED);
        }else{
            dto.put("status", "authorized");
            dto.put("authorities", authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority));
            dto.put("user", authentication.getName());
            if (checkAuthority("ADMIN", authentication) ) {
                dto.put("medicos",medicoRepository.findAll().stream().filter(Medico::isActivo).map(Medico::MedicoDTO));
                responseEntity = new ResponseEntity<>(dto,HttpStatus.OK);
            }else{
                responseEntity = new ResponseEntity<>(makeMap("error","unauthorized"),HttpStatus.UNAUTHORIZED);
            }
        }

        return responseEntity;
    }

    @GetMapping("/activos/enfermeros")
    public ResponseEntity<Map<String,Object>> getAllEnfermeros(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ResponseEntity<Map<String,Object>> responseEntity;
        Map<String, Object> dto = new LinkedHashMap<>();
        if(isGuest(authentication)){
            responseEntity = new ResponseEntity<>(makeMap("error", "unauthorized"), HttpStatus.UNAUTHORIZED);
        }else{
            dto.put("status", "authorized");
            dto.put("authorities", authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority));
            dto.put("user", authentication.getName());
            if (checkAuthority("ADMIN", authentication) ) {
                dto.put("enfermeros",enfermeroRepository.findAll().stream().filter(Enfermero::isActivo).map(Enfermero::EnfermeroDTO));
                responseEntity = new ResponseEntity<>(dto,HttpStatus.OK);
            }else{
                responseEntity = new ResponseEntity<>(makeMap("error","unauthorized"),HttpStatus.UNAUTHORIZED);
            }
        }
        return responseEntity;
    }

    @GetMapping("/activos/administrativos")
    public ResponseEntity<Map<String,Object>> getAllAdministrativos(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ResponseEntity<Map<String,Object>> responseEntity;
        Map<String, Object> dto = new LinkedHashMap<>();
        if(isGuest(authentication)){
            responseEntity = new ResponseEntity<>(makeMap("error", "unauthorized"), HttpStatus.UNAUTHORIZED);
        }else{
            dto.put("status", "authorized");
            dto.put("authorities", authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority));
            dto.put("user", authentication.getName());
            if (checkAuthority("ADMIN", authentication) ) {
                dto.put("administrativos",administrativoRepository.findAll().stream().filter(Administrativo::isActivo).map(Administrativo::administrativoDTO));
                responseEntity = new ResponseEntity<>(dto,HttpStatus.OK);
            }else{
                responseEntity = new ResponseEntity<>(makeMap("error","unauthorized"),HttpStatus.UNAUTHORIZED);
            }
        }
        return responseEntity;
    }

    @GetMapping("/activos/datas")
    public ResponseEntity<Map<String,Object>> getAllDatas(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ResponseEntity<Map<String,Object>> responseEntity;
        Map<String, Object> dto = new LinkedHashMap<>();
        if(isGuest(authentication)){
            responseEntity = new ResponseEntity<>(makeMap("error", "unauthorized"), HttpStatus.UNAUTHORIZED);
        }else{
            dto.put("status", "authorized");
            dto.put("authorities", authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority));
            dto.put("user", authentication.getName());
            if (checkAuthority("ADMIN", authentication) ) {
                dto.put("administrativos",dataAnalystRepository.findAll().stream().filter(DataAnalyst::isActivo).map(DataAnalyst::dataAnalystDTO));
                responseEntity = new ResponseEntity<>(dto,HttpStatus.OK);
            }else{
                responseEntity = new ResponseEntity<>(makeMap("error","unauthorized"),HttpStatus.UNAUTHORIZED);
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
