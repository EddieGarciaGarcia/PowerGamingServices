package com.eddie.ecommerce.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.eddie.ecommerce.cache.Cache;
import com.eddie.ecommerce.cache.CacheManager;
import com.eddie.ecommerce.cache.CacheNames;
import com.eddie.ecommerce.dao.CategoriaDAO;
import com.eddie.ecommerce.dao.Utils.ConnectionManager;
import com.eddie.ecommerce.dao.Utils.JDBCUtils;
import com.eddie.ecommerce.dao.impl.CategoriaDAOImpl;
import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.exceptions.InstanceNotFoundException;
import com.eddie.ecommerce.model.Categoria;
import com.eddie.ecommerce.service.CategoriaService;

public class CategoriaServiceImpl implements CategoriaService{

	private static Logger logger=LogManager.getLogger(CategoriaServiceImpl.class);
	
	private CategoriaDAO cdao=null;
	
	public CategoriaServiceImpl() {
		cdao=new CategoriaDAOImpl();
	}
	
	@Override
	public Categoria findById(Integer id, String idioma) throws SQLException, InstanceNotFoundException, DataException {
		
		if(logger.isDebugEnabled()) {
			logger.debug("id= "+id+" , idioma = "+idioma);
		}
		
		boolean commit=false;
		Connection c=null;
		try {
		c=ConnectionManager.getConnection();
		c.setAutoCommit(false);
		
		
		
		Categoria cate = cdao.findById(c, id, idioma);		
		
		return cate;
		
		}catch(DataException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
	}

	@Override
	public List<Categoria> findAll(String idioma) throws SQLException, DataException {
		
		if(logger.isDebugEnabled()) {
			logger.debug("Idioma = "+idioma);
		}
		
		Cache<String, List> cacheCategoria= CacheManager.getInstance().getCache(CacheNames.CATEGORIACACHE, String.class, List.class);
		
		List<Categoria> categoria=cacheCategoria.get(idioma);
		
		boolean commit=false;
		
		if(categoria!=null) {
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
				
				categoria=cdao.findAll(c, idioma);
				
				cacheCategoria.put(idioma, categoria);
			
			}catch(SQLException e) {
				logger.error(e.getMessage(),e);
				throw e;
			}finally {
				JDBCUtils.closeConnection(c, commit);
			}
		}
		return categoria;
	}

	@Override
	public List<Categoria> findByJuego(Integer idJuego, String idioma) throws DataException, SQLException {
		
		if(logger.isDebugEnabled()) {
			logger.debug("id= "+idJuego+" , idioma = "+idioma);
		}
		
		boolean commit=false;
		Connection c=null;
		try {
		c=ConnectionManager.getConnection();
		c.setAutoCommit(false);
		
		List<Categoria> cat=cdao.findByJuego(c, idJuego, idioma);
		
		return cat;
		
		}catch(SQLException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
	}

}
