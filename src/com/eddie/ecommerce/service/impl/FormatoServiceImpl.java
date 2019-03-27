package com.eddie.ecommerce.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.eddie.ecommerce.cache.Cache;
import com.eddie.ecommerce.cache.CacheManager;
import com.eddie.ecommerce.cache.CacheNames;
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
	public List<Formato> findbyIdsFormato(List<Integer> ids, String idioma) throws InstanceNotFoundException, DataException {
		
		if(logger.isDebugEnabled()) {
			logger.debug("id= "+ids+" , idioma = "+idioma);
		}
		List<Formato> f=null;
		boolean commit=false;
		Connection c=null;
		try {
		c=ConnectionManager.getConnection();
		c.setAutoCommit(false);
			
		f = fdao.findbyIdsFormato(c, ids, idioma);	
				
		}catch(DataException e) {
			logger.error(e.getMessage(),e);
		} catch (SQLException e) {
			logger.error(e.getMessage(),e);
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
		return f;
	}

	@Override
	public List<Formato> findAll(String idioma) throws DataException  {
		
		if(logger.isDebugEnabled()) {
			logger.debug("Idioma = "+idioma);
		}
		
		Cache<String, List> cacheFormato= CacheManager.getInstance().getCache(CacheNames.FORMATOCACHE, String.class, List.class);
		
		List<Formato> formato=cacheFormato.get(idioma);
		
		boolean commit=false;
		if(formato!=null) {
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
			
			formato=fdao.findAll(c, idioma);
			
			cacheFormato.put(idioma, formato);
			
			}catch(SQLException e) {
				logger.error(e.getMessage(),e);
			}finally {
				JDBCUtils.closeConnection(c, commit);
			}
		}
		return formato;
	}

}
