package com.inmetrics;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.inmetrics.model.Celular;
import com.inmetrics.model.Contratante;
import com.inmetrics.model.Contrato;
import com.inmetrics.repository.CelularRepository;
import com.inmetrics.repository.ContratanteRepository;
import com.inmetrics.repository.ContratoRepository;

@SpringBootApplication
public class InmetricsApplication implements CommandLineRunner{

	@Autowired
	private ContratanteRepository contratanteRepository;
	
	@Autowired
	private CelularRepository celularRepository;
	
	@Autowired
	private ContratoRepository contratoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(InmetricsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		//Contratantes
		Contratante contratante = new Contratante(1L, "RG 1809060507", 25, "21 9999-8888", 3);
		Contratante contratante2 = new Contratante(2L, "RG 98798787", 30, "21 8888-99999", 5);
		Contratante contratante3 = new Contratante(3L, "RG 32131321", 61, "21 22222-77777", 10);
		Contratante contratante4 = new Contratante(4L, "CPF 13213215", 55, "21 9878-2133", 12);
		
		contratanteRepository.saveAll(Arrays.asList(contratante, contratante2, contratante3, contratante4));
		
		//Celulares
		Celular celular = new Celular(1L, "012345", "Samsung S10", new BigDecimal(4500.00), 123, contratante);
		Celular celular2 = new Celular(2L, "888771", "Iphone 8 plus", new BigDecimal(3800.00), 0001, contratante2);
		Celular celular3 = new Celular(3L, "564654", "Moto G 5", new BigDecimal(1350.00), 0031, contratante3);
		Celular celular4 = new Celular(4L, "98789877", "Xiaomi 9t", new BigDecimal(1999.00), 6641, contratante4);
		
		celularRepository.saveAll(Arrays.asList(celular, celular2, celular3, celular4));
		
		//Contratos
		LocalDate hoje = LocalDate.now();
		Contrato contrato = new Contrato( 1L, hoje.minusMonths(1), hoje.minusDays(30), contratante, new BigDecimal(200.00));
		Contrato contrato2 = new Contrato( 2L, hoje, hoje.minusDays(15), contratante2, new BigDecimal(800.00));
		Contrato contrato3 = new Contrato( 3L, hoje.minusMonths(5), hoje.minusDays(18), contratante3, new BigDecimal(380.00));
		Contrato contrato4 = new Contrato( 3L, hoje, hoje.minusDays(22), contratante4, new BigDecimal(600.00));

		contratoRepository.saveAll(Arrays.asList(contrato, contrato2, contrato3, contrato4));
		
	}
	

}
