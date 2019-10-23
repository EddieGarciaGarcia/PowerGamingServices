package com.eddie.ecommerce.service.impl;

import com.eddie.ecommerce.dao.FormatoDAO;
import com.eddie.ecommerce.dao.impl.FormatoDAOImpl;
import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.model.Formato;
import com.eddie.ecommerce.service.FormatoService;
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

public class FormatoServiceImpl implements FormatoService{
	
	private static Logger logger=LogManager.getLogger(FormatoServiceImpl.class);
	
	FormatoDAO formatoDAO =null;
	
	public FormatoServiceImpl() {
		formatoDAO =new FormatoDAOImpl();
	}
	@Override
	public List<Formato> findbyIdsFormato(List<Integer> ids, String idioma) throws DataException {
		
		if(logger.isDebugEnabled()) {
			logger.debug("id= "+ids+" , idioma = "+idioma);
		}
		List<Formato> formato=null;
		boolean commit=false;
		Connection connection=null;
		try {
		connection= ConnectionManager.getConnection();
		connection.setAutoCommit(false);
			
		formato = formatoDAO.findbyIdsFormato(connection, ids, idioma);
				
		}catch(DataException e) {
			logger.error(e.getMessage(),e);
		} catch (SQLException e) {
			logger.error(e.getMessage(),e);
		}finally {
			JDBCUtils.closeConnection(connection, commit);
		}
		return formato;
	}

	@Override
	public List<Formato> findAll(String idioma) throws DataException  {
		
		if(logger.isDebugEnabled()) {
			logger.debug("Idioma = "+idioma);
		}
		
		/*Cache<String, List> cache= CacheManager.getCachePG(Constantes.NOMBRE_CACHE_ESTATICOS);*/
		
		List<Formato> formato=null/*cache.get(Constantes.CACHE_FORMATO)*/;
		
		boolean commit=false;
		/*if(formato!=null) {
			if (logger.isDebugEnabled()) {
				logger.debug("Acierto cache: {}", idioma);
			}
		}else {*/
			if (logger.isDebugEnabled()) {
				logger.debug("Fallo cache: {}", idioma);
			}
			Connection connection=null;
			try {
			connection=ConnectionManager.getConnection();
			connection.setAutoCommit(false);
			
			formato= formatoDAO.findAll(connection, idioma);
			
			/*cache.put(Constantes.CACHE_FORMATO, formato);*/
			
			}catch(SQLException e) {
				logger.error(e.getMessage(),e);
			}finally {
				JDBCUtils.closeConnection(connection, commit);
			}
		/*}*/
		return formato;
	}
}
