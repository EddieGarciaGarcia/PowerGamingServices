package com.eddie.ecommerce.dao;

import java.sql.Connection;
import java.util.List;

import com.eddie.ecommerce.model.Juego;
import com.eddie.ecommerce.model.JuegoCriteria;
import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.exceptions.DuplicateInstanceException;
import com.eddie.ecommerce.exceptions.InstanceNotFoundException;

public interface JuegoDAO {
	
	public List<Juego> findByJuegoCriteria(JuegoCriteria c, String idioma, Connection connection) throws DataException;
	
	public List<Juego> findAllByDate() throws DataException;
	
	public List<Juego> findAllByValoración() throws DataException;
	
	public Juego findById(Integer id)throws InstanceNotFoundException, DataException;
	
	public Juego create(Juego j) throws DuplicateInstanceException, DataException;
	
	public boolean update(Juego j) throws InstanceNotFoundException, DataException;
	
	public void delete(Juego j) throws DataException;
}
