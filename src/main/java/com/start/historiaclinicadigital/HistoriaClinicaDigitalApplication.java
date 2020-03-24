package com.start.historiaclinicadigital;

import com.start.historiaclinicadigital.models.*;
import com.start.historiaclinicadigital.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
	public CommandLineRunner initData(MedicoRepository medicoRepository, EnfermeroRepository enfermeroRepository, PacienteRepository pacienteRepository, HistoriaClinicaRepository historiaClinicaRepository, AnamnesisRepository anamnesisRepository){
		return args -> {
			Medico medico1 = new Medico("Eduardo","Calleros","eduardo@hotmail.com",21888567,65024,passwordEncoder().encode("123qwe"));

			Enfermero enfermero1 = new Enfermero("Jose","Encardo","jose.encardo1@gmail.com",33921355,passwordEncoder().encode("Sensei14"));

			Paciente paciente1 = new Paciente("Juan","Rios","juan.rios@gmail.com",43997867, LocalDate.parse("2002-09-24"),"Av. De Mayo 2567","M","15-2098-1145");

			HistoriaClinica historiaClinica1 = new HistoriaClinica(LocalDateTime.now(),"Covid-19",40.0F,"Fiebre, Tos", "Reposo, Respirador","Presenta una leve baja de fiebre",medico1,paciente1);

			Anamnesis anamnesis1 = new Anamnesis(false,null,null,false,0,0,"","","","",2,"",paciente1);


			medicoRepository.save(medico1);
			enfermeroRepository.save(enfermero1);
			pacienteRepository.save(paciente1);
			historiaClinicaRepository.save(historiaClinica1);
			anamnesisRepository.save(anamnesis1);



		};
	}

}
