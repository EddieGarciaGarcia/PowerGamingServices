package com.eddie.ecommerce.service;

import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.model.Edicion;

import java.util.List;

public interface EdicionService {
	
	Edicion finById(Integer id) throws DataException;
	
	//Busqueda de las ediciones de un juego
	List<Edicion> findByIdJuego(Integer id) throws DataException;
	
	List<Edicion> findByIdsJuego(List<Integer> ids) throws DataException;
	
	boolean create(Edicion edicion) throws DataException;
	
	boolean update(Edicion edicion) throws DataException;
	
}
