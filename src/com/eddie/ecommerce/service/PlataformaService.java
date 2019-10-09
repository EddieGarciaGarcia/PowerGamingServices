package com.eddie.ecommerce.service;


import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.model.Plataforma;

import java.util.List;

public interface PlataformaService {
	
	Plataforma findbyIdPlataforma(Integer id) throws DataException;
	
	//Listado de Plataformas
	List<Plataforma>  findAll() throws DataException;
	
	List<Plataforma>  findByJuego(Integer idJuego) throws DataException;
}
