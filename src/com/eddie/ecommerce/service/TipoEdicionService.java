package com.eddie.ecommerce.service;

import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.model.TipoEdicion;

import java.util.List;

public interface TipoEdicionService {
	
	List<TipoEdicion> findbyIdsTipoEdicion(List<Integer> ids, String idioma) throws DataException;
	
	//Listado de Tipo de edicion
	List<TipoEdicion> findAll(String idioma) throws DataException;
}
