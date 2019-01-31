package com.eddie.ecommerce.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.eddie.ecommerce.dao.EdicionDAO;
import com.eddie.ecommerce.dao.Utils.ConnectionManager;
import com.eddie.ecommerce.dao.Utils.JDBCUtils;
import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.exceptions.DuplicateInstanceException;
import com.eddie.ecommerce.exceptions.InstanceNotFoundException;
import com.eddie.ecommerce.model.Categoria;
import com.eddie.ecommerce.model.Edicion;
import com.eddie.ecommerce.model.Juego;

public class EdicionDAOImpl implements EdicionDAO{

	@Override
	public Edicion findByIdEdicion(Connection conexion,Integer id) throws InstanceNotFoundException,DataException {
		Edicion e=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			conexion=ConnectionManager.getConnection();
			String sql;
			sql="select id_edicion,id_juego,id_formato,id_tipo_edicion,precio from edicion where id_edicion = ?";

			pst=conexion.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			
			int i=1;
			pst.setInt(i++, id);	
			rs=pst.executeQuery();
			
			
			if(rs.next()){
				e=loadNext(rs);
				
			}else {
				throw new InstanceNotFoundException("Error "+id+" id introducido incorrecto", Edicion.class.getName());
			}
			return e;
		}catch (SQLException ex) {
			System.out.println("Hemos detectado problemas. Por favor compruebe los datos");
			throw new DataException(ex);
		}finally{
			JDBCUtils.closeResultSet(rs);
			JDBCUtils.closeStatement(pst);
		}
	}
	
	@Override
	public List<Edicion> findByIdJuego(Connection conexion, Integer id) throws DataException {
		Edicion e=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			conexion=ConnectionManager.getConnection();
			String sql;
			sql="select id_edicion,id_juego,id_formato,id_tipo_edicion,precio from edicion where id_juego = ?";

			pst=conexion.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			
			int i=1;
			pst.setInt(i++, id);	
			rs=pst.executeQuery();
			
			List<Edicion> resultado=new ArrayList<Edicion>();
			while(rs.next()){
				e=loadNext(rs);
				resultado.add(e);
			}
			return resultado;
		}catch (SQLException ex) {
			System.out.println("Hemos detectado problemas. Por favor compruebe los datos");
			throw new DataException(ex);
		}finally{
			JDBCUtils.closeResultSet(rs);
			JDBCUtils.closeStatement(pst);
		}
	}
	
	@Override
	public Edicion create(Connection conexion,Edicion e) throws DuplicateInstanceException, DataException {
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			conexion=ConnectionManager.getConnection();
			String sql;
			sql="Insert Into edicion(id_juego,id_formato,id_tipo_edicion,precio) "
					+ "values (?,?,?,?)";
			
			pst=conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			int i=1;
			
			pst.setInt(i++,e.getIdJuego());
			pst.setInt(i++, e.getIdFormato());
			pst.setInt(i++, e.getIdTipoEdicion());
			pst.setDouble(i++, e.getPrecio());


			
			int insertRow=pst.executeUpdate();
			
			if(insertRow == 0) {
				throw new SQLException(" No se pudo insertar");
			}
			rs=pst.getGeneratedKeys();
			if(rs.next()) {
				Integer idEdicion=rs.getInt(1);
				e.setId(idEdicion);
			}else {
				throw new DataException("Problemas al autogenerar primary key");
			}
			return e;
		}catch (SQLException ex) {
			System.out.println("Hemos detectado problemas. Por favor compruebe los datos");
			throw new DataException(ex);
		}finally{
			JDBCUtils.closeResultSet(rs);
			JDBCUtils.closeStatement(pst);
		}
	}

	@Override
	public boolean update(Connection conexion,Edicion e) throws InstanceNotFoundException, DataException {
		PreparedStatement preparedStatement = null;
		conexion=null;
		StringBuilder sqlupdate;
		try {	
			conexion=ConnectionManager.getConnection();
			sqlupdate = new StringBuilder(" UPDATE edicion");
			
			boolean first = true;
			
			if (e.getIdJuego()!=null) {
				addUpdate(sqlupdate,first," id_juego = ?");
				first=false;
			}
			
			if (e.getIdFormato()!=null) {
				addUpdate(sqlupdate,first," id_formato = ?");
				first=false;
			}
			
			if (e.getIdTipoEdicion()!=null) {
				addUpdate(sqlupdate,first," id_tipo_edicion = ?");
				first=false;
			}
			
			if (e.getPrecio()!=null) {
				addUpdate(sqlupdate,first," precio = ?");
				first=false;
			}
					

			sqlupdate.append("WHERE email = ?");
			
			preparedStatement = conexion.prepareStatement(sqlupdate.toString());
			

			int i = 1;
			if (e.getIdJuego()!=null) 
				preparedStatement.setInt(i++,e.getIdJuego());
			
			if (e.getIdFormato()!=null) 
				preparedStatement.setInt(i++,e.getIdFormato());
			if (e.getIdTipoEdicion()!=null) 
				preparedStatement.setInt(i++,e.getIdTipoEdicion());
			if (e.getPrecio()!=null) 
				preparedStatement.setDouble(i++,e.getPrecio());
			
			
			preparedStatement.setInt(i++, e.getId());

			int updatedRows = preparedStatement.executeUpdate();

			if (updatedRows > 1) {
				throw new SQLException();
			}     
			return true;
		} catch (SQLException se) {
			System.out.println("Hemos detectado problemas. Por favor compruebe los datos");
			throw new DataException(se);    
		} finally {
			JDBCUtils.closeStatement(preparedStatement);
		}              		
	
	}

	public Edicion loadNext(ResultSet rs)throws DataException,SQLException{
		int i=1;
		int idEdicion  = rs.getInt(i++);
		int idJuego =rs.getInt(i++);
		int idFormato=rs.getInt(i++);
		int idTipoEdicion=rs.getInt(i++);
		double precio = rs.getDouble(i++);

		Edicion e=new Edicion();
		e.setId(idEdicion);
		e.setIdJuego(idJuego);
		e.setIdFormato(idFormato);
		e.setIdTipoEdicion(idTipoEdicion);
		e.setPrecio(precio);
		
		return e;

	}

	private void addClause(StringBuilder queryString, boolean first, String clause) {
		queryString.append(first? "WHERE ": " AND ").append(clause);
	}
	
	private void addUpdate(StringBuilder queryString, boolean first, String clause) {
		queryString.append(first? " SET ": " , ").append(clause);
	}

	
}
