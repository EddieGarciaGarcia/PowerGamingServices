package com.eddie.ecommerce.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.eddie.ecommerce.dao.CreadorDAO;
import com.eddie.ecommerce.dao.Utils.ConnectionManager;
import com.eddie.ecommerce.dao.Utils.JDBCUtils;
import com.eddie.ecommerce.dao.impl.CreadorDAOImpl;
import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.exceptions.InstanceNotFoundException;
import com.eddie.ecommerce.model.Creador;
import com.eddie.ecommerce.service.CreadorService;

public class CreadorServiceImpl implements CreadorService{

	private static Logger logger=LogManager.getLogger(CreadorServiceImpl.class);
	
	CreadorDAO cdao=null;
	
	public CreadorServiceImpl() {
		cdao=new CreadorDAOImpl();
	}
	
	@Override
	public Creador findbyIdCreador(Integer id) throws InstanceNotFoundException, DataException, SQLException {
		
		if(logger.isDebugEnabled()) {
			logger.debug("id= "+id);
		}
		
		boolean commit=false;
		Connection c=null;
		try {
		c=ConnectionManager.getConnection();
		c.setAutoCommit(false);
		
		
		Creador cre = cdao.findbyIdCreador(c, id);		
				
		return cre;
		
		}catch(DataException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
	}

	@Override
	public List<Creador> findAll() throws DataException, SQLException {
		boolean commit=false;
		Connection c=null;
		try {
		c=ConnectionManager.getConnection();
		c.setAutoCommit(false);
		
		List<Creador> creador=cdao.findAll(c);
		
		return creador;
		
		}catch(SQLException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
	}

}
