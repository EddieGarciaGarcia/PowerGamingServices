package com.eddie.ecommerce.service;


import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.model.Categoria;

import java.util.List;

public interface CategoriaService {
	
	Categoria findById(Integer id, String idioma) throws DataException;
	
	//listado de Categorias
	List<Categoria>  findAll(String idioma) throws DataException;

	//listado de Categorias para cuando se ense�e el juego
	List<Categoria>  findByJuego(Integer idJuego, String idioma) throws DataException;

}
