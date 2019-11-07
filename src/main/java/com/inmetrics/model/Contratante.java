package com.inmetrics.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Contratante implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	private Long id;
	private String documento;
	private Integer idade;
	private String numeroTelefone;
	private Integer quantidadeParcelas;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "contratante")
	private List<Celular> celular;
	
	public Contratante() {
		
	}
	
	public Contratante(Long id, String documento, Integer idade, String numeroTelefone, Integer quantidadeParcelas) {
		this.id = id;
		this.documento = documento;
		this.idade = idade;
		this.numeroTelefone = numeroTelefone;
		this.quantidadeParcelas = quantidadeParcelas;
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public Integer getIdade() {
		return idade;
	}
	public void setIdade(Integer idade) {
		this.idade = idade;
	}
	public String getNumeroTelefone() {
		return numeroTelefone;
	}
	public void setNumeroTelefone(String numeroTelefone) {
		this.numeroTelefone = numeroTelefone;
	}
	public Integer getQuantidadeParcelas() {
		return quantidadeParcelas;
	}
	public void setQuantidadeParcelas(Integer quantidadeParcelas) {
		this.quantidadeParcelas = quantidadeParcelas;
	}
	
	public List<Celular> getCelular() {
		return celular;
	}

	public void setCelular(List<Celular> celular) {
		this.celular = celular;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contratante other = (Contratante) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	
}
