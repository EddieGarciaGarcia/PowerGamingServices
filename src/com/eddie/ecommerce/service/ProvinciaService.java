package com.eddie.ecommerce.service;


import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.model.Provincia;

import java.util.List;

public interface ProvinciaService {
	
	//Listado de provincias segun el pais escogido
	List<Provincia> findAll()throws DataException;

}
