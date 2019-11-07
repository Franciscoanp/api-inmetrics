package com.inmetrics.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inmetrics.model.Celular;

public interface CelularRepository extends JpaRepository<Celular, String>{

	public List<Celular> findByImei(String imei);
}
