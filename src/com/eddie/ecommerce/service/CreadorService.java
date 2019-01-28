package com.eddie.ecommerce.service;

import java.util.List;

import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.exceptions.InstanceNotFoundException;
import com.eddie.ecommerce.model.Creador;

public interface CreadorService {
	
	public Creador findbyIdCreador(Integer id) throws InstanceNotFoundException, DataException;
	
	//Lista de Creadores
	public List<Creador> findAll() throws DataException;
}
