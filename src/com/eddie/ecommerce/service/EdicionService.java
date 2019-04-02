package com.eddie.ecommerce.service;

import java.util.List;

import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.exceptions.DuplicateInstanceException;
import com.eddie.ecommerce.exceptions.InstanceNotFoundException;
import com.eddie.ecommerce.model.Edicion;

public interface EdicionService {
	
	public Edicion finById(Integer id) throws DataException;
	
	//Busqueda de las ediciones de un juego
	public List<Edicion> findByIdJuego(Integer id) throws DataException;
	
	public List<Edicion> findByIdsJuego(List<Integer> ids) throws DataException;
	
	public Edicion create(Edicion e) throws DuplicateInstanceException, DataException;
	
	public boolean update(Edicion e) throws InstanceNotFoundException, DataException;		
	
}
