package com.eddie.training.dao;

import java.sql.Date;
import java.util.List;

import com.eddie.training.model.Categoria;
import com.eddie.training.model.Edicion;
import com.eddie.training.model.Idioma;
import com.eddie.training.model.Juego;
import com.eddie.training.model.Plataforma;

public interface JuegoDAO {
	
	public List<Juego> findByJuegoCriteria() throws Exception;
	
	public List<Juego> findAll(Integer id,Date fecha) throws Exception;
	
	public Juego findById(Integer id)throws Exception;
	
	public Juego create(Juego j) throws Exception;
	
	public boolean update(Juego j) throws Exception;
	
	public void delete(Juego j) throws Exception;
}
