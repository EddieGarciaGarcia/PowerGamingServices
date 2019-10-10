package com.eddie.ecommerce.service.impl;

import com.eddie.ecommerce.dao.PedidoDAO;
import com.eddie.ecommerce.dao.impl.PedidoDAOImpl;
import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.model.Pedido;
import com.eddie.ecommerce.model.Resultados;
import com.eddie.ecommerce.service.PedidoService;
import com.eddie.ecommerce.utils.ConnectionManager;
import com.eddie.ecommerce.utils.JDBCUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PedidoServiceImpl implements PedidoService{
	
	private static Logger logger=LogManager.getLogger(PedidoServiceImpl.class);
	
	PedidoDAO pdao=null;
	public PedidoServiceImpl() {
		pdao= new PedidoDAOImpl();
	}

	@Override
	public Resultados<Pedido> findByEmail(String email, int startIndex, int count) throws DataException {

		if(logger.isDebugEnabled()) {
			logger.debug("Email = "+email);
		}
		Resultados<Pedido> pedidos=null;
		boolean commit=false;
		Connection c=null;
		try {
			c= ConnectionManager.getConnection();
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
	public List<Pedido> findAllByEmail(String email) throws DataException {
		
		if(logger.isDebugEnabled()) {
			logger.debug("Email = "+email);
		}
		List<Pedido> pedidos=null;
		boolean commit=false;
		Connection c=null;
		try {
		c= ConnectionManager.getConnection();
		c.setAutoCommit(false);
		
		pedidos=pdao.findAllByEmail(c, email);
		
		}catch(SQLException e) {
			logger.error(e.getMessage(),e);
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
		return pedidos;
	}

	@Override
	public void delete(Integer idPedido) throws DataException {
		
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
	public boolean create(Pedido p) throws DataException {
		
		if(logger.isDebugEnabled()) {
			logger.debug("Pedido = "+p.toString());
		}
		boolean creado = false;
		boolean commit=false;
		Connection c=null;
		try {
		c=ConnectionManager.getConnection();
		c.setAutoCommit(false);

			creado = pdao.create(c,p);

		commit=true;

		}catch(SQLException ed) {
			logger.error(ed.getMessage(),ed);
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
		return creado;
	}

	@Override
	public Pedido findByEmail(String email) throws DataException {
		
		if(logger.isDebugEnabled()) {
			logger.debug("id= "+email);
		}
		
		Pedido p =null;
		boolean commit=false;
		Connection c=null;
		try {
		c=ConnectionManager.getConnection();
		c.setAutoCommit(false);
		
		p = pdao.findByEmail(c,email);

		commit=true;

		}catch(SQLException ed) {
			logger.error(ed.getMessage(),ed);
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
		return p;
	}

	@Override
	public List<Pedido> findByIds(List<Integer> ids) throws DataException {
		if(logger.isDebugEnabled()) {
			logger.debug("IdJuego = "+ids);
		}
		List<Pedido> pedido = null;
		boolean commit=false;
		Connection c=null;
		try {
		c=ConnectionManager.getConnection();
		c.setAutoCommit(false);
		
		pedido=pdao.findByIds(c, ids);
		
		}catch(SQLException e) {
			logger.error(e.getMessage(),e);
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
		return pedido;
	}

}
