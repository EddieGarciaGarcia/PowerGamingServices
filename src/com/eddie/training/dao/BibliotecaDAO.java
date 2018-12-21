package com.eddie.training.dao;

import java.util.Date;
import java.util.List;

import com.eddie.training.model.Biblioteca;

public interface BibliotecaDAO {

	public List<Biblioteca> findByUsuario(String email) throws Exception;
	
	public Biblioteca create(Biblioteca b) throws Exception;
	
	public boolean update(Biblioteca b) throws Exception;
	
	public void delete(Biblioteca b) throws Exception;
}
