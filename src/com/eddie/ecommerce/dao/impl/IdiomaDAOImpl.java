package com.eddie.ecommerce.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.eddie.ecommerce.dao.IdiomaDAO;
import com.eddie.ecommerce.dao.Utils.ConnectionManager;
import com.eddie.ecommerce.dao.Utils.JDBCUtils;
import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.exceptions.InstanceNotFoundException;
import com.eddie.ecommerce.model.Idioma;

public class IdiomaDAOImpl implements IdiomaDAO{

	@Override
	public Idioma findById(Connection conexion, String id, String idioma) throws InstanceNotFoundException, DataException {
		Idioma idi=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
	try {
		conexion=ConnectionManager.getConnection();
		String sql;
		sql="select id_idioma, nombre from idioma_idiomaweb where id_idioma= ? and id_idioma_web like '"+idioma+"'";
		
		pst=conexion.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		
		int i=1;
		pst.setString(i++, id);
		
		rs=pst.executeQuery();
		
		
		while(rs.next()){
			idi=loadNext(rs);
			
		}
		return idi;
	}catch (SQLException ex) {
		throw new DataException(ex);
	}finally{
		JDBCUtils.closeConnection(conexion);
		JDBCUtils.closeResultSet(rs);
		JDBCUtils.closeStatement(pst);
	}
	}

	@Override
	public List<Idioma> findAll(Connection conexion, String idioma) throws DataException {
		Idioma idi=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			conexion=ConnectionManager.getConnection();
			String sql;
			sql="select i.id_idioma, i.nombre from idioma_idiomaweb i where id_idioma_web like ?";

			pst=conexion.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			
			int i=1;
			//pst.setString(i++,"%"+nombrejuego.toUpperCase()+"%");
			pst.setString(i++, idioma);	
			rs=pst.executeQuery();
			
			List<Idioma> idiomas=new ArrayList<Idioma>();
			while(rs.next()){
				idi=loadNext(rs);
				idiomas.add(idi);
			}
			return idiomas;
		}catch (SQLException ex) {
			throw new DataException(ex);
		}finally{
			JDBCUtils.closeConnection(conexion);
			JDBCUtils.closeResultSet(rs);
			JDBCUtils.closeStatement(pst);
		}
	}
	
	public Idioma loadNext(ResultSet rs)throws DataException,SQLException{
		int i=1;
		String idIdioma = rs.getString(i++);
		String nombre = rs.getString(i++);

		Idioma id=new Idioma();
		id.setIdIdioma(idIdioma);
		id.setNombre(nombre);
		
		return id;

	}
}
