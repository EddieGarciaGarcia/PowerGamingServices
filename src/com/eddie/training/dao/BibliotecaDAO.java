package com.eddie.training.dao;

import com.eddie.training.model.Juego;

public interface BibliotecaDAO {

	
	public Juego create(Juego j) throws Exception;
	
	public boolean update(Juego j) throws Exception;
	
	public void delete(Juego j) throws Exception;
}
