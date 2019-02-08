package com.eddie.ecommerce.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.eddie.ecommerce.dao.PlataformaDAO;
import com.eddie.ecommerce.dao.Utils.ConnectionManager;
import com.eddie.ecommerce.dao.Utils.JDBCUtils;
import com.eddie.ecommerce.dao.impl.PlataformaDAOImpl;
import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.exceptions.InstanceNotFoundException;
import com.eddie.ecommerce.model.Plataforma;
import com.eddie.ecommerce.service.PlataformaService;

public class PlataformaServiceImpl implements PlataformaService{
	
	private static Logger logger=LogManager.getLogger(PlataformaServiceImpl.class);

	PlataformaDAO pdao=null;
	
	public PlataformaServiceImpl() {
		pdao= new PlataformaDAOImpl();
	}
	
	@Override
	public Plataforma findbyIdPlataforma(Integer id) throws InstanceNotFoundException, DataException, SQLException {
		
		if(logger.isDebugEnabled()) {
			logger.debug("id= "+id);
		}
		
		boolean commit=false;
		Connection c=null;
		try {
		c=ConnectionManager.getConnection();
		c.setAutoCommit(false);
		
		
		Plataforma p = pdao.findbyIdPlataforma(c, id);		
				
		return p;
		
		}catch(DataException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
	}

	@Override
	public List<Plataforma> findAll() throws SQLException,DataException {
		boolean commit=false;
		Connection c=null;
		try {
		c=ConnectionManager.getConnection();
		c.setAutoCommit(false);
		
		List<Plataforma> plataforma=pdao.findAll(c);
		
		return plataforma;
		
		}catch(DataException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
	}

	@Override
	public List<Plataforma> findByJuego(Integer idJuego) throws DataException, SQLException {
		
		if(logger.isDebugEnabled()) {
			logger.debug("id = "+idJuego);
		}
		
		boolean commit=false;
		Connection c=null;
		try {
		c=ConnectionManager.getConnection();
		c.setAutoCommit(false);
		
		List<Plataforma> p=pdao.findByJuego(c, idJuego);
		
		return p;
		
		}catch(SQLException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
	}

}
