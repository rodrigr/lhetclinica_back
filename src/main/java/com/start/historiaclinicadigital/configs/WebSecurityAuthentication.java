package com.start.historiaclinicadigital.configs;

import com.start.historiaclinicadigital.models.*;
import com.start.historiaclinicadigital.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

@Configuration
public class WebSecurityAuthentication extends GlobalAuthenticationConfigurerAdapter {

    @Autowired
    MedicoRepository medicoRepository;
    @Autowired
    EnfermeroRepository enfermeroRepository;
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    DataAnalystRepository dataAnalystRepository;
    @Autowired
    AdministrativoRepository administrativoRepository;

    @Override
    public void init (AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(email -> {
            Optional<Medico> medico = medicoRepository.findByEmail(email);
            Optional<Enfermero> enfermero = enfermeroRepository.findByEmail(email);
            Optional<Admin> admin = adminRepository.findByEmail(email);
            Optional<Administrativo> administrativo = administrativoRepository.findByEmail(email);
            Optional<DataAnalyst> dataAnalyst = dataAnalystRepository.findByEmail(email);
            if(medico.isPresent() && medico.get().isActivo()){
                return new User(medico.get().getEmail(), medico.get().getPassword(),
                        AuthorityUtils.createAuthorityList("MEDICO"));
            } else if(enfermero.isPresent() && enfermero.get().isActivo()){
                return new User(enfermero.get().getEmail(), enfermero.get().getPassword(),
                        AuthorityUtils.createAuthorityList("ENFERMERO"));
            }
            else if(admin.isPresent()){
                return new User(admin.get().getEmail(), admin.get().getPassword(),
                        AuthorityUtils.createAuthorityList("ADMIN"));
            }
            else if(administrativo.isPresent() && administrativo.get().isActivo()){
                return new User(administrativo.get().getEmail(), administrativo.get().getPassword(),
                        AuthorityUtils.createAuthorityList("ADMINISTRATIVO"));
            }
            else if(dataAnalyst.isPresent() && administrativo.get().isActivo()){
                return new User(dataAnalyst.get().getEmail(), dataAnalyst.get().getPassword(),
                        AuthorityUtils.createAuthorityList("DATA_ANALYST"));
            } else{
                throw new UsernameNotFoundException("Unknown user: " + email);
            }
        });
    }
}
