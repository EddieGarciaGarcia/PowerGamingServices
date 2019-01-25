package com.eddie.ecommerce.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.eddie.ecommerce.dao.FormatoDAO;
import com.eddie.ecommerce.dao.Utils.ConnectionManager;
import com.eddie.ecommerce.dao.Utils.JDBCUtils;
import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.exceptions.InstanceNotFoundException;
import com.eddie.ecommerce.model.Formato;

public class FormatoDAOImpl implements FormatoDAO{
	
	public FormatoDAOImpl() {}
	
	public Formato findbyIdFormato(Connection conexion,Integer id, String idioma)
		throws InstanceNotFoundException,DataException{
			Formato f=null;
			PreparedStatement pst=null;
			ResultSet rs=null;
		try {
			conexion=ConnectionManager.getConnection();
			String sql;
			sql="select id_formato, nombre from idiomaweb_formato where id_formato= ? and id_idioma_web like '"+idioma+"'";
			
			pst=conexion.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			
			int i=1;
			pst.setInt(i++, id);
			
			rs=pst.executeQuery();
			
			
			while(rs.next()){
				f=loadNext(rs);
				
			}
			return f;
		}catch (SQLException ex) {
			throw new DataException(ex);
		}finally{
			JDBCUtils.closeConnection(conexion);
			JDBCUtils.closeResultSet(rs);
			JDBCUtils.closeStatement(pst);
		}
		
		
	}
	
	public List<Formato> findAll(Connection conexion, String idioma) throws DataException{
		Formato f=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			conexion=ConnectionManager.getConnection();
			String sql;
			sql="select f.id_formato, f.nombre from idiomaweb_formato f where id_idioma_web like ?";

			pst=conexion.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			
			int i=1;
			//pst.setString(i++,"%"+nombrejuego.toUpperCase()+"%");
			pst.setString(i++, idioma);	
			rs=pst.executeQuery();
			
			List<Formato> resultado=new ArrayList<Formato>();
			while(rs.next()){
				f=loadNext(rs);
				resultado.add(f);
			}
			return resultado;
		}catch (SQLException ex) {
			throw new DataException(ex);
		}finally{
			JDBCUtils.closeConnection(conexion);
			JDBCUtils.closeResultSet(rs);
			JDBCUtils.closeStatement(pst);
		}
		
	}
	
	public Formato loadNext(ResultSet rs)throws DataException,SQLException{
		int i=1;
		int idFormato  = rs.getInt(i++);
		String nombre = rs.getString(i++);

		Formato f=new Formato();
		f.setIdFormato(idFormato);
		f.setNombre(nombre);
		
		return f;

	}
}
