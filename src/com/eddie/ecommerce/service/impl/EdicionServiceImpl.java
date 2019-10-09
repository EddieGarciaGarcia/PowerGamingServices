package com.eddie.ecommerce.service.impl;

import com.eddie.ecommerce.dao.EdicionDAO;
import com.eddie.ecommerce.dao.impl.EdicionDAOImpl;
import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.model.Edicion;
import com.eddie.ecommerce.service.EdicionService;
import com.eddie.ecommerce.utils.ConnectionManager;
import com.eddie.ecommerce.utils.JDBCUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class EdicionServiceImpl implements EdicionService{

	private static Logger logger=LogManager.getLogger(EdicionServiceImpl.class);
	
	private EdicionDAO edicionDAO =null;
	public EdicionServiceImpl() {
		edicionDAO = new EdicionDAOImpl();
	}
	
	
	@Override
	public Edicion finById(Integer id) throws DataException {
		if(logger.isDebugEnabled()) {
			logger.debug("Id = "+id);
		}
		Edicion edicion=null;
		boolean commit=false;
		Connection connection=null;
		try {
		connection= ConnectionManager.getConnection();
		connection.setAutoCommit(false);
		
		edicion= edicionDAO.findByIdEdicion(connection, id);
		
		}catch(SQLException e) {
			logger.error(e.getMessage(),e);
		}finally {
			JDBCUtils.closeConnection(connection, commit);
		}
		return edicion;
	}
	
	@Override
	public List<Edicion> findByIdJuego(Integer id) throws DataException {
		
		if(logger.isDebugEnabled()) {
			logger.debug("Id = "+id);
		}
		List<Edicion> ediciones=null;
		boolean commit=false;
		Connection connection=null;
		try {
		connection=ConnectionManager.getConnection();
		connection.setAutoCommit(false);
		
		ediciones= edicionDAO.findByIdJuego(connection, id);
		
		}catch(SQLException e) {
			logger.error(e.getMessage(),e);
		}finally {
			JDBCUtils.closeConnection(connection, commit);
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
		Connection connection=null;
		try {
		connection=ConnectionManager.getConnection();
		connection.setAutoCommit(false);
		
		ediciones= edicionDAO.findByIdsJuego(connection, ids);
		
		}catch(SQLException e) {
			logger.error(e.getMessage(),e);
		}finally {
			JDBCUtils.closeConnection(connection, commit);
		}
		return ediciones;
	}

	@Override
	public boolean create(Edicion edicion) throws DataException {
		
		if(logger.isDebugEnabled()) {
			logger.debug("Edicion = "+edicion.toString());
		}
		boolean creado=false;
		boolean commit=false;
		Connection connection=null;
		try {
		connection=ConnectionManager.getConnection();
		connection.setAutoCommit(false);

		commit=true;

		creado = edicionDAO.create(connection,edicion);
		}catch(SQLException ed) {
			logger.error(ed.getMessage(),ed);
		}finally {
			JDBCUtils.closeConnection(connection, commit);
		}
		return creado;
	}

	@Override
	public boolean update(Edicion edicion) throws DataException {
		
		if(logger.isDebugEnabled()) {
			logger.debug("Edicion = "+edicion.toString());
		}
		
		boolean commit=false;
		Connection connection=null;
		try {
	          
            connection = ConnectionManager.getConnection();

            connection.setAutoCommit(false);

            edicionDAO.update(connection,edicion);
            commit = true;
            
        } catch (SQLException ed) {
        	logger.error(ed.getMessage(),ed);
            throw new DataException(ed);
        } finally {
        	JDBCUtils.closeConnection(connection, commit);
        }
		return true;
	}
}
