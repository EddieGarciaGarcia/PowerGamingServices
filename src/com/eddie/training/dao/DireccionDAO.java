package com.eddie.training.dao;

import com.eddie.training.model.Direccion;
import com.eddie.training.model.Usuario;

public interface DireccionDAO {
	
	public Direccion findById(Integer id) throws Exception;
	
	public Direccion create(Direccion d) throws Exception;
	
	public boolean update(Direccion d) throws Exception;
	
	public void delete(Direccion d) throws Exception;
}
