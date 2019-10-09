package com.eddie.ecommerce.dao;

import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.model.Idioma;

import java.sql.Connection;
import java.util.List;

public interface IdiomaDAO {
	
	Idioma findById(Connection conexion, String id, String idioma) throws DataException;;

	List<Idioma> findAll(Connection conexion, String idioma) throws DataException;

	List<Idioma> findByJuego(Connection conexion, Integer idJuego, String idioma) throws DataException;
}
