package com.eddie.ecommerce.service;


import java.util.List;

import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.exceptions.InstanceNotFoundException;
import com.eddie.ecommerce.model.Categoria;

public interface CategoriaService {
	
	public Categoria findById(Integer id, String idioma) throws InstanceNotFoundException, DataException;
	
	//listado de Categorias
	public List<Categoria>  findAll( String idioma) throws DataException;
	
	//listado de Categorias para cuando se enseñe el juego
	public List<Categoria>  findByJuego(Integer idJuego,String idioma) throws DataException; 

}
