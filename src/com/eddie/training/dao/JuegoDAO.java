package com.eddie.training.dao;

import java.util.List;

import com.eddie.training.model.Juego;
import com.eddie.training.model.JuegoCriteria;

public interface JuegoDAO {
	
	public List<Juego> findByJuegoCriteria(JuegoCriteria c) throws Exception;
	
	public List<Juego> findAll() throws Exception;
	
	public Juego findById(Integer id)throws Exception;
	
	public Juego create(Juego j) throws Exception;
	
	public boolean update(Juego j) throws Exception;
	
	public void delete(Juego j) throws Exception;
}
