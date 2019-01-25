package com.eddie.ecommerce.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.eddie.ecommerce.dao.PlataformaDAO;
import com.eddie.ecommerce.dao.Utils.ConnectionManager;
import com.eddie.ecommerce.dao.Utils.JDBCUtils;
import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.exceptions.InstanceNotFoundException;
import com.eddie.ecommerce.model.Plataforma;

public class PlataformaDAOImpl implements PlataformaDAO{

	@Override
	public Plataforma findbyIdPlataforma(Connection conexion, Integer id) throws InstanceNotFoundException, DataException {
		Plataforma p=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
	try {
		conexion=ConnectionManager.getConnection();
		String sql;
		sql="select id_plataforma, nombre from plataforma where id_plataforma= ? ";
		
		pst=conexion.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		
		int i=1;
		pst.setInt(i++, id);
		
		rs=pst.executeQuery();
		
		
		while(rs.next()){
			p=loadNext(rs);
			
		}
		return p;
	}catch (SQLException ex) {
		throw new DataException(ex);
	}finally{
		JDBCUtils.closeConnection(conexion);
		JDBCUtils.closeResultSet(rs);
		JDBCUtils.closeStatement(pst);
	}
	}

	@Override
	public List<Plataforma> findAll(Connection conexion) throws DataException {
		Plataforma p=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			conexion=ConnectionManager.getConnection();
			String sql;
			sql="select id_plataforma, nombre from plataforma";

			pst=conexion.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			
			rs=pst.executeQuery();
			
			List<Plataforma> resultado=new ArrayList<Plataforma>();
			while(rs.next()){
				p=loadNext(rs);
				resultado.add(p);
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
	
	
	public Plataforma loadNext(ResultSet rs) 
			throws DataException,SQLException{
				int i=1;
				Integer idPlataforma  = rs.getInt(i++);
				String nombre = rs.getString(i++);
				
				
				Plataforma p= new Plataforma();
				p.setIdPlatadorma(idPlataforma);
				p.setNombre(nombre);
				
				
				return p;
			
		}
}
