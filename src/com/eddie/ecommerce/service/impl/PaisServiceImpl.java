package com.eddie.ecommerce.service.impl;

import com.eddie.ecommerce.dao.PaisDAO;
import com.eddie.ecommerce.dao.impl.PaisDAOImpl;
import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.model.Pais;
import com.eddie.ecommerce.service.PaisService;
import com.eddie.ecommerce.utils.ConnectionManager;
import com.eddie.ecommerce.utils.JDBCUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PaisServiceImpl implements PaisService{
	
	private static Logger logger=LogManager.getLogger(PaisServiceImpl.class);
	
	 PaisDAO pdao=null;
	 
	 public PaisServiceImpl() {
		 pdao=new PaisDAOImpl();
	 }
	@Override
	public List<Pais> findAll() throws DataException {
		boolean commit=false;
		Connection c=null;
		List<Pais> pais=null;
		try {
		c= ConnectionManager.getConnection();
		c.setAutoCommit(false);
		
		pais=pdao.findAll(c)	;
		
		}catch(SQLException e) {
			logger.error(e.getMessage(),e);
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
		return pais;
	}

}
