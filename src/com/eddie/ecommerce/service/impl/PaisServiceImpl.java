package com.eddie.ecommerce.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.eddie.ecommerce.dao.PaisDAO;
import com.eddie.ecommerce.dao.Utils.ConnectionManager;
import com.eddie.ecommerce.dao.Utils.JDBCUtils;
import com.eddie.ecommerce.dao.impl.PaisDAOImpl;
import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.model.Pais;
import com.eddie.ecommerce.service.PaisService;

public class PaisServiceImpl implements PaisService{
	
	 PaisDAO pdao=null;
	 
	 public PaisServiceImpl() {
		 pdao=new PaisDAOImpl();
	 }
	@Override
	public List<Pais> findAll() throws DataException, SQLException {
		boolean commit=false;
		Connection c=null;
		try {
		c=ConnectionManager.getConnection();
		c.setAutoCommit(false);
		
		List<Pais> pais=pdao.findAll(c)	;
		
		return pais;
		
		}catch(SQLException e) {
			e.printStackTrace();
			throw e;
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
	}

}
