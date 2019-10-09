package com.eddie.ecommerce.dao;

import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.model.Formato;

import java.sql.Connection;
import java.util.List;

public interface FormatoDAO {
	
	List<Formato> findbyIdsFormato(Connection conexion, List<Integer> ids, String idioma) throws DataException;
	
	List<Formato> findAll(Connection conexion, String idioma) throws DataException;
}
