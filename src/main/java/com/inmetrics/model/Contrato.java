package com.inmetrics.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.springframework.data.annotation.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Contrato implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDateTime dataVencimento;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDateTime dataAbertura;

	@Transient
	private boolean cotacaoExpirada;

	@OneToOne
	@JoinColumn(name = "CONTRATANTE_ID")
	private Contratante contratante;

	public Contrato() {

	}

	public Contrato(Long id, LocalDateTime dataVencimento, LocalDateTime dataAbertura, Contratante contratante) {
		this.id = id;
		this.dataVencimento = dataVencimento;
		this.dataAbertura = dataAbertura;
		this.contratante = contratante;
	}

	public boolean isCotacaoExpirada() {
		return cotacaoExpirada;
	}

	public void setCotacaoExpirada(boolean cotacaoExpirada) {
		this.cotacaoExpirada = cotacaoExpirada;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(LocalDateTime dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Contratante getContratante() {
		return contratante;
	}

	public void setContratante(Contratante contratante) {
		this.contratante = contratante;
	}

	public LocalDateTime getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(LocalDateTime dataAbertura) {
		this.dataAbertura = dataAbertura;
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
		Contrato other = (Contrato) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
