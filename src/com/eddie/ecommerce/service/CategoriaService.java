package com.eddie.ecommerce.service;


import java.sql.SQLException;
import java.util.List;

import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.exceptions.InstanceNotFoundException;
import com.eddie.ecommerce.model.Categoria;

public interface CategoriaService {
	
	public Categoria findById(Integer id, String idioma) throws SQLException,InstanceNotFoundException, DataException;
	
	//listado de Categorias
	public List<Categoria>  findAll( String idioma) throws SQLException,DataException;
	
	public List<Categoria>  findByJuego(Integer idJuego,String idioma) throws DataException,SQLException; 

}
