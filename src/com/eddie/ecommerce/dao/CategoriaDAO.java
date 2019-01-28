package com.eddie.ecommerce.dao;

import java.sql.Connection;
import java.util.List;
import com.eddie.ecommerce.model.Categoria;
import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.exceptions.InstanceNotFoundException;

public interface CategoriaDAO {
	
	public Categoria findById(Connection conexion,Integer id, String idioma) throws InstanceNotFoundException, DataException;
	
	public List<Categoria>  findAll(Connection conexion, String idioma) throws DataException; 
	
	public List<Categoria>  findByJuego(Connection conexion, Integer idJuego,String idioma) throws DataException; 
}
