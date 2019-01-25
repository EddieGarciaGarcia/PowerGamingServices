package com.eddie.ecommerce.dao;

import java.sql.Connection;
import java.util.List;
import com.eddie.ecommerce.model.Idioma;
import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.exceptions.InstanceNotFoundException;

public interface IdiomaDAO {
	
	public Idioma findById(Connection conexion,String id, String idioma) throws  InstanceNotFoundException, DataException;;
	
	public List<Idioma> findAll(Connection conexion, String idioma) throws DataException;
	
	
}
