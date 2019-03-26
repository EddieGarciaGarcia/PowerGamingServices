package com.eddie.ecommerce.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.eddie.ecommerce.dao.EdicionDAO;
import com.eddie.ecommerce.dao.Utils.ConnectionManager;
import com.eddie.ecommerce.dao.Utils.JDBCUtils;
import com.eddie.ecommerce.dao.impl.EdicionDAOImpl;
import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.exceptions.DuplicateInstanceException;
import com.eddie.ecommerce.exceptions.InstanceNotFoundException;
import com.eddie.ecommerce.model.Edicion;
import com.eddie.ecommerce.service.EdicionService;

public class EdicionServiceImpl implements EdicionService{

	private static Logger logger=LogManager.getLogger(EdicionServiceImpl.class);
	
	EdicionDAO edao=null;
	public EdicionServiceImpl() {
		edao= new EdicionDAOImpl();
	}
	
	@Override
	public List<Edicion> findByIdJuego(Integer id) throws DataException {
		
		if(logger.isDebugEnabled()) {
			logger.debug("Id = "+id);
		}
		List<Edicion> ediciones=null;
		boolean commit=false;
		Connection c=null;
		try {
		c=ConnectionManager.getConnection();
		c.setAutoCommit(false);
		
		ediciones=edao.findByIdJuego(c, id);
		
		}catch(SQLException e) {
			logger.error(e.getMessage(),e);
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
		return ediciones;
	}
	
	@Override
	public List<Edicion> findByIdsJuego(List<Integer> ids) throws DataException {
		if(logger.isDebugEnabled()) {
			logger.debug("Id = "+ids);
		}
		List<Edicion> ediciones=null;
		boolean commit=false;
		Connection c=null;
		try {
		c=ConnectionManager.getConnection();
		c.setAutoCommit(false);
		
		ediciones=edao.findByIdsJuego(c, ids);
		
		}catch(SQLException e) {
			logger.error(e.getMessage(),e);
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
		return ediciones;
	}

	@Override
	public Edicion create(Edicion e) throws DuplicateInstanceException, DataException {
		
		if(logger.isDebugEnabled()) {
			logger.debug("Edicion = "+e.toString());
		}
		
		boolean commit=false;
		Connection c=null;
		try {
		c=ConnectionManager.getConnection();
		c.setAutoCommit(false);
		
		e = edao.create(c,e);

		commit=true;
		
		}catch(SQLException ed) {
			logger.error(ed.getMessage(),ed);
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
		return e;
	}

	@Override
	public boolean update(Edicion e) throws InstanceNotFoundException, DataException {
		
		if(logger.isDebugEnabled()) {
			logger.debug("Edicion = "+e.toString());
		}
		
		boolean commit=false;
		Connection c=null;
		try {
	          
            c = ConnectionManager.getConnection();

            c.setAutoCommit(false);

            edao.update(c,e);
            commit = true;
            
        } catch (SQLException ed) {
        	logger.error(ed.getMessage(),ed);
            throw new DataException(ed);
        } finally {
        	JDBCUtils.closeConnection(c, commit);
        }
		return true;
	}

	

}
