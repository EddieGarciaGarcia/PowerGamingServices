package com.eddie.ecommerce.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.eddie.ecommerce.dao.FormatoDAO;
import com.eddie.ecommerce.dao.Utils.ConnectionManager;
import com.eddie.ecommerce.dao.Utils.JDBCUtils;
import com.eddie.ecommerce.dao.impl.FormatoDAOImpl;
import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.exceptions.InstanceNotFoundException;
import com.eddie.ecommerce.model.Formato;
import com.eddie.ecommerce.service.FormatoService;

public class FormatoServiceImpl implements FormatoService{
	
	private static Logger logger=LogManager.getLogger(FormatoServiceImpl.class);
	
	FormatoDAO fdao=null;
	
	public FormatoServiceImpl() {
		fdao=new FormatoDAOImpl();
	}
	@Override
	public Formato findbyIdFormato(Integer id, String idioma) throws InstanceNotFoundException, DataException, SQLException {
		
		if(logger.isDebugEnabled()) {
			logger.debug("id= "+id+" , idioma = "+idioma);
		}
		
		boolean commit=false;
		Connection c=null;
		try {
		c=ConnectionManager.getConnection();
		c.setAutoCommit(false);
		
		
		Formato f = fdao.findbyIdFormato(c, id, idioma);	
				
		return f;
		
		}catch(DataException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
	}

	@Override
	public List<Formato> findAll(String idioma) throws DataException, SQLException  {
		
		if(logger.isDebugEnabled()) {
			logger.debug("Idioma = "+idioma);
		}
		
		boolean commit=false;
		Connection c=null;
		try {
		c=ConnectionManager.getConnection();
		c.setAutoCommit(false);
		
		List<Formato> formato=fdao.findAll(c, idioma);
		
		return formato;
		
		}catch(SQLException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
	}

}
