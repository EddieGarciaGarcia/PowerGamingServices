package com.eddie.ecommerce.service;


import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.model.Pais;

import java.util.List;

public interface PaisService {
	
	//Listado de Paises
	List<Pais> findAll()throws DataException;
	
}
