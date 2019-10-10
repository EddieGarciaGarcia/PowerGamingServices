package com.eddie.ecommerce.dao;

import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.model.Juego;
import com.eddie.ecommerce.model.JuegoCriteria;
import com.eddie.ecommerce.model.Resultados;

import java.sql.Connection;
import java.util.List;

public interface JuegoDAO {

	Resultados<Juego> findByJuegoCriteria(Connection connection, JuegoCriteria juegoCriteria, String idioma, int startIndex, int count) throws DataException;

	Resultados<Juego> findAllByDate(Connection connection, String idioma, int startIndex, int count) throws DataException;
	
	List<Juego> findByJuegoCriteria(Connection connection, JuegoCriteria juegoCriteria, String idioma) throws DataException;
	
	List<Juego> findAllByDate(Connection connection, String idioma) throws DataException;
	
	List<Juego> findByIDs(Connection connection, List<Integer> ids, String idioma)throws DataException;
	
	List<Juego> findAllByValoracion(Connection connection, String idioma) throws DataException;
	
	Juego findById(Connection connection, Integer id, String idioma)throws DataException;

	Juego create(Connection connection, Juego juego) throws DataException;

	boolean update(Connection connection, Juego juego) throws DataException;

	boolean delete(Connection connection, Integer id) throws DataException;
	
	//Media
	Integer puntuacion(Connection connection, Integer idJuego)throws DataException;
}
