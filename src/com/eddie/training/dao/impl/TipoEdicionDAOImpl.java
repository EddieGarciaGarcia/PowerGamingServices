package com.eddie.training.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.eddie.training.dao.TipoEdicionDAO;
import com.eddie.training.dao.Utils.ConnectionManager;
import com.eddie.training.dao.Utils.JDBCUtils;
import com.eddie.training.exceptions.DataException;
import com.eddie.training.model.TipoEdicion;

public class TipoEdicionDAOImpl implements TipoEdicionDAO{

	public TipoEdicionDAOImpl() {}
	
	public TipoEdicion findbyIdTipoEdicion(Integer id)
		throws Exception{
			TipoEdicion te=null;
			Connection connection=null;
			PreparedStatement pst=null;
			ResultSet rs=null;
		try {
			connection=ConnectionManager.getConnection();
			String sql;
			sql="select id_tipoedicion, nombre from tipoedicion where id_tipoedicion= ?";
			
			pst=connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			
			int i=1;
			pst.setLong(i++, id);
			
			rs=pst.executeQuery();
			
			
			if(rs.next()){
				te=LoadNext(rs);
				
			}else {
				throw new Exception("Non se encontrou o empleado "+id);
			}
			if (rs.next()) {
				throw new Exception("Empleado "+id+" duplicado");
			}
			
		}catch (SQLException ex) {
			throw new DataException(ex);
		}finally{
			JDBCUtils.closeConnection(connection);
			JDBCUtils.closeResultSet(rs);
			JDBCUtils.closeStatement(pst);
		}
		
		return te;
	}
	
	public TipoEdicion create(TipoEdicion te) 
	throws Exception{
		Connection conn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			conn=ConnectionManager.getConnection();
			String sql="Insert Into id_formato,nombre values (?,?)";
			pst=conn.prepareStatement(sql);
			int i=1;
			pst.setInt(i++, te.getIdTipoEdicion());
			pst.setString(i++, te.getNombre());
			
			int insertRow=pst.executeUpdate();
			
			if(insertRow == 0) {
				throw new SQLException(" No se pudo insertar");
			}
			
			rs=pst.getGeneratedKeys();
			if(rs.next()) {
				Integer idTipoEdicionDAO=rs.getInt(i++);
				String nombre=rs.getString(i++);
				te.setIdTipoEdicion(idTipoEdicionDAO);
				te.setNombre(nombre);
			}else {
				throw new DataException("Problemas al autogenerar primary key");
			}
		
			return te;
		}catch (SQLException ex) {
			throw new DataException(ex);
		}finally{
			JDBCUtils.closeConnection(conn);
			JDBCUtils.closeResultSet(rs);
			JDBCUtils.closeStatement(pst);
		}
		}
	

	public boolean update(TipoEdicion te) {
		return false;
		}
		

	public void delete(TipoEdicion te) {}
	
	
	public TipoEdicion LoadNext(ResultSet rs)throws Exception{
		int i=1;
		int idTipoEdicion  = rs.getInt(i++);
		String nombre = rs.getString(i++);

		TipoEdicion te=new TipoEdicion();
		te.setIdTipoEdicion(idTipoEdicion);
		te.setNombre(nombre);
		
		return te;

	}

	
}
