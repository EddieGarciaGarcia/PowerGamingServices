package com.eddie.ecommerce.service;


import java.util.List;

import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.exceptions.InstanceNotFoundException;
import com.eddie.ecommerce.model.TipoEdicion;

public interface TipoEdicionService {
	
	public TipoEdicion findbyIdTipoEdicion(Integer id, String idioma) throws InstanceNotFoundException, DataException;
	
	//Listado de Tipo de edicion
	public List<TipoEdicion> findAll(String idioma) throws DataException;
}
