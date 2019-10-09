package com.eddie.ecommerce.service.impl;

import com.eddie.ecommerce.dao.IdiomaDAO;
import com.eddie.ecommerce.dao.impl.IdiomaDAOImpl;
import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.model.Idioma;
import com.eddie.ecommerce.service.IdiomaService;
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

public class IdiomaServiceImpl implements IdiomaService{
	
	private static Logger logger=LogManager.getLogger(IdiomaServiceImpl.class);
	
	IdiomaDAO idiomaDAO =null;
	
	public IdiomaServiceImpl() {
		idiomaDAO =new IdiomaDAOImpl();
	}
	
	@Override
	public Idioma findById(String id, String idiomaWeb) throws DataException {
		
		if(logger.isDebugEnabled()) {
			logger.debug("id= "+id+" , idioma = "+idiomaWeb);
		}
		Idioma idioma=null;
		boolean commit=false;
		Connection connection=null;
		try {
		connection= ConnectionManager.getConnection();
		connection.setAutoCommit(false);
		
		idioma = idiomaDAO.findById(connection, id, idiomaWeb);
				
		}catch(DataException e) {
			logger.error(e.getMessage(),e);
		} catch (SQLException e) {
			logger.error(e.getMessage(),e);
		}finally {
			JDBCUtils.closeConnection(connection, commit);
		}
		return idioma;
	}

	@Override
	public List<Idioma> findAll(String idiomaWeb) throws DataException {
		
		if(logger.isDebugEnabled()) {
			logger.debug("Idioma = "+idiomaWeb);
		}
		
		Cache<String, List> cacheIdioma= CacheManager.getCachePG(Constantes.NOMBRE_CACHE_ESTATICOS);
		
		List<Idioma> idiomas=cacheIdioma.get(Constantes.CACHE_IDIOMA);
		
		boolean commit=false;
		if(idiomas!=null) {
			if (logger.isDebugEnabled()) {
				logger.debug("Acierto cache: {}", idiomaWeb);
			}
		}else {
			if (logger.isDebugEnabled()) {
				logger.debug("Fallo cache: {}", idiomaWeb);
			}
			Connection connection=null;
			try {
			connection=ConnectionManager.getConnection();
			connection.setAutoCommit(false);
			
			idiomas= idiomaDAO.findAll(connection, idiomaWeb);
			
			cacheIdioma.put(Constantes.CACHE_IDIOMA, idiomas);
			
			}catch(SQLException e) {
				logger.error(e.getMessage(),e);
			}finally {
				JDBCUtils.closeConnection(connection, commit);
			}
		}
		return idiomas;
	}

	@Override
	public List<Idioma> findByJuego(Integer idJuego, String idiomaWeb) throws DataException {
		
		if(logger.isDebugEnabled()) {
			logger.debug("id= "+idJuego+" , idioma = "+idiomaWeb);
		}
		List<Idioma> idiomas=null;
		boolean commit=false;
		Connection connection=null;
		try {
		connection=ConnectionManager.getConnection();
		connection.setAutoCommit(false);
		
		idiomas= idiomaDAO.findByJuego(connection, idJuego, idiomaWeb);
		
		}catch(SQLException e) {
			logger.error(e.getMessage(),e);
		}finally {
			JDBCUtils.closeConnection(connection, commit);
		}
		return idiomas;
	}

}
