package com.eddie.ecommerce.dao;

import java.sql.Connection;
import java.util.List;

import com.eddie.ecommerce.model.Juego;
import com.eddie.ecommerce.model.JuegoCriteria;
import com.eddie.ecommerce.service.Resultados;
import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.exceptions.DuplicateInstanceException;
import com.eddie.ecommerce.exceptions.InstanceNotFoundException;

public interface JuegoDAO {
	
	
	public List<Juego> findByJuegoCriteria(JuegoCriteria c, String idioma, Connection connection) throws DataException;
	
	public Resultados<Juego> findAllByDate(Connection connection, String idioma, int startIndex, int count) throws DataException;
	
	public List<Juego> findAllByValoracion(Connection connection, String idioma) throws DataException;
	
	public Juego findById(Connection connection,Integer id, String idioma)throws InstanceNotFoundException, DataException;
	
	public Juego create(Connection connection,Juego j) throws DuplicateInstanceException, DataException;
	
	public boolean update(Connection connection,Juego j) throws InstanceNotFoundException, DataException;
	
	public void delete(Connection connection,Integer id) throws DataException;
}
