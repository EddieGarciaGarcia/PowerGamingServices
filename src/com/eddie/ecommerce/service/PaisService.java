package com.eddie.ecommerce.service;


import java.sql.SQLException;
import java.util.List;

import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.model.Pais;

public interface PaisService {
	
	//Listado de Paises
	public List<Pais> findAll()throws DataException, SQLException;
	
}
