package com.eddie.ecommerce.dao;

import java.sql.Connection;
import java.util.List;

import com.eddie.ecommerce.model.Formato;
import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.exceptions.InstanceNotFoundException;

public interface FormatoDAO {
	
	public List<Formato> findbyIdsFormato(Connection conexion,List<Integer> ids, String idioma) throws InstanceNotFoundException, DataException;
	
	public List<Formato> findAll(Connection conexion, String idioma) throws DataException;
}
