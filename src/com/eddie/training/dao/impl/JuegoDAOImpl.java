package com.eddie.training.dao.impl;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.eddie.training.dao.JuegoDAO;
import com.eddie.training.dao.Utils.ConnectionManager;
import com.eddie.training.dao.Utils.JDBCUtils;
import com.eddie.training.exceptions.DataException;
import com.eddie.training.model.Edicion;
import com.eddie.training.model.Juego;

public  class JuegoDAOImpl implements JuegoDAO {
	
	private EdicionDAOImpl edicionDAO=null;
	
	public JuegoDAOImpl() {
		edicionDAO= new EdicionDAOImpl();
	}
	
	@Override
	public List<Juego> findByJuegoCriteria() throws Exception {
		
		
		
		
		
		
		return null;
	}

	
	public List<Juego> findAll(Integer id, java.sql.Date fecha) 
		throws Exception{
			Juego j=null;
			Connection connection=null;
			PreparedStatement pst=null;
			ResultSet rs=null;
		try {
			connection=ConnectionManager.getConnection();
			String sql;
			sql="select nombre from juego where fecha_lanzamiento =(select max(?) from juego);";
			
			pst=connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			
			int i=1;
			pst.setDate(i++, fecha);
			
			rs=pst.executeQuery();
			
			List <Juego> juegos=new ArrayList<Juego>();
			if(rs.next()){
				j=loadNext(rs);
				juegos.add(j);
				
			}else {
				throw new Exception("Non se encontrou o empleado "+id);
			}
			if (rs.next()) {
				throw new Exception("Empleado "+id+" duplicado");
			}
			return juegos;
		}catch (SQLException ex) {
			throw new DataException(ex);
		}finally{
			JDBCUtils.closeConnection(connection);
			JDBCUtils.closeResultSet(rs);
			JDBCUtils.closeStatement(pst);
		}
		
		
	}
	
	public Juego findById(Integer id_creador) 
		throws Exception{
		Juego j=null;
		Connection connection=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			connection=ConnectionManager.getConnection();
			String sql;
			sql="select id_juego, nombre, id_creador, fecha_lanzamiento from juego where id_juego= ?";
			
			pst=connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			
			int i=1;
			//pst.setString(i++,"%"+nombrejuego.toUpperCase()+"%");
			pst.setInt(i++, id_creador);
	
			rs=pst.executeQuery();
			
			while(rs.next()){
				j=loadNext(rs);
			}
			return j;
		}catch (SQLException ex) {
			throw new DataException(ex);
		}finally{
			JDBCUtils.closeConnection(connection);
			JDBCUtils.closeResultSet(rs);
			JDBCUtils.closeStatement(pst);
		}
	
	}
	
	public Juego create(Juego j) 
		throws Exception{
		Connection conn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			conn=ConnectionManager.getConnection();
			String sql;
			sql="Insert Into juego(Nombre, fecha_lanzamiento, informacion, requisitos, id_creador) "
					+ "values (?,?,?,?,?)";
			
			pst=conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			int i=1;
			pst.setString(i++, j.getNombre());
			pst.setDate(i++, new java.sql.Date(j.getFechaLanzamiento().getTime()));
			pst.setString(i++, j.getInformacion());
			pst.setString(i++, j.getRequisitos());
			
			pst.setInt(i++, j.getId_creador());
			
			
			int insertRow=pst.executeUpdate();
			
			if(insertRow == 0) {
				throw new SQLException(" No se pudo insertar");
			}
			
			rs=pst.getGeneratedKeys();
			
			if(rs.next()) {
				Integer idJuego=rs.getInt(1);
				j.setIdJuego(idJuego);
			}else {
				throw new DataException("Problemas al autogenerar primary key");
			}
			
			for (Edicion e: j.getEdiciones()) {
				edicionDAO.create(e);
			}
			
			return j;
		}catch (SQLException ex) {
			throw new DataException(ex);
		}finally{
			JDBCUtils.closeConnection(conn);
			JDBCUtils.closeResultSet(rs);
			JDBCUtils.closeStatement(pst);
		}
		
	}
	public boolean update(Juego j) throws Exception{
		
		PreparedStatement preparedStatement = null;
		Connection connection=null;
		StringBuilder sqlupdate;
		try {	
			connection=ConnectionManager.getConnection();
			sqlupdate = new StringBuilder(
					" UPDATE Juego" 
					);
			
			if (j.getNombre()!=null) {}
			
			if (j.getFechaLanzamiento()!=null) {}
			
			if (j.getInformacion()!=null) {}
			
			if (j.getInformacion()!=null) {}
			
			if (j.getRequisitos()!=null) {}
			
			if (j.getId_creador()!=null) {}
			
			sqlupdate.append("WHERE id_juego = ?");
			
			preparedStatement = connection.prepareStatement(sqlupdate.toString());
			

			int i = 1;
			if (j.getNombre()!=null) 
				preparedStatement.setString(i++,j.getNombre());
			
			if (j.getFechaLanzamiento()!=null) 
				preparedStatement.setDate(i++,(java.sql.Date) j.getFechaLanzamiento());
			
			if (j.getInformacion()!=null) 
				preparedStatement.setString(i++,j.getInformacion());
			
			if (j.getRequisitos()!=null) 
				preparedStatement.setString(i++,j.getRequisitos());
			
			if (j.getId_creador()!=null) 
				preparedStatement.setInt(i++,j.getId_creador());
			

			preparedStatement.setInt(i++, j.getIdJuego());

			int updatedRows = preparedStatement.executeUpdate();

			if (updatedRows > 1) {
				throw new SQLException("Id duplicado = '" + j.getIdJuego() + "' en table 'Juego'");
			}     
			return true;
		} catch (SQLException e) {
			throw new DataException(e);    
		} finally {
			JDBCUtils.closeStatement(preparedStatement);
		}              		
		
	}
		

	public void delete(Juego j) {}
			
	
	public Juego loadNext(ResultSet rs) 
		throws Exception{
			int i=1;
			int id  = rs.getInt(i++);
			String nombre = rs.getString(i++);
			int idCreador = rs.getInt(i++);
			Date fechaLanzamiento=rs.getDate(i++);
			
			
			Juego j= new Juego();
			j.setIdJuego(id);
			j.setNombre(nombre);
			j.setFechaLanzamiento(fechaLanzamiento);
			j.setId_creador(idCreador);
			
			/*List<Edicion> ediciones = edicionDAO.findByJuego(id);
			j.setEdiciones(ediciones);
			*/
			return j;
			//Edicion e=EdicionDAO.findById(id);
			//j.setEdiciones(e);
		
	}
	
}
