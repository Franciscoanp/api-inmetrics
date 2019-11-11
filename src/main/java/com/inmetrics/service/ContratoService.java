package com.inmetrics.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inmetrics.model.Celular;
import com.inmetrics.model.Contrato;
import com.inmetrics.repository.CelularRepository;
import com.inmetrics.repository.ContratoRepository;

@Service
public class ContratoService {

	private static final Logger logger = LoggerFactory.getLogger(ContratoService.class);
	
	@Autowired
	private ContratoRepository contratoRepository;
	
	@Autowired
	private CelularRepository celularRepository;
	
	public Optional<Contrato> simulaContrato(Long codigo) {
		
		Optional<Contrato> contrato = contratoRepository.findById(codigo);
		
		verificaPrazoCotacao(contrato);
		
		verificaImeiPossuiMaisDeUmUsuario(contrato);
		
		verificaVencimentoContrato(contrato);
		
		calculaPorcentagemEquipamento(contrato);
		
		
		return contrato;
	}


	private void calculaPorcentagemEquipamento(Optional<Contrato> contrato) {
		Double valorCalculado = null;
		Double valorPorcentagemEquipamento = null;
		
		BigDecimal valorCelular = contrato.get().getContratante().getCelular().get(0).getValor();
		
		valorPorcentagemEquipamento = calculaPercentualPorValor(valorPorcentagemEquipamento, valorCelular);
		
		valorCalculado = calculaPercentualPorIdade(contrato, valorCalculado, valorPorcentagemEquipamento);
		
		System.out.println("valor calculado: " +valorCalculado);
	}



	private Double calculaPercentualPorIdade(Optional<Contrato> contrato, Double valorCalculado,
			Double valorPorcentagemEquipamento) {
		if(contrato.get().getContratante().getIdade() <= 10) {
			valorCalculado = valorPorcentagemEquipamento * 0.60;
		}else if (contrato.get().getContratante().getIdade() > 11 && contrato.get().getContratante().getIdade() <= 17) {
			valorCalculado = valorPorcentagemEquipamento * 0.65;
		}else if (contrato.get().getContratante().getIdade() > 18 && contrato.get().getContratante().getIdade() <= 21) {
			valorCalculado = valorPorcentagemEquipamento * 0.68;
		}else if (contrato.get().getContratante().getIdade() > 22 && contrato.get().getContratante().getIdade() <= 33) {
			valorCalculado = valorPorcentagemEquipamento * 0.67;
		}else if (contrato.get().getContratante().getIdade() <= 34  && contrato.get().getContratante().getIdade() <= 45) {
			valorCalculado = valorPorcentagemEquipamento * 0.55;
		}else if (contrato.get().getContratante().getIdade() > 46 && contrato.get().getContratante().getIdade() <= 90) {
			valorCalculado = valorPorcentagemEquipamento * 0.50;
		}
		return valorCalculado;
	}



	private Double calculaPercentualPorValor(Double valorPorcentagemEquipamento, BigDecimal valorCelular) {
		if (valorCelular.doubleValue() > 100 && valorCelular.doubleValue() <= 900 ) {
			valorPorcentagemEquipamento = (valorCelular.doubleValue() * 12) / 100; 
		}else if (valorCelular.doubleValue() > 901 && valorCelular.doubleValue() <= 1500) {
			valorPorcentagemEquipamento = (valorCelular.doubleValue() * 13) / 100;
		}else if (valorCelular.doubleValue() > 1501 && valorCelular.doubleValue() <= 2000) {
			valorPorcentagemEquipamento = (valorCelular.doubleValue() * 14) / 100;
		}else if (valorCelular.doubleValue() > 2001 && valorCelular.doubleValue() <= 9000) {
			valorPorcentagemEquipamento = (valorCelular.doubleValue() * 15) / 100;
		} else {
			System.out.println("Será analisado um plano de acordo com o valor desse aparelho: " + valorCelular);
		}
		return valorPorcentagemEquipamento;
	}

	
	// verifica se contrato tem mais de 30 dias para vencimento
	private void verificaVencimentoContrato(Optional<Contrato> contrato) {
		long days = verificaQuantidadeDias(contrato);
		if(days > 30) {
			System.out.println("Data vencimento: " +contrato.get().getDataVencimento()  +
								"\nNúmero do contrato: " +contrato.get().getId() +  
								"\nValor do contrato: " +contrato.get().getValorContrato());
		}
	}

	//Verifica se imei possui mais de um usuario nos ultimos 30 dias.
	private void verificaImeiPossuiMaisDeUmUsuario(Optional<Contrato> contrato) {
		long days = verificaQuantidadeDias(contrato);
		
		List<Celular> celularVinculado = celularRepository.findByImei(contrato.get().getContratante().getCelular().get(0).getImei());	
	
		if(celularVinculado.size() > 1 && days > 30) {
			System.out.println("O imei " +contrato.get().getContratante().getCelular().get(0).getImei() + " está associado a mais de uma pessoa e não está com data vigente");
		}
	}

	//Toda cotação tem prazo de 30 dias
	private void verificaPrazoCotacao(Optional<Contrato> contrato) {
		long days = verificaQuantidadeDias(contrato);

		if (days > 30) {
			contrato.get().setCotacaoExpirada(true);
			logger.info("Contrato fora do prazo de validade");
		}
	}
	
	//remover para classe util -> utilizado para comparar a data de vencimento
	private long verificaQuantidadeDias(Optional<Contrato> contrato) {
		long days = ChronoUnit.DAYS.between(contrato.get().getDataVencimento(), LocalDateTime.now());
		return days;
	}
	
}
