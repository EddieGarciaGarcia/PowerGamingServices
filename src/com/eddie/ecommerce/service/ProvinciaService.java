package com.eddie.ecommerce.service;


import java.sql.SQLException;
import java.util.List;

import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.model.Provincia;

public interface ProvinciaService {
	
	//Listado de provincias segun el pais escogido
	public List<Provincia> findAllByIdPais(Integer idPais)throws DataException, SQLException;

}
