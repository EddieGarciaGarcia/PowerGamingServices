package com.eddie.ecommerce.service.impl;


import com.eddie.ecommerce.dao.CategoriaDAO;
import com.eddie.ecommerce.dao.impl.CategoriaDAOImpl;
import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.model.Categoria;
import com.eddie.ecommerce.service.CategoriaService;
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


public class CategoriaServiceImpl implements CategoriaService {

	private static Logger logger=LogManager.getLogger(CategoriaServiceImpl.class);
	
	private CategoriaDAO categoriaDAO =null;
	
	public CategoriaServiceImpl() {
		categoriaDAO =new CategoriaDAOImpl();
	}
	
	@Override
	public Categoria findById(Integer id, String idioma) throws DataException {
		

		Categoria categoria = null;
		boolean commit=false;
		Connection connection=null;
		try {
		connection= ConnectionManager.getConnection();
		connection.setAutoCommit(false);
		
		categoria = categoriaDAO.findById(connection, id, idioma);
		
		}catch(DataException e) {
			logger.error(e.getMessage(),e);
		} catch (SQLException e) {
			logger.error(e.getMessage(),e);;
		}finally {
			JDBCUtils.closeConnection(connection, commit);
		}
		return categoria;
	}

	@Override
	public List<Categoria> findAll(String idioma) throws DataException {
		
		if(logger.isDebugEnabled()) {
			logger.debug("Idioma = "+idioma);
		}

		//Cache<String, List> cache= CacheManager.getCachePG(Constantes.NOMBRE_CACHE_ESTATICOS);
		
		List<Categoria> categoria = null /*= cache.get(Constantes.CACHE_CATEGORIA)*/;
		
		boolean commit=false;
		
		/*if(categoria!=null) {
			if (logger.isDebugEnabled()) {
				logger.debug("Acierto cache: {}", categoria.size());
			}
		}else {*/
			if (logger.isDebugEnabled()) {
				logger.debug("Fallo cache");
			}
			Connection connection=null;
			try {
				connection=ConnectionManager.getConnection();
				connection.setAutoCommit(false);
				
				categoria= categoriaDAO.findAll(connection, idioma);

				/*cache.put(Constantes.CACHE_CATEGORIA, categoria);*/
			
			}catch(SQLException e) {
				logger.error(e.getMessage(),e);
			}finally {
				JDBCUtils.closeConnection(connection, commit);
			}
		/*}*/
		return categoria;
	}

	@Override
	public List<Categoria> findByJuego(Integer idJuego, String idioma) throws DataException {
		
		if(logger.isDebugEnabled()) {
			logger.debug("id= "+idJuego+" , idioma = "+idioma);
		}
		List<Categoria> categorias=null;
		boolean commit=false;
		Connection connection=null;
		try {
		connection=ConnectionManager.getConnection();
		connection.setAutoCommit(false);
		
		categorias= categoriaDAO.findByJuego(connection, idJuego, idioma);

		}catch(SQLException e) {
			logger.error(e.getMessage(),e);
		}finally {
			JDBCUtils.closeConnection(connection, commit);
		}
		return categorias;
	}

}
