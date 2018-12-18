package com.eddie.training.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.eddie.training.dao.FormatoDAO;
import com.eddie.training.dao.Utils.ConnectionManager;
import com.eddie.training.dao.Utils.JDBCUtils;
import com.eddie.training.exceptions.DataException;
import com.eddie.training.model.Formato;

public class FormatoDAOImpl implements FormatoDAO{
	
	public FormatoDAOImpl() {}
	
	public Formato findbyID(Integer id)
		throws Exception{
			Formato f=null;
			Connection connection=null;
			PreparedStatement pst=null;
			ResultSet rs=null;
		try {
			connection=ConnectionManager.getConnection();
			String sql;
			sql="select id_formato, nombre from formato where id_formato= ?";
			
			pst=connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			
			int i=1;
			pst.setLong(i++, id);
			
			rs=pst.executeQuery();
			
			
			if(rs.next()){
				f=LoadNext(rs);
				
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
		
		return f;
	}
	
	public Formato create(Formato f) 
	throws Exception{
		Connection conn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			conn=ConnectionManager.getConnection();
			String sql="Insert Into id_formato,nombre values (?,?)";
			pst=conn.prepareStatement(sql);
			int i=1;
			pst.setInt(i++, f.getIdFormato());
			pst.setString(i++, f.getNombre());
			
			int insertRow=pst.executeUpdate();
			
			if(insertRow == 0) {
				throw new SQLException(" No se pudo insertar");
			}
			
			rs=pst.getGeneratedKeys();
			if(rs.next()) {
				Integer idFormato=rs.getInt(i++);
				String nombre=rs.getString(i++);
				f.setIdFormato(idFormato);
				f.setNombre(nombre);
			}else {
				throw new DataException("Problemas al autogenerar primary key");
			}
		
			return f;
		}catch (SQLException ex) {
			throw new DataException(ex);
		}finally{
			JDBCUtils.closeConnection(conn);
			JDBCUtils.closeResultSet(rs);
			JDBCUtils.closeStatement(pst);
		}
		}
	
	public boolean update(Formato f) {
		return false;
		}
		

	public void delete(Formato f) {}
	
	
	public Formato LoadNext(ResultSet rs)throws Exception{
		int i=1;
		int idFormato  = rs.getInt(i++);
		String nombre = rs.getString(i++);

		Formato f=new Formato();
		f.setIdFormato(idFormato);
		f.setNombre(nombre);
		
		return f;

	}
}
