package com.eddie.training.dao;

import com.eddie.training.model.TipoEdicion;

public interface TipoEdicionDAO {
	
	public TipoEdicion findbyIdTipoEdicion(Integer id) throws Exception;
	
	public TipoEdicion create(TipoEdicion f) throws Exception;
	
	public boolean update(TipoEdicion f) throws Exception;
	
	public void delete(TipoEdicion f) throws Exception;
}
