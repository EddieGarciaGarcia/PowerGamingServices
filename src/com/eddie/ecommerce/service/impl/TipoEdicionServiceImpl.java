package com.eddie.ecommerce.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.eddie.ecommerce.cache.Cache;
import com.eddie.ecommerce.cache.CacheManager;
import com.eddie.ecommerce.cache.CacheNames;
import com.eddie.ecommerce.dao.TipoEdicionDAO;
import com.eddie.ecommerce.dao.Utils.ConnectionManager;
import com.eddie.ecommerce.dao.Utils.JDBCUtils;
import com.eddie.ecommerce.dao.impl.TipoEdicionDAOImpl;
import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.exceptions.InstanceNotFoundException;
import com.eddie.ecommerce.model.TipoEdicion;
import com.eddie.ecommerce.service.TipoEdicionService;

public class TipoEdicionServiceImpl implements TipoEdicionService{
	
	private static Logger logger=LogManager.getLogger(TipoEdicionServiceImpl.class);

	TipoEdicionDAO tedao=null;
	
	public TipoEdicionServiceImpl() {
		tedao=new TipoEdicionDAOImpl();
	}
	
	@Override
	public TipoEdicion findbyIdTipoEdicion(Integer id, String idioma) throws InstanceNotFoundException, DataException {
		
		if(logger.isDebugEnabled()) {
			logger.debug("id= "+id+" , idioma = "+idioma);
		}
		TipoEdicion te=null;
		boolean commit=false;
		Connection c=null;
		try {
		c=ConnectionManager.getConnection();
		c.setAutoCommit(false);
		
		te = tedao.findbyIdTipoEdicion(c, id, idioma);	
				
		}catch(DataException e) {
			logger.error(e.getMessage(),e);
		} catch (SQLException e) {
			logger.error(e.getMessage(),e);
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
		return te;
	}

	@Override
	public List<TipoEdicion> findAll(String idioma) throws DataException {
		
		if(logger.isDebugEnabled()) {
			logger.debug("Idioma = "+idioma);
		}
		
		Cache<String, List> cacheTipoEdicion= CacheManager.getInstance().getCache(CacheNames.TIPOEDICIONCACHE, String.class, List.class);
		
		List<TipoEdicion> tipoEdicion=cacheTipoEdicion.get(idioma);
		
		boolean commit=false;
		if(tipoEdicion!=null) {
			if (logger.isDebugEnabled()) {
				logger.debug("Acierto cache: {}", idioma);
			}
		}else {
			if (logger.isDebugEnabled()) {
				logger.debug("Fallo cache: {}", idioma);
			}
			Connection c=null;
			try {
			c=ConnectionManager.getConnection();
			c.setAutoCommit(false);
			
			tipoEdicion=tedao.findAll(c, idioma);
			
			cacheTipoEdicion.put(idioma, tipoEdicion);
			
			}catch(SQLException e) {
				logger.error(e.getMessage(),e);
			}finally {
				JDBCUtils.closeConnection(c, commit);
			}
		}
		return tipoEdicion;
	}

}
