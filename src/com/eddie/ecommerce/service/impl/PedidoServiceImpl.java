package com.eddie.ecommerce.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
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
	
	PedidoDAO pdao=null;
	public PedidoServiceImpl() {
		pdao= new PedidoDAOImpl();
	}
	
	@Override
	public List<Pedido> findByEmail(String email) throws InstanceNotFoundException, SQLException, DataException {
		boolean commit=false;
		Connection c=null;
		try {
		c=ConnectionManager.getConnection();
		c.setAutoCommit(false);
		
		List<Pedido> pedidos=pdao.findByEmail(c, email);
		
		return pedidos;
		
		}catch(SQLException e) {
			e.printStackTrace();
			throw e;
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
	}

	@Override
	public void delete(Integer idPedido) throws InstanceNotFoundException, SQLException, DataException {
		boolean commit=false;
		Connection c=null;
		try {
		c=ConnectionManager.getConnection();
		c.setAutoCommit(false);
		
		pdao.delete(c, idPedido);

		commit=true;

		}catch(SQLException ed) {
			ed.printStackTrace();
			throw ed;
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
	}

	@Override
	public Pedido create(Pedido p) throws DuplicateInstanceException, SQLException, DataException {
		boolean commit=false;
		Connection c=null;
		try {
		c=ConnectionManager.getConnection();
		c.setAutoCommit(false);
		
		p = pdao.create(c,p);

		commit=true;
		
		return p;
		
		}catch(SQLException ed) {
			ed.printStackTrace();
			throw ed;
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
	}

	@Override
	public Pedido findByID(Integer idPedido) throws DataException, SQLException {
		boolean commit=false;
		Connection c=null;
		try {
		c=ConnectionManager.getConnection();
		c.setAutoCommit(false);
		
		Pedido p = pdao.findByID(c,idPedido);

		commit=true;
		
		return p;
		
		}catch(SQLException ed) {
			ed.printStackTrace();
			throw ed;
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
	}

}
