package com.eddie.ecommerce.service;


import java.sql.SQLException;
import java.util.List;

import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.exceptions.InstanceNotFoundException;
import com.eddie.ecommerce.model.Idioma;

public interface IdiomaService {
	
	public Idioma findById(String id, String idioma) throws  SQLException,InstanceNotFoundException, DataException;;
	
	//Listado de idiomas
	public List<Idioma> findAll( String idioma) throws SQLException,DataException;
	
	public List<Idioma> findByJuego(Integer idJuego, String idioma) throws DataException,SQLException;

}
