package com.eddie.training.dao;

import java.sql.Connection;
import java.util.List;

import com.eddie.training.model.Plataforma;

public interface PlataformaDAO {

	public Plataforma findbyIdPlataforma(Connection conexion,Integer id) throws Exception;
	
	public List<Plataforma>  findAll(Connection conexion) throws Exception; 
}
