package com.eddie.ecommerce.dao;

import java.sql.Connection;
import java.util.List;
import com.eddie.ecommerce.model.Edicion;
import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.exceptions.DuplicateInstanceException;
import com.eddie.ecommerce.exceptions.InstanceNotFoundException;

public interface EdicionDAO {
	
	public Edicion findByIdEdicion(Connection conexion,Integer id) throws InstanceNotFoundException,DataException;
	
	public List<Edicion> findByIdJuego(Connection conexion,Integer id) throws DataException;
	
	public List<Edicion> findByIdsJuego(Connection conexion,List<Integer> ids) throws DataException;
	
	public Edicion create(Connection conexion,Edicion e) throws DuplicateInstanceException, DataException;
	
	public boolean update(Connection conexion,Edicion e) throws InstanceNotFoundException, DataException;		
	
}
