package com.start.historiaclinicadigital;

import com.start.historiaclinicadigital.models.Enfermero;
import com.start.historiaclinicadigital.models.Medico;
import com.start.historiaclinicadigital.models.Paciente;
import com.start.historiaclinicadigital.repositories.EnfermeroRepository;
import com.start.historiaclinicadigital.repositories.MedicoRepository;
import com.start.historiaclinicadigital.repositories.PacienteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@SpringBootApplication
public class HistoriaClinicaDigitalApplication {

	public static void main(String[] args) {
		SpringApplication.run(HistoriaClinicaDigitalApplication.class, args);
	}

	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	public CommandLineRunner initData(MedicoRepository medicoRepository, EnfermeroRepository enfermeroRepository, PacienteRepository pacienteRepository){
		return args -> {
			Medico medico1 = new Medico("Eduardo","Calleros","eduardo@hotmail.com",21888567,65024,passwordEncoder().encode("123qwe"));
			medicoRepository.save(medico1);
			Enfermero enfermero1 = new Enfermero("Jose","Encardo","jose.encardo1@gmail.com",33921355,passwordEncoder().encode("Sensei14"));
			enfermeroRepository.save(enfermero1);
			Paciente paciente1 = new Paciente("Juan","Rios","juan.rios@gmail.com",43997867, LocalDate.parse("2002-09-24"),"Av. De Mayo 2567","M","15-2098-1145");
			pacienteRepository.save(paciente1);
		};
	}

}
