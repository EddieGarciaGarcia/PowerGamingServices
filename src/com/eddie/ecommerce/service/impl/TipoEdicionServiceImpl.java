package com.eddie.ecommerce.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import com.eddie.ecommerce.dao.TipoEdicionDAO;
import com.eddie.ecommerce.dao.Utils.ConnectionManager;
import com.eddie.ecommerce.dao.Utils.JDBCUtils;
import com.eddie.ecommerce.dao.impl.TipoEdicionDAOImpl;
import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.exceptions.InstanceNotFoundException;
import com.eddie.ecommerce.model.TipoEdicion;
import com.eddie.ecommerce.service.TipoEdicionService;

public class TipoEdicionServiceImpl implements TipoEdicionService{

	TipoEdicionDAO tedao=null;
	
	public TipoEdicionServiceImpl() {
		tedao=new TipoEdicionDAOImpl();
	}
	
	@Override
	public TipoEdicion findbyIdTipoEdicion(Integer id, String idioma) throws InstanceNotFoundException, DataException, SQLException {
		boolean commit=false;
		Connection c=null;
		try {
		c=ConnectionManager.getConnection();
		c.setAutoCommit(false);
		
		
		TipoEdicion te = tedao.findbyIdTipoEdicion(c, id, idioma);	
				
		return te;
		
		}catch(DataException e) {
			e.printStackTrace();
			throw e;
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
	}

	@Override
	public List<TipoEdicion> findAll(String idioma) throws DataException, SQLException {
		boolean commit=false;
		Connection c=null;
		try {
		c=ConnectionManager.getConnection();
		c.setAutoCommit(false);
		
		List<TipoEdicion> te=tedao.findAll(c, idioma);
		
		return te;
		
		}catch(SQLException e) {
			e.printStackTrace();
			throw e;
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
	}

}
