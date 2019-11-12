package com.inmetrics.resource;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inmetrics.model.Contrato;
import com.inmetrics.repository.ContratoRepository;
import com.inmetrics.service.ContratoService;

@RestController
@RequestMapping(value = "/contratos")
@CrossOrigin("*")
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
	
	@PostMapping
	public ResponseEntity<Contrato> salvar(@Valid @RequestBody Contrato contrato, HttpServletResponse response) {
		Contrato contratoSalvo = repository.save(contrato);
		return ResponseEntity.status(HttpStatus.CREATED).body(contratoSalvo);
	}
	
}
