package com.eddie.training.dao;

import java.sql.Connection;
import java.util.List;

import com.eddie.training.model.Juego;
import com.eddie.training.model.JuegoCriteria;

public interface JuegoDAO {
	
	public List<Juego> findByJuegoCriteria(JuegoCriteria c, String idioma, Connection connection) throws Exception;
	
	public List<Juego> findAllByDate() throws Exception;
	
	public List<Juego> findAllByValoración() throws Exception;
	
	public Juego findById(Integer id)throws Exception;
	
	public Juego create(Juego j) throws Exception;
	
	public boolean update(Juego j) throws Exception;
	
	public void delete(Juego j) throws Exception;
}
