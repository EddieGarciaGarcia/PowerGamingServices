package com.eddie.ecommerce.service;

import java.util.List;

import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.exceptions.InstanceNotFoundException;
import com.eddie.ecommerce.model.TipoEdicion;

public interface TipoEdicionService {
	
	public List<TipoEdicion> findbyIdsTipoEdicion(List<Integer> ids, String idioma) throws InstanceNotFoundException, DataException;
	
	//Listado de Tipo de edicion
	public List<TipoEdicion> findAll(String idioma) throws DataException;
}
