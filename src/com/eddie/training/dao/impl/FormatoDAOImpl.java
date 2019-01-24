package com.eddie.training.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.eddie.training.dao.Utils.ConnectionManager;
import com.eddie.training.dao.Utils.JDBCUtils;
import com.eddie.training.model.Formato;

public class FormatoDAOImpl {
	
	public FormatoDAOImpl() {}
	
	public Formato findbyIdFormato(Connection conexion,Integer id)
		throws Exception{
			Formato f=null;
			PreparedStatement pst=null;
			ResultSet rs=null;
		try {
			conexion=ConnectionManager.getConnection();
			String sql;
			sql="select id_formato, nombre from formato where id_formato= ?";
			
			pst=conexion.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			
			int i=1;
			pst.setInt(i++, id);
			
			rs=pst.executeQuery();
			
			
			while(rs.next()){
				f=LoadNext(rs);
				
			}
			return f;
		}catch (SQLException ex) {
			throw new Exception(ex);
		}finally{
			JDBCUtils.closeConnection(conexion);
			JDBCUtils.closeResultSet(rs);
			JDBCUtils.closeStatement(pst);
		}
		
		
	}
	
	public List<Formato> findAll(Connection conexion, String idioma) throws Exception{
		Formato f=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			conexion=ConnectionManager.getConnection();
			String sql;
			//sql="select f.id_formato, f.nombre from idiomaweb_formato f where id_idioma_web like ?";
			sql="select id_formato, nombre from formato";
			pst=conexion.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			
			int i=1;
			//pst.setString(i++,"%"+nombrejuego.toUpperCase()+"%");
			pst.setString(i++, idioma);	
			rs=pst.executeQuery();
			
			List<Formato> resultado=new ArrayList<Formato>();
			while(rs.next()){
				f=LoadNext(rs);
				resultado.add(f);
			}
			return resultado;
		}catch (SQLException ex) {
			throw new Exception(ex);
		}finally{
			JDBCUtils.closeConnection(conexion);
			JDBCUtils.closeResultSet(rs);
			JDBCUtils.closeStatement(pst);
		}
		
	}
	
	
	
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
