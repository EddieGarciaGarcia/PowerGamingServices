package com.eddie.ecommerce.service.impl;

import com.eddie.ecommerce.dao.CreadorDAO;
import com.eddie.ecommerce.dao.impl.CreadorDAOImpl;
import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.model.Creador;
import com.eddie.ecommerce.service.CreadorService;
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

public class CreadorServiceImpl implements CreadorService{

	private static Logger logger=LogManager.getLogger(CreadorServiceImpl.class);
	
	CreadorDAO creadorDAO =null;
	
	public CreadorServiceImpl() {
		creadorDAO =new CreadorDAOImpl();
	}
	
	@Override
	public Creador findbyIdCreador(Integer id) throws DataException {
		
		if(logger.isDebugEnabled()) {
			logger.debug("id= "+id);
		}
		Creador creador=null;
		boolean commit=false;
		Connection connection=null;
		try {
		connection= ConnectionManager.getConnection();
		connection.setAutoCommit(false);
		
		creador= creadorDAO.findbyIdCreador(connection, id);

		}catch(DataException e) {
			logger.error(e.getMessage(),e);
		} catch (SQLException e) {
			logger.error(e.getMessage(),e);
		}finally {
			JDBCUtils.closeConnection(connection, commit);
		}
		return creador;
	}

	@Override
	public List<Creador> findAll() throws DataException {
		int i=1;

		Cache<String, List> cache= CacheManager.getCachePG(Constantes.NOMBRE_CACHE_ESTATICOS);
		
		List<Creador> creador=cache.get(Constantes.CACHE_CREADOR);
		
		boolean commit=false;
		if(creador!=null) {
			if (logger.isDebugEnabled()) {
				logger.debug("Acierto cache: {}", i);
			}
		}else {
			if (logger.isDebugEnabled()) {
				logger.debug("Fallo cache: {}", i);
			}
			Connection connection=null;
			try {
			connection=ConnectionManager.getConnection();
			connection.setAutoCommit(false);

			creador= creadorDAO.findAll(connection);

			cache.put(Constantes.CACHE_CREADOR, creador);
			
			}catch(SQLException e) {
				logger.error(e.getMessage(),e);
			}finally {
				JDBCUtils.closeConnection(connection, commit);
			}
		}
		return creador;
	}

}
