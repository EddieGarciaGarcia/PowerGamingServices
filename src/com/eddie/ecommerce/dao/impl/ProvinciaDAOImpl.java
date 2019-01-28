package com.eddie.ecommerce.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.eddie.ecommerce.dao.ProvinciaDAO;
import com.eddie.ecommerce.dao.Utils.ConnectionManager;
import com.eddie.ecommerce.dao.Utils.JDBCUtils;
import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.exceptions.InstanceNotFoundException;
import com.eddie.ecommerce.model.Provincia;

public class ProvinciaDAOImpl implements ProvinciaDAO{
	@Override
	public Provincia findById(Connection conexion, Integer id) throws InstanceNotFoundException,DataException {
		Provincia p=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
	try {
		conexion=ConnectionManager.getConnection();
		String sql;
		sql="select id_provincia, id_pais, nombre from provincia where id_provincia= ? ";
		
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
	public List<Provincia> findAllByIdPais(Connection conexion, Integer idPais) throws DataException {
		Provincia p=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
	try {
		conexion=ConnectionManager.getConnection();
		String sql;
		sql="select id_provincia, id_pais, nombre from provincia where id_pais= ? ";
		
		pst=conexion.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		
		int i=1;
		pst.setInt(i++, idPais);
		
		rs=pst.executeQuery();
		
		List<Provincia> provincias=new ArrayList<Provincia>();
		while(rs.next()){
			p=loadNext(rs);
			provincias.add(p);
		}
		return provincias;
	}catch (SQLException ex) {
		throw new DataException(ex);
	}finally{
		JDBCUtils.closeConnection(conexion);
		JDBCUtils.closeResultSet(rs);
		JDBCUtils.closeStatement(pst);
	}
	}

	@Override
	public List<Provincia> findAll(Connection conexion) throws DataException {
		Provincia p=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			conexion=ConnectionManager.getConnection();
			String sql;
			sql="select id_provincia, id_pais, nombre from provincia";

			pst=conexion.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			
			rs=pst.executeQuery();
			
			List<Provincia> provincias=new ArrayList<Provincia>();
			while(rs.next()){
				p=loadNext(rs);
				provincias.add(p);
			}
			return provincias;
		}catch (SQLException ex) {
			throw new DataException(ex);
		}finally{
			JDBCUtils.closeConnection(conexion);
			JDBCUtils.closeResultSet(rs);
			JDBCUtils.closeStatement(pst);
		}
	}
	public Provincia loadNext(ResultSet rs) 
			throws DataException, SQLException{
				int i=1;
				Integer idProvincia  = rs.getInt(i++);
				Integer idPais=rs.getInt(i++);
				String nombre = rs.getString(i++);
				
				
				Provincia p= new Provincia();
				p.setIdProvincia(idProvincia);
				p.setIdPais(idPais);
				p.setNombre(nombre);
				
				
				return p;
			
		}
}
