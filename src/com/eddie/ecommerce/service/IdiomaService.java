package com.eddie.ecommerce.service;


import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.model.Idioma;

import java.util.List;

public interface IdiomaService {
	
	Idioma findById(String id, String idioma) throws DataException;;
	
	//Listado de idiomas
	List<Idioma> findAll(String idioma) throws DataException;
	
	List<Idioma> findByJuego(Integer idJuego, String idioma) throws DataException;

}
