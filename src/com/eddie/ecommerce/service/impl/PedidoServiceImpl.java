package com.eddie.ecommerce.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.eddie.ecommerce.dao.PedidoDAO;
import com.eddie.ecommerce.dao.Utils.ConnectionManager;
import com.eddie.ecommerce.dao.Utils.JDBCUtils;
import com.eddie.ecommerce.dao.impl.PedidoDAOImpl;
import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.exceptions.DuplicateInstanceException;
import com.eddie.ecommerce.exceptions.InstanceNotFoundException;
import com.eddie.ecommerce.model.Pedido;
import com.eddie.ecommerce.service.PedidoService;

public class PedidoServiceImpl implements PedidoService{
	
	private static Logger logger=LogManager.getLogger(PedidoServiceImpl.class);
	
	PedidoDAO pdao=null;
	public PedidoServiceImpl() {
		pdao= new PedidoDAOImpl();
	}
	
	@Override
	public List<Pedido> findByEmail(String email) throws InstanceNotFoundException, SQLException, DataException {
		
		if(logger.isDebugEnabled()) {
			logger.debug("Email = "+email);
		}
		
		boolean commit=false;
		Connection c=null;
		try {
		c=ConnectionManager.getConnection();
		c.setAutoCommit(false);
		
		List<Pedido> pedidos=pdao.findByEmail(c, email);
		
		return pedidos;
		
		}catch(SQLException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
	}

	@Override
	public void delete(Integer idPedido) throws InstanceNotFoundException, SQLException, DataException {
		
		if(logger.isDebugEnabled()) {
			logger.debug("Id= "+idPedido);
		}
		
		boolean commit=false;
		Connection c=null;
		try {
		c=ConnectionManager.getConnection();
		c.setAutoCommit(false);
		
		pdao.delete(c, idPedido);

		commit=true;

		}catch(SQLException ed) {
			logger.error(ed.getMessage(),ed);
			throw ed;
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
	}

	@Override
	public Pedido create(Pedido p) throws DuplicateInstanceException, SQLException, DataException {
		
		if(logger.isDebugEnabled()) {
			logger.debug("Pedido = "+p.toString());
		}
		
		
		boolean commit=false;
		Connection c=null;
		try {
		c=ConnectionManager.getConnection();
		c.setAutoCommit(false);
		
		p = pdao.create(c,p);

		commit=true;
		
		return p;
		
		}catch(SQLException ed) {
			logger.error(ed.getMessage(),ed);
			throw ed;
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
	}

	@Override
	public Pedido findByID(Integer idPedido) throws DataException, SQLException {
		
		if(logger.isDebugEnabled()) {
			logger.debug("id= "+idPedido);
		}
		
		
		boolean commit=false;
		Connection c=null;
		try {
		c=ConnectionManager.getConnection();
		c.setAutoCommit(false);
		
		Pedido p = pdao.findByID(c,idPedido);

		commit=true;
		
		return p;
		
		}catch(SQLException ed) {
			logger.error(ed.getMessage(),ed);
			throw ed;
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
	}

}
