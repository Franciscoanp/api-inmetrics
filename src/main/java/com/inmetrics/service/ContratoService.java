package com.inmetrics.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		
		LocalDateTime hoje = LocalDateTime.now();
		
		List<Celular> celularesVinculados = celularRepository.find
		
		return contrato;
	}

	
	//Toda cotação tem prazo de 30 dias
	private void verificaPrazoCotacao(Optional<Contrato> contrato) {
		long days = ChronoUnit.DAYS.between(contrato.get().getDataVencimento(), LocalDateTime.now());

		if (days > 30) {
			contrato.get().setCotacaoExpirada(true);
			logger.info("Contrato fora do prazo de validade");
		}
	}
}
