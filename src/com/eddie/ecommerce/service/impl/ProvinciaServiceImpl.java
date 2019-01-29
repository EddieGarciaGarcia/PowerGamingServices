package com.eddie.ecommerce.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import com.eddie.ecommerce.dao.ProvinciaDAO;
import com.eddie.ecommerce.dao.Utils.ConnectionManager;
import com.eddie.ecommerce.dao.Utils.JDBCUtils;
import com.eddie.ecommerce.dao.impl.ProvinciaDAOImpl;
import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.model.Provincia;
import com.eddie.ecommerce.service.ProvinciaService;

public class ProvinciaServiceImpl implements ProvinciaService{
	
	ProvinciaDAO pdao=null;
	
	public ProvinciaServiceImpl() {
		pdao=new ProvinciaDAOImpl();
	}

	@Override
	public List<Provincia> findAllByIdPais(Integer idPais) throws DataException, SQLException {
		boolean commit=false;
		Connection c=null;
		try {
		c=ConnectionManager.getConnection();
		c.setAutoCommit(false);
		
		List<Provincia> p=pdao.findAllByIdPais(c, idPais);
		
		return p;
		
		}catch(SQLException e) {
			e.printStackTrace();
			throw e;
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
	}

}
