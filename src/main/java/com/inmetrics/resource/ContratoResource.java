package com.inmetrics.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inmetrics.model.Contrato;
import com.inmetrics.repository.ContratoRepository;

@RestController
@RequestMapping(value = "/contratos")
public class ContratoResource {

	@Autowired
	private ContratoRepository repository;
	
	@GetMapping
	public List<Contrato> findAll() {
		return repository.findAll();
	}
	
	
}
