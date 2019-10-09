package com.eddie.ecommerce.service.impl;


import com.eddie.ecommerce.dao.PlataformaDAO;
import com.eddie.ecommerce.dao.impl.PlataformaDAOImpl;
import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.exceptions.InstanceNotFoundException;
import com.eddie.ecommerce.model.Plataforma;
import com.eddie.ecommerce.service.PlataformaService;
import com.eddie.ecommerce.utils.CacheManager;
import com.eddie.ecommerce.utils.ConnectionManager;
import com.eddie.ecommerce.utils.Constantes;
import com.eddie.ecommerce.utils.JDBCUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ehcache.Cache;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

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
		c= ConnectionManager.getConnection();
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
		
		Cache<String, List> cachePlataforma= CacheManager.getCachePG(Constantes.NOMBRE_CACHE_ESTATICOS);
		
		List<Plataforma> plataforma=cachePlataforma.get(Constantes.CACHE_PLATAFORMA);
		
		
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
			
			cachePlataforma.put(Constantes.CACHE_PLATAFORMA, plataforma);
			
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
