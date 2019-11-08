package com.inmetrics.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inmetrics.model.Contrato;
import com.inmetrics.repository.ContratoRepository;
import com.inmetrics.service.ContratoService;

@RestController
@RequestMapping(value = "/contratos")
public class ContratoResource {

	@Autowired
	private ContratoRepository repository;
	
	@Autowired
	private ContratoService service;
	
	@GetMapping
	public List<Contrato> findAll() {
		return repository.findAll();
	}
	
	@GetMapping(path = "/{codigo}" )
	public Optional<Contrato> simulaContrato(@PathVariable Long codigo) {
		return service.simulaContrato(codigo);
	}
	
	
	
}
