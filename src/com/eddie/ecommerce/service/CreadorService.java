package com.eddie.ecommerce.service;

import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.model.Creador;

import java.util.List;

public interface CreadorService {
	
	Creador findbyIdCreador(Integer id) throws DataException;
	
	//Lista de Creadores
	List<Creador> findAll() throws DataException;
}
