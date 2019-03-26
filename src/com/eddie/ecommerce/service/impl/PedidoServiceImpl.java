package com.eddie.ecommerce.service.impl;

import java.sql.Connection;
import java.sql.SQLException;

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
import com.eddie.ecommerce.service.Resultados;

public class PedidoServiceImpl implements PedidoService{
	
	private static Logger logger=LogManager.getLogger(PedidoServiceImpl.class);
	
	PedidoDAO pdao=null;
	public PedidoServiceImpl() {
		pdao= new PedidoDAOImpl();
	}
	
	@Override
	public Resultados<Pedido> findByEmail(String email, int startIndex, int count) throws InstanceNotFoundException, DataException {
		
		if(logger.isDebugEnabled()) {
			logger.debug("Email = "+email);
		}
		Resultados<Pedido> pedidos=null;
		boolean commit=false;
		Connection c=null;
		try {
		c=ConnectionManager.getConnection();
		c.setAutoCommit(false);
		
		pedidos=pdao.findByEmail(c, email, startIndex, count);
		
		}catch(SQLException e) {
			logger.error(e.getMessage(),e);
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
		return pedidos;
	}

	@Override
	public void delete(Integer idPedido) throws InstanceNotFoundException, DataException {
		
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
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
	}

	@Override
	public Pedido create(Pedido p) throws DuplicateInstanceException, DataException {
		
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

		}catch(SQLException ed) {
			logger.error(ed.getMessage(),ed);
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
		return p;
	}

	@Override
	public Pedido findByID(Integer idPedido) throws DataException {
		
		if(logger.isDebugEnabled()) {
			logger.debug("id= "+idPedido);
		}
		
		Pedido p =null;
		boolean commit=false;
		Connection c=null;
		try {
		c=ConnectionManager.getConnection();
		c.setAutoCommit(false);
		
		p = pdao.findByID(c,idPedido);

		commit=true;

		}catch(SQLException ed) {
			logger.error(ed.getMessage(),ed);
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
		return p;
	}

}
