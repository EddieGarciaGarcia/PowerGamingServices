package com.eddie.ecommerce.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.eddie.ecommerce.dao.ItemBibliotecaDAO;
import com.eddie.ecommerce.dao.Utils.ConnectionManager;
import com.eddie.ecommerce.dao.Utils.JDBCUtils;
import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.exceptions.DuplicateInstanceException;
import com.eddie.ecommerce.exceptions.InstanceNotFoundException;
import com.eddie.ecommerce.model.ItemBiblioteca;


public class ItemBibliotecaDAOImpl implements ItemBibliotecaDAO{

	@Override
	public List<ItemBiblioteca> findByUsuario(Connection connection, String email) throws DataException {
		ItemBiblioteca ib=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			connection=ConnectionManager.getConnection();
			String sql;
			sql="select email,id_juego,puntuacion,comprado,comentario,fecha_comentario from usuarios_juego where email=?";
			
			pst=connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			
			int i=1;
			//pst.setString(i++,"%"+nombrejuego.toUpperCase()+"%");
			pst.setString(i++, email);	
			rs=pst.executeQuery();
			
			List<ItemBiblioteca> biblioteca = new ArrayList<ItemBiblioteca>();
			while(rs.next()){
				ib=loadNext(rs);
				biblioteca.add(ib);
			}
			return biblioteca;
		}catch (SQLException ex) {
			throw new DataException(ex);
		}finally{
			JDBCUtils.closeConnection(connection);
			JDBCUtils.closeResultSet(rs);
			JDBCUtils.closeStatement(pst);
		}
	}

	@Override
	public List<ItemBiblioteca> findByJuego(Connection connection, Integer idJuego) throws DataException {
		ItemBiblioteca ib=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			connection=ConnectionManager.getConnection();
			String sql;
			sql="select email,id_juego,puntuacion,comprado,comentario,fecha_comentario from usuarios_juego where id_juego=?";
			
			pst=connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			
			int i=1;
			//pst.setString(i++,"%"+nombrejuego.toUpperCase()+"%");
			pst.setInt(i++, idJuego);	
			rs=pst.executeQuery();
			
			List<ItemBiblioteca> biblioteca = new ArrayList<ItemBiblioteca>();
			while(rs.next()){
				ib=loadNext(rs);
				biblioteca.add(ib);
			}
			return biblioteca;
		}catch (SQLException ex) {
			throw new DataException(ex);
		}finally{
			JDBCUtils.closeConnection(connection);
			JDBCUtils.closeResultSet(rs);
			JDBCUtils.closeStatement(pst);
		}
	}

	@Override
	public ItemBiblioteca create(Connection connection, ItemBiblioteca b)
			throws DuplicateInstanceException, DataException {
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			connection=ConnectionManager.getConnection();
			String sql;
			sql="Insert Into usuarios_juego(email,id_juego,puntuacion,comprado,comentario,fecha_comentario) "
					+ "values (?,?,?,?,?,?)";
			
			pst=connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			int i=1;
			if(b.getEmail()==null || b.getEmail().equals("")) {
				System.out.println("fallo email null o vacio");
			}else {
				pst.setString(i++,b.getEmail());
			}
			
			pst.setInt(i++, b.getIdJuego());
			
			pst.setInt(i++,b.getPuntuacion());
			pst.setString(i++, b.getComprado());
			if(b.getComentario()==null) {
				pst.setNull(i++, Types.NULL);
			}else {
				pst.setString(i++,b.getComentario());
			}
			
			if(b.getFechaComentario()==null) {
				pst.setNull(i++, Types.NULL);
			}else {
				pst.setDate(i++, (java.sql.Date) b.getFechaComentario());
			}
			
			
			int insertRow=pst.executeUpdate();
			
			if(insertRow == 0) {
				throw new SQLException(" No se pudo insertar");
			}
			
			return b;
		}catch (SQLException ex) {
			throw new DataException(ex);
		}finally{
			JDBCUtils.closeConnection(connection);
			JDBCUtils.closeResultSet(rs);
			JDBCUtils.closeStatement(pst);
		}
	}
	
	@Override
	public long delete(Connection connection,String email, Integer idJuego)
			throws InstanceNotFoundException, DataException {
		PreparedStatement preparedStatement = null;

		try {
			String queryString =	
					  "DELETE FROM usuarios_juego " 
					+ "WHERE email like ? and id_juego = ? ";
			
			preparedStatement = connection.prepareStatement(queryString);

			int i = 1;
			preparedStatement.setString(i++, email);
			preparedStatement.setInt(i++, idJuego);
			

			int removedRows = preparedStatement.executeUpdate();

			if (removedRows == 0) {
				throw new InstanceNotFoundException(idJuego,"No se elimino el juego correctamente");
			} 
			

			return removedRows;

		} catch (SQLException e) {
			throw new DataException(e);
		} finally {
			JDBCUtils.closeStatement(preparedStatement);
		}
	}
	
	@Override
	public ItemBiblioteca update(Connection connection, ItemBiblioteca b) throws DuplicateInstanceException, DataException {
		PreparedStatement preparedStatement = null;
		connection=null;
		StringBuilder sqlupdate;
		try {	
			connection=ConnectionManager.getConnection();
			sqlupdate = new StringBuilder(" UPDATE usuarios_juego");
			
			boolean first = true;
			
			if (b.getPuntuacion()!=null) {
				addUpdate(sqlupdate,first," puntuacion = ?");
				first=false;
			}
			
			if (b.getComprado()!=null) {
				addUpdate(sqlupdate,first," comprado = ?");
				first=false;
			}
			
			if (b.getComentario()==null) {
				addUpdate(sqlupdate,first," comentario = ?");
				first=false;
			}
			
			if (b.getFechaComentario()==null) {
				addUpdate(sqlupdate,first," fecha_comentario = ?");
				first=false;
			}
			
			sqlupdate.append("WHERE email like ? and id_juego = ?");
			
			preparedStatement = connection.prepareStatement(sqlupdate.toString());
			

			int i = 1;
			if (b.getPuntuacion()!=null) 
				preparedStatement.setInt(i++,b.getPuntuacion());
			
			if (b.getComprado()!=null) 
				preparedStatement.setString(i++,b.getComprado());
			if (b.getComentario()!=null) 
				preparedStatement.setString(i++,b.getComentario());
			if (b.getFechaComentario()!=null) 
				preparedStatement.setDate(i++,(java.sql.Date)b.getFechaComentario());
		
			preparedStatement.setString(i++, b.getEmail());
			preparedStatement.setInt(i++, b.getIdJuego());

			int updatedRows = preparedStatement.executeUpdate();

			if (updatedRows > 1) {
				throw new SQLException();
			}     
		} catch (SQLException e) {
			throw new DataException(e);    
		} finally {
			JDBCUtils.closeStatement(preparedStatement);
		}
		return b;              		
	}
	
	public ItemBiblioteca loadNext(ResultSet rs) 
			throws SQLException,DataException{
				int i=1;
				String email  = rs.getString(i++);
				Integer idJuego = rs.getInt(i++);
				Integer puntuacion=rs.getInt(i++);
				String comprado=rs.getString(i++);
				String comentario=rs.getString(i++);
				Date fechaComentario=rs.getDate(i++);
			
				ItemBiblioteca ib= new ItemBiblioteca();
				
				ib.setEmail(email);
				ib.setIdJuego(idJuego);
				ib.setPuntuacion(puntuacion);
				ib.setComentario(comentario);
				ib.setFechaComentario(fechaComentario);
				ib.setComprado(comprado);
				
				return ib;
	}


		private void addUpdate(StringBuilder queryString, boolean first, String clause) {
			queryString.append(first? " SET ": " , ").append(clause);
		}

		
}
