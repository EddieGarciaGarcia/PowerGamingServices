package com.eddie.ecommerce.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.eddie.ecommerce.dao.PedidoDAO;
import com.eddie.ecommerce.dao.Utils.ConnectionManager;
import com.eddie.ecommerce.dao.Utils.JDBCUtils;
import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.exceptions.DuplicateInstanceException;
import com.eddie.ecommerce.exceptions.InstanceNotFoundException;
import com.eddie.ecommerce.model.Pedido;

public class PedidoDAOImpl implements PedidoDAO{
	
	private static Logger logger=LogManager.getLogger(PedidoDAOImpl.class);

	@Override
	public List<Pedido> findByEmail(Connection conexion,String email) throws InstanceNotFoundException, DataException {
		
		if(logger.isDebugEnabled()) {
			logger.debug("Email = "+email);
		}
		
		Pedido p=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			conexion=ConnectionManager.getConnection();
			String sql;
			sql="sselect id_pedido,email,iva,total,fecha_pedido from pedido where email=?";
			
			pst=conexion.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			
			int i=1;
			//pst.setString(i++,"%"+nombrejuego.toUpperCase()+"%");
			pst.setString(i++, email);	
			rs=pst.executeQuery();
			
			logger.debug(sql);
			
			List<Pedido> pedidos = new ArrayList<Pedido>();
			while(rs.next()){
				p=loadNext(rs);
				pedidos.add(p);
			}
			return pedidos;
		}catch (SQLException ex) {
			logger.error(ex.getMessage(),ex);
			throw new DataException(ex);
		}finally{
			JDBCUtils.closeResultSet(rs);
			JDBCUtils.closeStatement(pst);
		}
	}

	@Override
	public Pedido findByID(Connection conexion,Integer idPedido) throws InstanceNotFoundException, DataException {
		
		if(logger.isDebugEnabled()) {
			logger.debug("Id = "+idPedido);
		}
		
		Pedido p=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			conexion=ConnectionManager.getConnection();
			String sql;
			sql="select id_pedido,email,iva,total,fecha_pedido from pedido where id_pedido=?";
			
			pst=conexion.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			
			int i=1;
			//pst.setString(i++,"%"+nombrejuego.toUpperCase()+"%");
			pst.setInt(i++, idPedido);	
			rs=pst.executeQuery();
			
			logger.debug(sql);
			
			if(rs.next()){
				p=loadNext(rs);
	
			}
			else {
				throw new InstanceNotFoundException("Error "+idPedido+" id introducido incorrecto", Pedido.class.getName());
			}
			return p;
		}catch (SQLException ex) {
			logger.error(ex.getMessage(),ex);
			throw new DataException(ex);
		}finally{
			JDBCUtils.closeResultSet(rs);
			JDBCUtils.closeStatement(pst);
		}
	}

	@Override
	public Pedido create(Connection conexion,Pedido p) throws DuplicateInstanceException, DataException {
		
		if(logger.isDebugEnabled()) {
			logger.debug("Pedido = "+p.toString());
		}
		
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			conexion=ConnectionManager.getConnection();
			String sql;
			sql="Insert Into usuarios_juego(email,iva,total,fecha_pedido) "
					+ "values (?,?,?,?)";
			
			pst=conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			int i=1;
			
			pst.setString(i++,p.getEmail());
			pst.setDouble(i++, p.getIva());
			pst.setDouble(i++, p.getTotal());
			pst.setDate(i++, new java.sql.Date(p.getFecha_pedido().getTime()));
		
			logger.debug(sql);
			
			int insertRow=pst.executeUpdate();
			
			if(insertRow == 0) {
				throw new SQLException(" No se pudo insertar");
			}
			
			return p;
		}catch (SQLException ex) {
			logger.error(ex.getMessage(),ex);
			throw new DataException(ex);
		}finally{
			JDBCUtils.closeResultSet(rs);
			JDBCUtils.closeStatement(pst);
		}
	}

	
	@Override
	public void delete(Connection conexion,Integer idPedido) throws DataException {
		
		if(logger.isDebugEnabled()) {
			logger.debug("Id = "+idPedido);
		}
		
		PreparedStatement preparedStatement = null;

		try {
			String queryString =	
					  "DELETE FROM pedido " 
					+ "WHERE  id_pedido = ? ";
			
			preparedStatement = conexion.prepareStatement(queryString);

			int i = 1;
			preparedStatement.setInt(i++, idPedido);
			
			logger.debug(queryString);
			
			int removedRows = preparedStatement.executeUpdate();

			if (removedRows == 0) {
				throw new InstanceNotFoundException(idPedido,"No se elimino el pedido correctamente");
			} 


		} catch (SQLException e) {
			logger.error(e.getMessage(),e);
			throw new DataException(e);
		} finally {
			JDBCUtils.closeStatement(preparedStatement);
		}
		
	}

	public Pedido loadNext(ResultSet rs) 
			throws SQLException,DataException{
				int i=1;
				Integer idPedido=rs.getInt(i++);
				String email  = rs.getString(i++);
				Date fechaPedido=rs.getDate(i++);
				Double iva=rs.getDouble(i++);
				Double total=rs.getDouble(i++);
				
				Pedido p= new Pedido();
				
				p.setIdPedido(idPedido);
				p.setEmail(email);
				p.setFecha_pedido(fechaPedido);
				iva=p.getIva();
				p.setTotal(total);
				
				return p;
				
			
		}
		
}
