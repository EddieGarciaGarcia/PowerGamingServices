package com.eddie.ecommerce.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.eddie.ecommerce.dao.LineaPedidoDAO;
import com.eddie.ecommerce.dao.Utils.ConnectionManager;
import com.eddie.ecommerce.dao.Utils.JDBCUtils;
import com.eddie.ecommerce.dao.impl.LineaPedidoDAOImpl;
import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.exceptions.DuplicateInstanceException;
import com.eddie.ecommerce.exceptions.InstanceNotFoundException;
import com.eddie.ecommerce.model.Edicion;
import com.eddie.ecommerce.model.Juego;
import com.eddie.ecommerce.model.LineaPedido;
import com.eddie.ecommerce.service.LineaPedidoService;

public class LineaPedidoServiceImpl implements LineaPedidoService{

	LineaPedidoDAO lpdao=null;
	
	public LineaPedidoServiceImpl() {
		lpdao=new LineaPedidoDAOImpl();
	}
	
	
	@Override
	public List<LineaPedido> findByPedido(Integer idPedido) throws DataException,SQLException {
		boolean commit=false;
		Connection c=null;
		try {
		c=ConnectionManager.getConnection();
		c.setAutoCommit(false);
		
		List<LineaPedido> lineaspedido=lpdao.findByPedido(c, idPedido);
		
		return lineaspedido;
		
		}catch(SQLException e) {
			e.printStackTrace();
			throw e;
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
	}

	@Override
	public LineaPedido findById(Integer numeroLinea) throws InstanceNotFoundException, DataException, SQLException {
		boolean commit=false;
		Connection c=null;
		try {
		c=ConnectionManager.getConnection();
		c.setAutoCommit(false);
		
		
		LineaPedido lp = lpdao.findById(c,numeroLinea);
		
		return lp;
		
		}catch(SQLException e) {
			e.printStackTrace();
			throw e;
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
	}

	@Override
	public LineaPedido create(LineaPedido lp) throws DuplicateInstanceException, DataException, SQLException {
		boolean commit=false;
		Connection c=null;
		try {
		c=ConnectionManager.getConnection();
		c.setAutoCommit(false);
		
		lp = lpdao.create(c,lp);

		commit=true;
		
		return lp;
		
		}catch(SQLException ed) {
			ed.printStackTrace();
			throw ed;
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
	}

	@Override
	public void delete(LineaPedido lp) throws DataException {
		boolean commit=false;
		Connection c=null;
		try {
	          
            c = ConnectionManager.getConnection();

            c.setAutoCommit(false);

            lpdao.delete(c,lp);
            commit = true;
            
        } catch (SQLException ed) {
            throw new DataException(ed);

        } finally {
        	JDBCUtils.closeConnection(c, commit);
        }
		
	}

}
