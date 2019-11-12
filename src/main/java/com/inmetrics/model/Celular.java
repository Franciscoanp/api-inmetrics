package com.inmetrics.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Celular implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String imei;
	private String modelo;
	private BigDecimal valorAparelho;
	private Integer numeroNotaFiscal;

	@JsonBackReference
	@ManyToOne
	public Contratante contratante;

	public Celular() {

	}

	public Celular(Long id, String imei, String modelo, BigDecimal valorAparelho, Integer numeroNotaFiscal,	Contratante contratante) {
		this.id = id;
		this.imei = imei;
		this.modelo = modelo;
		this.valorAparelho = valorAparelho;
		this.numeroNotaFiscal = numeroNotaFiscal;
		this.contratante = contratante;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Contratante getContratante() {
		return contratante;
	}

	public void setContratante(Contratante contratante) {
		this.contratante = contratante;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public BigDecimal getValorAparelho() {
		return valorAparelho;
	}

	public void setValorAparelho(BigDecimal valorAparelho) {
		this.valorAparelho = valorAparelho;
	}

	public Integer getNumeroNotaFiscal() {
		return numeroNotaFiscal;
	}

	public void setNumeroNotaFiscal(Integer numeroNotaFiscal) {
		this.numeroNotaFiscal = numeroNotaFiscal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((imei == null) ? 0 : imei.hashCode());
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
		Celular other = (Celular) obj;
		if (imei == null) {
			if (other.imei != null)
				return false;
		} else if (!imei.equals(other.imei))
			return false;
		return true;
	}

}
