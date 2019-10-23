package com.eddie.ecommerce.service.impl;

import com.eddie.ecommerce.dao.TipoEdicionDAO;
import com.eddie.ecommerce.dao.impl.TipoEdicionDAOImpl;
import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.exceptions.InstanceNotFoundException;
import com.eddie.ecommerce.model.TipoEdicion;
import com.eddie.ecommerce.service.TipoEdicionService;
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

public class TipoEdicionServiceImpl implements TipoEdicionService{
	
	private static Logger logger=LogManager.getLogger(TipoEdicionServiceImpl.class);

	TipoEdicionDAO tedao=null;
	
	public TipoEdicionServiceImpl() {
		tedao=new TipoEdicionDAOImpl();
	}
	
	@Override
	public List<TipoEdicion> findbyIdsTipoEdicion(List<Integer> ids, String idioma) throws InstanceNotFoundException, DataException {
		
		if(logger.isDebugEnabled()) {
			logger.debug("id= "+ids+" , idioma = "+idioma);
		}
		List<TipoEdicion> te=null;
		boolean commit=false;
		Connection c=null;
		try {
		c=ConnectionManager.getConnection();
		c.setAutoCommit(false);
		
		te = tedao.findbyIdsTipoEdicion(c, ids, idioma);	
				
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
		
		/*Cache<String, List> cacheTipoEdicion= CacheManager.getCachePG(Constantes.NOMBRE_CACHE_ESTATICOS);*/
		
		List<TipoEdicion> tipoEdicion=null/*cacheTipoEdicion.get(Constantes.CACHE_TIPO_EDICION)*/;
		
		boolean commit=false;
		/*if(tipoEdicion!=null) {
			if (logger.isDebugEnabled()) {
				logger.debug("Acierto cache: {}", idioma);
			}
		}else {*/
			if (logger.isDebugEnabled()) {
				logger.debug("Fallo cache: {}", idioma);
			}
			Connection c=null;
			try {
			c= ConnectionManager.getConnection();
			c.setAutoCommit(false);
			
			tipoEdicion=tedao.findAll(c, idioma);
			
			/*cacheTipoEdicion.put(Constantes.CACHE_TIPO_EDICION, tipoEdicion);*/
			
			}catch(SQLException e) {
				logger.error(e.getMessage(),e);
			}finally {
				JDBCUtils.closeConnection(c, commit);
			}
		/*}*/
		return tipoEdicion;
	}

}
