package com.start.historiaclinicadigital.controllers;

import com.start.historiaclinicadigital.dtos.*;
import com.start.historiaclinicadigital.models.*;
import com.start.historiaclinicadigital.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

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
    AdminRepository adminRepository;
    @Autowired
    AdministrativoRepository administrativoRepository;
    @Autowired
    DataAnalystRepository dataAnalystRepository;
    @Autowired
    HistoriaClinicaRepository historiaClinicaRepository;
    @Autowired
    RegistroEnfermeriaRepository registroEnfermeriaRepository;
    @Autowired
    AnamnesisRepository anamnesisRepository;
    @Autowired
    ContactoEmergenciaRepository contactoEmergenciaRepository;
    @Autowired
    HemogramaRepository hemogramaRepository;
    @Autowired
    EritrosedimentacionRepository eritrosedimentacionRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    /*==================================
    ====================================
         APIs usuarios (GET,POST)
    ===================================
    =================================*/

    @GetMapping("/user")
    public ResponseEntity<Map<String,Object>> getUserData(){
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
                Medico medico = medicoRepository.findByEmail(authentication.getName()).orElse(null);
                if(medico != null){
                    dto.put("userData",medico.MedicoDTO());
                    responseEntity = new ResponseEntity<>(dto,HttpStatus.OK);
                }else{
                    responseEntity = new ResponseEntity<>(makeMap("error", "unauthorized"), HttpStatus.UNAUTHORIZED);
                }

            }else if(checkAuthority("ENFERMERO", authentication)){
                Enfermero enfermero = enfermeroRepository.findByEmail(authentication.getName()).orElse(null);
                if(enfermero != null){
                    dto.put("userData",enfermero.EnfermeroDTO());
                    responseEntity = new ResponseEntity<>(dto,HttpStatus.OK);
                }else{
                    responseEntity = new ResponseEntity<>(makeMap("error", "unauthorized"), HttpStatus.UNAUTHORIZED);
                }
            }else if(checkAuthority("ADMIN", authentication)){
                Admin admin = adminRepository.findByEmail(authentication.getName()).orElse(null);
                if(admin != null){
                    dto.put("userData",admin.adminDTO());
                    responseEntity = new ResponseEntity<>(dto,HttpStatus.OK);
                }else{
                    responseEntity = new ResponseEntity<>(makeMap("error", "unauthorized"), HttpStatus.UNAUTHORIZED);
                }
            }else if(checkAuthority("ADMINISTRATIVO", authentication)){
                Administrativo administrativo = administrativoRepository.findByEmail(authentication.getName()).orElse(null);
                if(administrativo != null){
                    dto.put("userData",administrativo.administrativoDTO());
                    responseEntity = new ResponseEntity<>(dto,HttpStatus.OK);
                }else{
                    responseEntity = new ResponseEntity<>(makeMap("error", "unauthorized"), HttpStatus.UNAUTHORIZED);
                }
            }else if(checkAuthority("DATA_ANALYST", authentication)){
                DataAnalyst dataAnalyst = dataAnalystRepository.findByEmail(authentication.getName()).orElse(null);
                if(dataAnalyst != null){
                    dto.put("userData",dataAnalyst.dataAnalystDTO());
                    responseEntity = new ResponseEntity<>(dto,HttpStatus.OK);
                }else{
                    responseEntity = new ResponseEntity<>(makeMap("error", "unauthorized"), HttpStatus.UNAUTHORIZED);
                }
            }else{
                responseEntity = new ResponseEntity<>(makeMap("error", "unauthorized"), HttpStatus.UNAUTHORIZED);
            }
        }
        return responseEntity;
    }

    @PostMapping("/medicos")
    public ResponseEntity<Map<String,Object>> createMedico(@RequestBody RegistroMedico registroMedico){
        ResponseEntity<Map<String,Object>> responseEntity;
        Map<String, Object> dto = new LinkedHashMap<>();
        if(registroMedico.checkForNullOrEmpty()){
            responseEntity = new ResponseEntity<>(makeMap("error", "hay campos vacíos o nulos"), HttpStatus.FORBIDDEN);
        }else{
            Medico medico = new Medico(
                    registroMedico.getNombre(),
                    registroMedico.getApellido(),
                    registroMedico.getEmail(),
                    registroMedico.getDocumento(),
                    registroMedico.getMatricula(),
                    passwordEncoder.encode(registroMedico.getPassword()),
                    registroMedico.getTelefono()
            );
            medicoRepository.save(medico);
            responseEntity = new ResponseEntity<>(makeMap("success", "usuario registrado"), HttpStatus.CREATED);
        }
        return responseEntity;
    }

    @PostMapping("/enfermeros")
    public ResponseEntity<Map<String,Object>> createEnfermero(@RequestBody RegistroEnfermero registroEnfermero){
        ResponseEntity<Map<String,Object>> responseEntity;
        Map<String, Object> dto = new LinkedHashMap<>();
        if(registroEnfermero.checkForNullOrEmpty()){
            responseEntity = new ResponseEntity<>(makeMap("error", "hay campos vacíos o nulos"), HttpStatus.FORBIDDEN);
        }else{
            Enfermero enfermero = new Enfermero(
                    registroEnfermero.getNombre(),
                    registroEnfermero.getApellido(),
                    registroEnfermero.getEmail(),
                    registroEnfermero.getDocumento(),
                    passwordEncoder.encode(registroEnfermero.getPassword()),
                    registroEnfermero.getTelefono()
            );
            enfermeroRepository.save(enfermero);
            responseEntity = new ResponseEntity<>(makeMap("success", "usuario registrado"), HttpStatus.CREATED);
        }
        return responseEntity;
    }

    @PostMapping("/administrativos")
    public ResponseEntity<Map<String,Object>> createAdministrativo(@RequestBody RegistroAdministrativo registroAdministrativo){
        ResponseEntity<Map<String,Object>> responseEntity;
        Map<String, Object> dto = new LinkedHashMap<>();
        if(registroAdministrativo.checkForNullOrEmpty()){
            responseEntity = new ResponseEntity<>(makeMap("error", "hay campos vacíos o nulos"), HttpStatus.FORBIDDEN);
        }else{
            Administrativo administrativo = new Administrativo(
                    registroAdministrativo.getNombre(),
                    registroAdministrativo.getApellido(),
                    registroAdministrativo.getEmail(),
                    registroAdministrativo.getDocumento(),
                    passwordEncoder.encode(registroAdministrativo.getPassword()),
                    registroAdministrativo.getTelefono()
            );
            administrativoRepository.save(administrativo);
            responseEntity = new ResponseEntity<>(makeMap("success", "usuario registrado"), HttpStatus.CREATED);
        }
        return responseEntity;
    }


    /*==================================
    ====================================
      APIs Pacientes (GET, POST, PATCH)
    ===================================
    =================================*/

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
                Medico medico = medicoRepository.findByEmail(authentication.getName()).orElse(null);
                if(medico == null){
                    responseEntity = new ResponseEntity<>(makeMap("error","unauthorized"),HttpStatus.UNAUTHORIZED);
                }else{
                    dto.put("nombre",medico.getNombre());
                    dto.put("apellido",medico.getApellido());
                    dto.put("matrícula",medico.getMatricula());
                    dto.put("pacientes",medico.getPacientes().stream().map(Paciente::PacienteDTO));
                    responseEntity = new ResponseEntity<>(dto,HttpStatus.OK);
                }
            }else if (checkAuthority("ENFERMERO", authentication)) {
                Enfermero enfermero = enfermeroRepository.findByEmail(authentication.getName()).orElse(null);
                if(enfermero == null){
                    responseEntity = new ResponseEntity<>(makeMap("error","unauthorized"),HttpStatus.UNAUTHORIZED);
                } else{
                    dto.put("nombre",enfermero.getNombre());
                    dto.put("apellido",enfermero.getApellido());
                    dto.put("pacientes",enfermero.getPacientes().stream().map(Paciente::PacienteDTO));
                    responseEntity = new ResponseEntity<>(dto,HttpStatus.OK);
                }
            } else{
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
            if (checkAuthority("MEDICO",authentication) || checkAuthority("ENFERMERO",authentication) || checkAuthority("ADMINISTRATIVO",authentication) ){
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
                            formularioPaciente.getCondiciones_preexistentes(),
                            formularioPaciente.getMedicacion_regular(),
                            formularioPaciente.isVacuna_antigripal(),
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

    @PatchMapping("/pacientes/{pacienteId}")
    public ResponseEntity<Map<String,Object>> updatePaciente(@PathVariable long pacienteId, @RequestBody FormularioPaciente formularioPaciente){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ResponseEntity<Map<String,Object>> responseEntity;
        Map<String, Object> dto = new LinkedHashMap<>();
        if(isGuest(authentication)){
            responseEntity = new ResponseEntity<>(makeMap("error", "unauthorized"), HttpStatus.UNAUTHORIZED);
        }else{
            dto.put("authorities", authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority));
            dto.put("user", authentication.getName());
            if(checkAuthority("MEDICO",authentication)){
                Paciente paciente = pacienteRepository.findById(pacienteId).orElse(null);
                Medico medico = medicoRepository.findByEmail(authentication.getName()).orElse(null);
                if(paciente == null){
                    responseEntity = new ResponseEntity<>(makeMap("error", "el paciente no existe"), HttpStatus.NOT_FOUND);
                }else if(medico == null){
                    responseEntity = new ResponseEntity<>(makeMap("error", "unauthorized"), HttpStatus.UNAUTHORIZED);
                }else if(medico.getPacientes().stream().noneMatch(p -> p.getId() == pacienteId)){
                    responseEntity = new ResponseEntity<>(makeMap("error", "no tiene permisos para modificar los datos del paciente"), HttpStatus.FORBIDDEN);
                } else{
                    if(formularioPaciente.getNombre() != null && !formularioPaciente.getNombre().isEmpty()){
                        paciente.setNombre(formularioPaciente.getNombre());
                    }
                    if(formularioPaciente.getApellido() != null && !formularioPaciente.getApellido().isEmpty()){
                        paciente.setApellido(formularioPaciente.getApellido());
                    }
                    if(formularioPaciente.getEmail() != null && !formularioPaciente.getEmail().isEmpty()){
                        paciente.setEmail(formularioPaciente.getEmail());
                    }
                    if(formularioPaciente.getDocumento() != 0){
                        paciente.setDocumento(formularioPaciente.getDocumento());
                    }
                    if(formularioPaciente.getFecha_nacimiento() != null){
                        paciente.setFecha_nacimiento(formularioPaciente.getFecha_nacimiento());
                    }
                    if(formularioPaciente.getDireccion() != null && !formularioPaciente.getDireccion().isEmpty()){
                        paciente.setDireccion(formularioPaciente.getDireccion());
                    }
                    if(formularioPaciente.getSexo() != null){
                        paciente.setSexo(formularioPaciente.getSexo());
                    }
                    if(formularioPaciente.getTelefono() != null && !formularioPaciente.getTelefono().isEmpty()){
                        paciente.setTelefono(formularioPaciente.getTelefono());
                    }
                    pacienteRepository.save(paciente);
                    dto.put("status", "success");
                    responseEntity = new ResponseEntity<>(dto, HttpStatus.OK);
                }

            }else{
                responseEntity = new ResponseEntity<>(makeMap("error", "unauthorized"), HttpStatus.UNAUTHORIZED);
            }
        }
        return responseEntity;
    }

    /*==================================
    ====================================
      APIs HCs (GET, POST, PATCH)
    ===================================
    =================================*/

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
                Medico medico = medicoRepository.findByEmail(authentication.getName()).orElse(null);
                Paciente paciente = pacienteRepository.findById(pacienteId).orElse(null);
                if(medico == null){
                    responseEntity = new ResponseEntity<>(makeMap("error","unauthorized"),HttpStatus.UNAUTHORIZED);
                }else if(paciente == null){
                    responseEntity = new ResponseEntity<>(makeMap("error","paciente no encontrado"),HttpStatus.NOT_FOUND);
                }else if(!medico.getPacientes().contains(paciente)){
                    responseEntity = new ResponseEntity<>(makeMap("error","unauthorized"),HttpStatus.UNAUTHORIZED);
                } else{
                    dto.put("paciente",paciente.PacienteDTO());
                    dto.put("historiasClinicas",paciente.getHistoriaClinica().stream().map(HistoriaClinica::makeHistoriaClinicaDTO));
                    responseEntity = new ResponseEntity<>(dto,HttpStatus.OK);
                }
            }else{
                responseEntity = new ResponseEntity<>(makeMap("error","unauthorized"),HttpStatus.UNAUTHORIZED);
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
                                formularioHC.getMotivo_consulta(),
                                formularioHC.getDiagnostico(),
                                formularioHC.getTemperatura(),
                                formularioHC.getSintomas(),
                                formularioHC.getTratamiento(),
                                formularioHC.getObservaciones(),
                                medico,
                                paciente
                        );

                        if(!formularioHC.checkNullMedicamentos()){
                            formularioHC.getMedicamentos().forEach(m -> {
                                        if(!m.checkForNullOrEmpty()){
                                            Medicamento medicamento = new Medicamento(m.getNombre(),m.getDosis(),historiaClinica);
                                            historiaClinica.addMedicamento(medicamento);
                                            dto.put(medicamento.getNombre(),"success");
                                        }
                                    });
                        }else{
                            dto.put("medicamentos",null);
                        }

                        historiaClinicaRepository.save(historiaClinica);

                        if(!formularioHC.checkNullHemograma()){
                            Hemograma hemograma = new Hemograma(formularioHC.getGlobulos_blancos(),formularioHC.getGlobulos_rojos(),formularioHC.getPlaquetas(),historiaClinica);
                            hemogramaRepository.save(hemograma);
                            dto.put("hemograma", "success");
                        }else{
                            dto.put("hemograma", null);
                        }
                        if(!formularioHC.checkNullEritrosedimentacion()){
                            Eritrosedimentacion eritrosedimentacion = new Eritrosedimentacion(formularioHC.getEritrosedimentacion(),historiaClinica);
                            eritrosedimentacionRepository.save(eritrosedimentacion);
                            dto.put("eritrosedimentacion","success");
                        }else{
                            dto.put("eritrosedimentacion",null);
                        }
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

    @PatchMapping("/pacientes/{pacienteId}/hc/{hcId}")
    public ResponseEntity<Map<String,Object>> updateHC(@PathVariable long pacienteId, @PathVariable long hcId, @RequestBody FormularioHC formularioHC){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ResponseEntity<Map<String,Object>> responseEntity;
        Map<String, Object> dto = new LinkedHashMap<>();
        if(isGuest(authentication)){
            responseEntity = new ResponseEntity<>(makeMap("error", "unauthorized"), HttpStatus.UNAUTHORIZED);
        }else{
            dto.put("authorities", authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority));
            dto.put("user", authentication.getName());
            if(checkAuthority("MEDICO",authentication)){
                Paciente paciente = pacienteRepository.findById(pacienteId).orElse(null);
                HistoriaClinica historiaClinica = historiaClinicaRepository.findById(hcId).orElse(null);
                Medico medico = medicoRepository.findByEmail(authentication.getName()).orElse(null);
                if(paciente == null){
                    responseEntity = new ResponseEntity<>(makeMap("error", "el paciente no existe"), HttpStatus.NOT_FOUND);
                }else if(historiaClinica == null){
                    responseEntity = new ResponseEntity<>(makeMap("error", "la historia clínica solicitada no existe"), HttpStatus.NOT_FOUND);
                }else if(paciente.getHistoriaClinica().stream().noneMatch(hc -> hc.getId() == hcId)){
                    responseEntity = new ResponseEntity<>(makeMap("error", "la historia clínica solicitada no pertenece al paciente indicado"), HttpStatus.CONFLICT);
                }else if(medico == null){
                    responseEntity = new ResponseEntity<>(makeMap("error", "unauthorized"), HttpStatus.UNAUTHORIZED);
                }else if(medico.getHistoriasClinicas().stream().noneMatch(hc -> hc.getId() == hcId)){
                    responseEntity = new ResponseEntity<>(makeMap("error", "no tiene permisos para modificar la historia clínica"), HttpStatus.FORBIDDEN);
                } else{
                    if(formularioHC.getDiagnostico() != null){
                        historiaClinica.setDiagnostico(formularioHC.getDiagnostico());
                    }
                    if(formularioHC.getTemperatura() != 0){
                        historiaClinica.setTemperatura(formularioHC.getTemperatura());
                    }
                    if(formularioHC.getSintomas() != null && !formularioHC.getSintomas().isEmpty()){
                        historiaClinica.setSintomas(formularioHC.getSintomas());
                    }
                    if(formularioHC.getTratamiento() != null && !formularioHC.getTratamiento().isEmpty()){
                        historiaClinica.setTratamiento(formularioHC.getTratamiento());
                    }
                    if(formularioHC.getObservaciones() != null && !formularioHC.getObservaciones().isEmpty()){
                        historiaClinica.setObservaciones(formularioHC.getObservaciones());
                    }
                    historiaClinicaRepository.save(historiaClinica);
                    dto.put("status", "success");
                    responseEntity = new ResponseEntity<>(dto, HttpStatus.OK);
                }

            }else{
                responseEntity = new ResponseEntity<>(makeMap("error", "unauthorized"), HttpStatus.UNAUTHORIZED);
            }
        }
        return responseEntity;
    }

    /*==================================
    ====================================
      APIs REs (GET, POST, PATCH)
    ===================================
    =================================*/

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
                Enfermero enfermero = enfermeroRepository.findByEmail(authentication.getName()).orElse(null);
                Paciente paciente = pacienteRepository.findById(pacienteId).orElse(null);
                if(enfermero == null){
                    responseEntity = new ResponseEntity<>(makeMap("error","unauthorized"),HttpStatus.UNAUTHORIZED);
                }else if(paciente == null){
                    responseEntity = new ResponseEntity<>(makeMap("error","paciente no encontrado"),HttpStatus.NOT_FOUND);
                }else if(!enfermero.getPacientes().contains(paciente)){
                    responseEntity = new ResponseEntity<>(makeMap("error","unauthorized"),HttpStatus.UNAUTHORIZED);
                } else{
                    dto.put("paciente",paciente.PacienteDTO());
                    dto.put("registros",paciente.getRegistros().stream().map(RegistroEnfermeria::makeRegistroEnfermeriaDTO));
                    responseEntity = new ResponseEntity<>(dto,HttpStatus.OK);
                }
            } else if (checkAuthority("MEDICO", authentication)) {
                Medico medico = medicoRepository.findByEmail(authentication.getName()).orElse(null);
                Paciente paciente = pacienteRepository.findById(pacienteId).orElse(null);
                if(medico == null){
                    responseEntity = new ResponseEntity<>(makeMap("error","unauthorized"),HttpStatus.UNAUTHORIZED);
                }else if(paciente == null){
                    responseEntity = new ResponseEntity<>(makeMap("error","paciente no encontrado"),HttpStatus.NOT_FOUND);
                }else if(!medico.getPacientes().contains(paciente)){
                    responseEntity = new ResponseEntity<>(makeMap("error","unauthorized"),HttpStatus.UNAUTHORIZED);
                } else{
                    dto.put("paciente",paciente.PacienteDTO());
                    dto.put("registros",paciente.getRegistros().stream().map(RegistroEnfermeria::makeRegistroEnfermeriaDTO));
                    responseEntity = new ResponseEntity<>(dto,HttpStatus.OK);
                }
            }
            else{
                responseEntity = new ResponseEntity<>(makeMap("error","unauthorized"),HttpStatus.UNAUTHORIZED);
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

    @PatchMapping("/pacientes/{pacienteId}/registros/{registroId}")
    public ResponseEntity<Map<String,Object>> updateRegistroEnfermeria(@PathVariable long pacienteId, @PathVariable long registroId, @RequestBody FormularioRegistro formularioRegistro){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ResponseEntity<Map<String,Object>> responseEntity;
        Map<String, Object> dto = new LinkedHashMap<>();
        if(isGuest(authentication)){
            responseEntity = new ResponseEntity<>(makeMap("error", "unauthorized"), HttpStatus.UNAUTHORIZED);
        }else{
            dto.put("authorities", authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority));
            dto.put("user", authentication.getName());
            if(checkAuthority("ENFERMERO",authentication)){
                Paciente paciente = pacienteRepository.findById(pacienteId).orElse(null);
                RegistroEnfermeria registroEnfermeria = registroEnfermeriaRepository.findById(registroId).orElse(null);
                Enfermero enfermero = enfermeroRepository.findByEmail(authentication.getName()).orElse(null);
                if(paciente == null){
                    responseEntity = new ResponseEntity<>(makeMap("error", "El paciente no existe"), HttpStatus.NOT_FOUND);
                }else if(registroEnfermeria == null){
                    responseEntity = new ResponseEntity<>(makeMap("error", "El registro de enfermería solicitado no existe"), HttpStatus.NOT_FOUND);
                }else if(paciente.getHistoriaClinica().stream().noneMatch(hc -> hc.getId() == registroId)){
                    responseEntity = new ResponseEntity<>(makeMap("error", "El registro de enfermería solicitado no pertenece al paciente indicado"), HttpStatus.CONFLICT);
                }else if(enfermero == null){
                    responseEntity = new ResponseEntity<>(makeMap("error", "unauthorized"), HttpStatus.UNAUTHORIZED);
                }else if(enfermero.getRegistros().stream().noneMatch(registro -> registro.getId() == registroId)){
                    responseEntity = new ResponseEntity<>(makeMap("error", "No tiene permisos para modificar el registro de enfermería"), HttpStatus.FORBIDDEN);
                } else{
                    if(formularioRegistro.getTension_arterial() != 0 ){
                        registroEnfermeria.setTension_arterial(formularioRegistro.getTension_arterial());
                    }
                    if(formularioRegistro.getTemperatura() != 0){
                        registroEnfermeria.setTemperatura(formularioRegistro.getTemperatura());
                    }
                    if(formularioRegistro.getFrecuencia_cardiaca() != 0 ){
                        registroEnfermeria.setFrecuencia_cardiaca(formularioRegistro.getFrecuencia_cardiaca());
                    }
                    if(formularioRegistro.getFrecuencia_respiratoria() != 0 ){
                        registroEnfermeria.setFrecuencia_respiratoria(formularioRegistro.getFrecuencia_respiratoria());
                    }
                    if(formularioRegistro.getObservaciones() != null && !formularioRegistro.getObservaciones().isEmpty()){
                        registroEnfermeria.setObservaciones(formularioRegistro.getObservaciones());
                    }
                    registroEnfermeriaRepository.save(registroEnfermeria);
                    dto.put("status", "success");
                    responseEntity = new ResponseEntity<>(dto, HttpStatus.OK);
                }

            }else{
                responseEntity = new ResponseEntity<>(makeMap("error", "unauthorized"), HttpStatus.UNAUTHORIZED);
            }
        }
        return responseEntity;
    }

    /*==================================
    ====================================
      APIs Contactos (POST, PATCH)
    ===================================
    =================================*/

    @PostMapping("/pacientes/{pacienteId}/contactos")
    public ResponseEntity<Map<String,Object>> addContacto(@PathVariable long pacienteId, @RequestBody FormularioContacto formularioContacto){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ResponseEntity<Map<String,Object>> responseEntity;
        Map<String, Object> dto = new LinkedHashMap<>();
        if(isGuest(authentication)){
            responseEntity = new ResponseEntity<>(makeMap("error", "unauthorized"), HttpStatus.UNAUTHORIZED);
        }else{
            dto.put("authorities", authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority));
            dto.put("user", authentication.getName());
            if(checkAuthority("ENFERMERO",authentication) || checkAuthority("MEDICO",authentication) || checkAuthority("ADMINISTRATIVO",authentication)){
                Paciente paciente = pacienteRepository.findById(pacienteId).orElse(null);
                if(paciente == null){
                    responseEntity = new ResponseEntity<>(makeMap("error", "el paciente no existe"), HttpStatus.NOT_FOUND);
                }else {
                    if(formularioContacto.checkForNullOrEmpty()){
                        responseEntity = new ResponseEntity<>(makeMap("error", "hay campos vacíos o nulos"), HttpStatus.FORBIDDEN);
                    }else{
                        ContactoEmergencia contactoEmergencia = new ContactoEmergencia(
                                formularioContacto.getNombreEmergencia(),
                                formularioContacto.getApellidoEmergencia(),
                                formularioContacto.getEmailEmergencia(),
                                formularioContacto.getTelefonoEmergencia(),
                                formularioContacto.getTelefono2Emergencia(),
                                formularioContacto.getRelacion(),
                                formularioContacto.getDireccionEmergencia(),
                                paciente
                        );
                        contactoEmergenciaRepository.save(contactoEmergencia);
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


    @PatchMapping("/api/pacientes/{pacienteId}/contactos/{contactoId}")
    public ResponseEntity<Map<String,Object>> updateContacto(@PathVariable long pacienteId, @PathVariable long contactoId, @RequestBody FormularioContacto formularioContacto){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ResponseEntity<Map<String,Object>> responseEntity;
        Map<String, Object> dto = new LinkedHashMap<>();
        if(isGuest(authentication)){
            responseEntity = new ResponseEntity<>(makeMap("error", "unauthorized"), HttpStatus.UNAUTHORIZED);
        }else{
            dto.put("authorities", authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority));
            dto.put("user", authentication.getName());
            if(checkAuthority("MEDICO",authentication)){
                Paciente paciente = pacienteRepository.findById(pacienteId).orElse(null);
                ContactoEmergencia contactoEmergencia = contactoEmergenciaRepository.findById(contactoId).orElse(null);
                Medico medico = medicoRepository.findByEmail(authentication.getName()).orElse(null);
                if(paciente == null){
                    responseEntity = new ResponseEntity<>(makeMap("error", "El paciente no existe"), HttpStatus.NOT_FOUND);
                }else if(contactoEmergencia == null){
                    responseEntity = new ResponseEntity<>(makeMap("error", "El contacto de emergencia solicitado no existe"), HttpStatus.NOT_FOUND);
                }else if(paciente.getContactoEmergencia().stream().noneMatch(contacto -> contacto.getId() == contactoId)){
                    responseEntity = new ResponseEntity<>(makeMap("error", "El contacto de emergencia solicitado no pertenece al paciente indicado"), HttpStatus.CONFLICT);
                }else if(medico == null){
                    responseEntity = new ResponseEntity<>(makeMap("error", "unauthorized"), HttpStatus.UNAUTHORIZED);
                }else{
                    if(formularioContacto.getNombreEmergencia() != null && !formularioContacto.getNombreEmergencia().isEmpty()){
                        formularioContacto.setNombreEmergencia(formularioContacto.getNombreEmergencia());
                    }
                    if(formularioContacto.getApellidoEmergencia() != null && !formularioContacto.getApellidoEmergencia().isEmpty()){
                        formularioContacto.setApellidoEmergencia(formularioContacto.getApellidoEmergencia());
                    }
                    if(formularioContacto.getEmailEmergencia() != null && !formularioContacto.getEmailEmergencia().isEmpty()){
                        formularioContacto.setEmailEmergencia(formularioContacto.getEmailEmergencia());
                    }
                    if(formularioContacto.getTelefonoEmergencia() != null && !formularioContacto.getTelefonoEmergencia().isEmpty()){
                        formularioContacto.setTelefonoEmergencia(formularioContacto.getTelefonoEmergencia());
                    }
                    if(formularioContacto.getTelefono2Emergencia() != null && !formularioContacto.getTelefono2Emergencia().isEmpty()){
                        formularioContacto.setTelefono2Emergencia(formularioContacto.getTelefono2Emergencia());
                    }
                    if(formularioContacto.getRelacion() != null && !formularioContacto.getRelacion().isEmpty()){
                        formularioContacto.setRelacion(formularioContacto.getRelacion());
                    }
                    if(formularioContacto.getDireccionEmergencia() != null && !formularioContacto.getDireccionEmergencia().isEmpty()){
                        formularioContacto.setDireccionEmergencia(formularioContacto.getDireccionEmergencia());
                    }

                    contactoEmergenciaRepository.save(contactoEmergencia);
                    dto.put("status", "success");
                    responseEntity = new ResponseEntity<>(dto, HttpStatus.OK);
                }

            }else{
                responseEntity = new ResponseEntity<>(makeMap("error", "unauthorized"), HttpStatus.UNAUTHORIZED);
            }
        }
        return responseEntity;
    }

    /*==================================
    ====================================
      APIs info repos (GET)
    ===================================
    =================================*/

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
            }else if(checkAuthority("ADMINISTRATIVO",authentication)){
                dto.put("pacientes",pacienteRepository.findAll().stream().map(Paciente::pacienteDTOforAdministrativos));
                responseEntity = new ResponseEntity<>(dto,HttpStatus.OK);
            }
            else{
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
