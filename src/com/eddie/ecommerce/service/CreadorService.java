package com.eddie.ecommerce.service;

import java.sql.SQLException;
import java.util.List;

import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.exceptions.InstanceNotFoundException;
import com.eddie.ecommerce.model.Creador;

public interface CreadorService {
	
	public Creador findbyIdCreador(Integer id) throws InstanceNotFoundException, DataException, SQLException;
	
	//Lista de Creadores
	public List<Creador> findAll() throws DataException, SQLException;
}
