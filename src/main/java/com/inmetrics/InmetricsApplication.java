package com.inmetrics;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.inmetrics.model.Contratante;
import com.inmetrics.repository.CelularRepository;
import com.inmetrics.repository.ContratanteRepository;

@SpringBootApplication
public class InmetricsApplication implements CommandLineRunner{

	@Autowired
	private ContratanteRepository contratanteRepository;
	
	@Autowired
	private CelularRepository celularRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(InmetricsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Contratante contratante = new Contratante(1L, "RG 1809060507", 25, "21 9999-8888", 12);
		
//		Celular celular = new Celular("0321654654654", "Samsung S10", "4500,00", 123, contratante);
//		
//		contratante.getCelulares().addAll(Arrays.asList(celular));
//		
		contratanteRepository.saveAll(Arrays.asList(contratante));
		//celularRepository.saveAll(Arrays.asList(celular));
		
		
	}
	

}
