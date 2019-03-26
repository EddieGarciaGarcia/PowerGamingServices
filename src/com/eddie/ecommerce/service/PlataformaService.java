package com.eddie.ecommerce.service;


import java.util.List;

import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.exceptions.InstanceNotFoundException;
import com.eddie.ecommerce.model.Plataforma;

public interface PlataformaService {
	
	public Plataforma findbyIdPlataforma(Integer id) throws InstanceNotFoundException, DataException;
	
	//Listado de Plataformas
	public List<Plataforma>  findAll() throws DataException; 
	
	public List<Plataforma>  findByJuego(Integer idJuego) throws DataException;
}
