package com.eddie.ecommerce.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.eddie.ecommerce.dao.LineaPedidoDAO;
import com.eddie.ecommerce.dao.Utils.ConnectionManager;
import com.eddie.ecommerce.dao.Utils.JDBCUtils;
import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.exceptions.DuplicateInstanceException;
import com.eddie.ecommerce.exceptions.InstanceNotFoundException;
import com.eddie.ecommerce.model.Categoria;
import com.eddie.ecommerce.model.LineaPedido;

public class LineaPedidoDAOImpl implements LineaPedidoDAO{

	@Override
	public List<LineaPedido> findByPedido(Connection conexion,Integer idPedido) throws DataException {
		LineaPedido lp=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			conexion=ConnectionManager.getConnection();
			String sql;
			sql="select numero_linea,id_edicion,id_pedido,cantidad,precio from lineapedido where id_pedido=?";
			
			pst=conexion.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			
			int i=1;
			//pst.setString(i++,"%"+nombrejuego.toUpperCase()+"%");
			pst.setInt(i++, idPedido);	
			rs=pst.executeQuery();
			
			List<LineaPedido> lineas = new ArrayList<LineaPedido>();
			while(rs.next()){
				lp=loadNext(rs);
				lineas.add(lp);
			}
			return lineas;
		}catch (SQLException ex) {
			System.out.println("Hemos detectado problemas. Por favor compruebe los datos");
			throw new DataException(ex);
		}finally{
			JDBCUtils.closeConnection(conexion);
			JDBCUtils.closeResultSet(rs);
			JDBCUtils.closeStatement(pst);
		}
	}

	@Override
	public LineaPedido findById(Connection conexion,Integer numeroLinea) throws InstanceNotFoundException, DataException {
		LineaPedido lp=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			conexion=ConnectionManager.getConnection();
			String sql;
			sql="select numero_linea,id_edicion,id_pedido,cantidad,precio from lineapedido where numero_linea=?";
			
			pst=conexion.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			
			int i=1;
			//pst.setString(i++,"%"+nombrejuego.toUpperCase()+"%");
			pst.setInt(i++, numeroLinea);	
			rs=pst.executeQuery();
			
			
			if(rs.next()){
				lp=loadNext(rs);
			}else {
				throw new InstanceNotFoundException("Error "+numeroLinea+" id introducido incorrecto", LineaPedido.class.getName());
			}
			return lp;
		}catch (SQLException ex) {
			System.out.println("Hemos detectado problemas. Por favor compruebe los datos");
			throw new DataException(ex);
		}finally{
			JDBCUtils.closeConnection(conexion);
			JDBCUtils.closeResultSet(rs);
			JDBCUtils.closeStatement(pst);
		}
	}

	@Override
	public LineaPedido create(Connection conexion,LineaPedido lp) throws DuplicateInstanceException, DataException {
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			conexion=ConnectionManager.getConnection();
			String sql;
			sql="Insert Into usuarios_juego(id_edicion,id_pedido,cantidad,precio) "
					+ "values (?,?,?,?)";
			
			pst=conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			int i=1;
			
			pst.setInt(i++,lp.getIdEdicion());
			pst.setInt(i++,lp.getPedido());
			pst.setInt(i++,lp.getCantidad());
			pst.setDouble(i++, lp.getPrecio());
			
			int insertRow=pst.executeUpdate();
			
			if(insertRow == 0) {
				throw new SQLException(" No se pudo insertar");
			}
			
			return lp;
		}catch (SQLException ex) {
			System.out.println("Hemos detectado problemas. Por favor compruebe los datos");
			throw new DataException(ex);
		}finally{
			JDBCUtils.closeConnection(conexion);
			JDBCUtils.closeResultSet(rs);
			JDBCUtils.closeStatement(pst);
		}
	}

	@Override
	public long delete(Connection conexion,Integer id) throws DataException {
		PreparedStatement preparedStatement = null;

		try {
			String queryString =	
					  "DELETE FROM lineapedido " 
					+ "WHERE  numero_linea = ? ";
			
			preparedStatement = conexion.prepareStatement(queryString);

			int i = 1;
			preparedStatement.setInt(i++, id);
			

			int removedRows = preparedStatement.executeUpdate();

			if (removedRows == 0) {
				throw new InstanceNotFoundException(id,"No se elimino el pedido correctamente");
			} 

			return removedRows;
		} catch (SQLException e) {
			System.out.println("Hemos detectado problemas. Por favor compruebe los datos");
			throw new DataException(e);
		} finally {
			JDBCUtils.closeStatement(preparedStatement);
		}
		
	}
	
	public LineaPedido loadNext(ResultSet rs) 
			throws SQLException,DataException{
				int i=1;
				Integer numeroLinea=rs.getInt(i++);
				Integer idEdicion  = rs.getInt(i++);
				Integer idPedido=rs.getInt(i++);
				Integer cantidad=rs.getInt(i++);
				Double precio=rs.getDouble(i++);
				
				LineaPedido lp= new LineaPedido();
				
				lp.setNumeroLinea(numeroLinea);
				lp.setIdEdicion(idEdicion);
				lp.setPedido(idPedido);
				lp.setCantidad(cantidad);
				lp.setPrecio(precio);
				
				return lp;
				
			
		}
		
		
		private void addUpdate(StringBuilder queryString, boolean first, String clause) {
			queryString.append(first? " SET ": " , ").append(clause);
		}
}