package com.eddie.ecommerce.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.eddie.ecommerce.cache.Cache;
import com.eddie.ecommerce.cache.CacheManager;
import com.eddie.ecommerce.cache.CacheNames;
import com.eddie.ecommerce.dao.CreadorDAO;
import com.eddie.ecommerce.dao.Utils.ConnectionManager;
import com.eddie.ecommerce.dao.Utils.JDBCUtils;
import com.eddie.ecommerce.dao.impl.CreadorDAOImpl;
import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.exceptions.InstanceNotFoundException;
import com.eddie.ecommerce.model.Categoria;
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
		int i=1;
		
		Cache<Integer, List> cacheCreador= CacheManager.getInstance().getCache(CacheNames.CREADORCACHE, Integer.class, List.class);
		
		List<Creador> creador=cacheCreador.get(i);
		
		boolean commit=false;
		if(creador!=null) {
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
			
			creador=cdao.findAll(c);
			
			cacheCreador.put(i, creador);
			
			}catch(SQLException e) {
				logger.error(e.getMessage(),e);
				throw e;
			}finally {
				JDBCUtils.closeConnection(c, commit);
			}
		}
		return creador;
	}

}
