package com.inmetrics.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inmetrics.model.Contratante;
import com.inmetrics.repository.ContratanteRepository;

@RestController
@RequestMapping(value = "/contratante")
public class ContratanteResource {

	@Autowired
	private ContratanteRepository repository;
	
	@GetMapping
	public List<Contratante> findAll() {
		return repository.findAll();
	}
	
}
