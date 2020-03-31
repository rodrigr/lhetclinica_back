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
	public CommandLineRunner initData(MedicoRepository medicoRepository, EnfermeroRepository enfermeroRepository, PacienteRepository pacienteRepository, HistoriaClinicaRepository historiaClinicaRepository, AnamnesisRepository anamnesisRepository, ContactoEmergenciaRepository contactoEmergenciaRepository){
		return args -> {
			//Medicos
			Medico medico1 = new Medico("Eduardo","Calleros","eduardo@hotmail.com",21888567,65024,passwordEncoder().encode("123qwe"));
			Medico medico2 = new Medico("Nahuel","Fernández","nahuel.fernandez@gmail.com",35797832,44087,passwordEncoder().encode("Swr471"));
			Medico medico3 = new Medico("Alexia","Domínguez","ale.dom@youpmail.com",30921548,77089,passwordEncoder().encode("Medica123"));
			Medico medico4 = new Medico("Maia","González","mai_med@hotmail.com",26722001,73897,passwordEncoder().encode("123456"));
			Medico medico5 = new Medico("Raúl","Echeverría","raul.eche@gmail.com",15569240,39970,passwordEncoder().encode("Covid123"));


			//Enfermeros
			Enfermero enfermero1 = new Enfermero("Jose","Encardo","jose.encardo1@gmail.com",33921355,passwordEncoder().encode("Sensei14"));
			Enfermero enfermero2 = new Enfermero("Micaela","Rodríguez","mic.rod5@youpmail.com",36886092,passwordEncoder().encode("123dfg"));
			Enfermero enfermero3 = new Enfermero("Gustavo","García","gustav_supernova@hotmail.com.com",29542833,passwordEncoder().encode("56asd1"));
			Enfermero enfermero4 = new Enfermero("Paula","Marquéz","paula.mar@gmail.com",20652310,passwordEncoder().encode("Enfer147"));
			Enfermero enfermero5 = new Enfermero("Patricia","López","patricia.lopez07@gmail.com",40512117,passwordEncoder().encode("12345678p"));

			//Pacientes
			Paciente paciente1 = new Paciente("Juan","Rios","juan.rios@gmail.com",43997867, LocalDate.parse("2002-09-24"),"Av. De Mayo 2567","M","15-2098-1145");
			Paciente paciente2 = new Paciente("Miguel","Chambilla","miguecham@gmail.com",40127007, LocalDate.parse("2001-02-28"),"Av. Cristianía 3768","M","11-4467-2709");
			Paciente paciente3 = new Paciente("Liliana","Manrique","lil.manrique@gmail.com",36997109, LocalDate.parse("1999-08-09"),"El Poncho 1567","F","15-2456-7890");
			Paciente paciente4 = new Paciente("Marcos","Olivares","marcos.olivares@gmail.com",27086993, LocalDate.parse("1991-01-05"),"Av. Juan de Garay 5346","M","11-2437-0907");
			Paciente paciente5 = new Paciente("Carla","Ferme","fermec@gmail.com",30998745, LocalDate.parse("1994-07-09"),"Av. Chiclana 3200","F","11-2367-4458");

			//Contactos de Emergencia
			ContactoEmergencia contactoEmergencia1 = new ContactoEmergencia("Maria Eugenia","Ríos","mariu@gmail.com","15-2234-5766",null,"Madre","Av. De Mayo 2567",paciente1);
			ContactoEmergencia contactoEmergencia2 = new ContactoEmergencia("Carlos","Chambilla","carlos.1@gmail.com","11-2434-4457","4486-2789","Padre","Av. Cristianía 3768",paciente2);
			ContactoEmergencia contactoEmergencia3 = new ContactoEmergencia("Alfredo","Sosa","alfredo.sosa@gmail.com",null,null,"Tío","El Poncho 1567",paciente3);
			ContactoEmergencia contactoEmergencia4 = new ContactoEmergencia("Marta","Acuña","marta02@gmail.com","15-4426-5698","4426-5698","Cónyugue","Av. Juan de Garay 5346",paciente4);
			ContactoEmergencia contactoEmergencia5 = new ContactoEmergencia("Marisa","Quintas",",arisa.q_fe@hotmail.com","11-4678-2019","5478-8891","Cónyugue","Av. Chiclana 3200",paciente5);

			//HCs
			HistoriaClinica historiaClinica1 = new HistoriaClinica(LocalDateTime.parse("2020-03-15T15:00:35"),"Cancer de pulmón",36.6,"Dificultad respiratoria, sangrado de nariz", "Reposo, respirador artificial, lavaje profundo","Se le hacen pruebas de respiracion profunda",medico5,paciente1);
			HistoriaClinica historiaClinica2 = new HistoriaClinica(LocalDateTime.parse("2020-03-21T18:30:56"),"Covid-19",40.0,"Fiebre, tos, dificultad respiratoria", "Reposo, respirador artificial, consumo de infusiones","Presenta una leve baja de fiebre",medico2,paciente2);


			//Anamnesis
			Anamnesis anamnesis1 = new Anamnesis(false,null,null,false,0,0,"","","","",2,"",true,"","",paciente1);

			//Repo Meds
			medicoRepository.save(medico1);
			medicoRepository.save(medico2);
			medicoRepository.save(medico3);
			medicoRepository.save(medico4);
			medicoRepository.save(medico5);

			//Repo Enfermeros
			enfermeroRepository.save(enfermero1);
			enfermeroRepository.save(enfermero2);
			enfermeroRepository.save(enfermero3);
			enfermeroRepository.save(enfermero4);
			enfermeroRepository.save(enfermero5);

			//Repo Pacientes
			pacienteRepository.save(paciente1);
			pacienteRepository.save(paciente2);
			pacienteRepository.save(paciente3);
			pacienteRepository.save(paciente4);
			pacienteRepository.save(paciente5);

			//Repo Contactos
			contactoEmergenciaRepository.save(contactoEmergencia1);
			contactoEmergenciaRepository.save(contactoEmergencia2);
			contactoEmergenciaRepository.save(contactoEmergencia3);
			contactoEmergenciaRepository.save(contactoEmergencia4);
			contactoEmergenciaRepository.save(contactoEmergencia5);

			//Repo HCs
			historiaClinicaRepository.save(historiaClinica1);
			historiaClinicaRepository.save(historiaClinica2);

			//Repo Anamnesis
			anamnesisRepository.save(anamnesis1);
		};
	}

}
