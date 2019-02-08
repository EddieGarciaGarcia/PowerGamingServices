package com.eddie.ecommerce.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.eddie.ecommerce.dao.LineaPedidoDAO;
import com.eddie.ecommerce.dao.Utils.ConnectionManager;
import com.eddie.ecommerce.dao.Utils.JDBCUtils;
import com.eddie.ecommerce.dao.impl.LineaPedidoDAOImpl;
import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.exceptions.DuplicateInstanceException;
import com.eddie.ecommerce.exceptions.InstanceNotFoundException;
import com.eddie.ecommerce.model.LineaPedido;
import com.eddie.ecommerce.service.LineaPedidoService;

public class LineaPedidoServiceImpl implements LineaPedidoService{
	
	private static Logger logger=LogManager.getLogger(LineaPedidoServiceImpl.class);

	LineaPedidoDAO lpdao=null;
	
	public LineaPedidoServiceImpl() {
		lpdao=new LineaPedidoDAOImpl();
	}
	
	
	@Override
	public List<LineaPedido> findByPedido(Integer idPedido) throws DataException,SQLException {
		
		if(logger.isDebugEnabled()) {
			logger.debug("id= "+idPedido);
		}
		
		boolean commit=false;
		Connection c=null;
		try {
		c=ConnectionManager.getConnection();
		c.setAutoCommit(false);
		
		List<LineaPedido> lineaspedido=lpdao.findByPedido(c, idPedido);
		
		return lineaspedido;
		
		}catch(SQLException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
	}

	@Override
	public LineaPedido findById(Integer numeroLinea) throws InstanceNotFoundException, DataException, SQLException {
		
		if(logger.isDebugEnabled()) {
			logger.debug("Numero linea= "+numeroLinea);
		}
		
		boolean commit=false;
		Connection c=null;
		try {
		c=ConnectionManager.getConnection();
		c.setAutoCommit(false);
		
		
		LineaPedido lp = lpdao.findById(c,numeroLinea);
		
		return lp;
		
		}catch(SQLException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
	}

	@Override
	public LineaPedido create(LineaPedido lp) throws DuplicateInstanceException, DataException, SQLException {
		
		if(logger.isDebugEnabled()) {
			logger.debug("Linea Pedido = "+lp.toString());
		}
		
		boolean commit=false;
		Connection c=null;
		try {
		c=ConnectionManager.getConnection();
		c.setAutoCommit(false);
		
		lp = lpdao.create(c,lp);

		commit=true;
		
		return lp;
		
		}catch(SQLException ed) {
			logger.error(ed.getMessage(),ed);
			throw ed;
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
	}

	@Override
	public void delete(Integer id) throws DataException {
		
		if(logger.isDebugEnabled()) {
			logger.debug("Id= "+id);
		}
		
		boolean commit=false;
		Connection c=null;
		try {
	          
            c = ConnectionManager.getConnection();

            c.setAutoCommit(false);

            lpdao.delete(c,id);
            commit = true;
            
        } catch (SQLException ed) {
        	logger.error(ed.getMessage(),ed);
            throw new DataException(ed);

        } finally {
        	JDBCUtils.closeConnection(c, commit);
        }
		
	}

}
