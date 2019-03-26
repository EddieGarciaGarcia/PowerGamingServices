package com.eddie.ecommerce.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.eddie.ecommerce.cache.Cache;
import com.eddie.ecommerce.cache.CacheManager;
import com.eddie.ecommerce.cache.CacheNames;
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
	public Plataforma findbyIdPlataforma(Integer id) throws InstanceNotFoundException, DataException {
		
		if(logger.isDebugEnabled()) {
			logger.debug("id= "+id);
		}
		Plataforma p=null;
		boolean commit=false;
		Connection c=null;
		try {
		c=ConnectionManager.getConnection();
		c.setAutoCommit(false);
		
		p = pdao.findbyIdPlataforma(c, id);		

		}catch(DataException e) {
			logger.error(e.getMessage(),e);
		} catch (SQLException e) {
			logger.error(e.getMessage(),e);
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
		return p;
	}

	@Override
	public List<Plataforma> findAll() throws DataException {
		int i=1;
		
		Cache<Integer, List> cachePlataforma= CacheManager.getInstance().getCache(CacheNames.PLATAFORMACACHE, Integer.class, List.class);
		
		List<Plataforma> plataforma=cachePlataforma.get(i);
		
		
		boolean commit=false;
		if(plataforma!=null) {
			if (logger.isDebugEnabled()) {
				logger.debug("Acierto cache: {}", i);
			}
		}else {
			if (logger.isDebugEnabled()) {
				logger.debug("Fallo cache: {}", i);
			}
			Connection c=null;
			try {
			c=ConnectionManager.getConnection();
			c.setAutoCommit(false);
			
			plataforma=pdao.findAll(c);
			
			cachePlataforma.put(i, plataforma);
			
			}catch(DataException e) {
				logger.error(e.getMessage(),e);
			} catch (SQLException e) {
				logger.error(e.getMessage(),e);
			}finally {
				JDBCUtils.closeConnection(c, commit);
			}
		}
		return plataforma;
	}

	@Override
	public List<Plataforma> findByJuego(Integer idJuego) throws DataException {
		
		if(logger.isDebugEnabled()) {
			logger.debug("id = "+idJuego);
		}
		List<Plataforma> p=null;
		boolean commit=false;
		Connection c=null;
		try {
		c=ConnectionManager.getConnection();
		c.setAutoCommit(false);
		
		p=pdao.findByJuego(c, idJuego);
		
		}catch(SQLException e) {
			logger.error(e.getMessage(),e);
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
		return p;
	}

}
