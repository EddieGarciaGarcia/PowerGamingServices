package com.eddie.training.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.eddie.training.dao.Utils.ConnectionManager;
import com.eddie.training.dao.Utils.JDBCUtils;
import com.eddie.training.exceptions.DataException;
import com.eddie.training.model.Edicion;
import com.eddie.training.model.Juego;
import com.eddie.training.model.JuegoCriteria;

public class JuegoDAOImpl {
private EdicionDAOImpl edicionDAO=null;
	
	public JuegoDAOImpl() {
		edicionDAO= new EdicionDAOImpl();
	}
	
	public List<Juego> findByJuegoCriteria(JuegoCriteria c, String idioma, Connection connection) throws Exception {
		PreparedStatement pst=null;
		ResultSet rs=null;
		StringBuilder strb=null;
		
		try {
			strb=new StringBuilder("select j.nombre from juego j " );
			
			boolean first=true;
			
			if(!c.getCategoria().isEmpty()) {
				strb.append("inner join juego_categoria jc on j.id_juego=jc.id_juego inner join categoria c on jc.id_categoria=c.id_categoria");
			}
			
			//Falta Fecha Lanzamiento
			
			if(!c.getIdioma().isEmpty()) {
				strb.append("inner join juego_idioma ji on j.id_juego=ji.id_juego inner join idioma i on ji.id_idioma=i.id_idioma");
			}
			
			if(!c.getPlataforma().isEmpty()) {
				strb.append("inner join juego_plataforma jp on j.id_juego=jp.id_juego inner join plataforma p on jp.id_plataforma=p.id_plataforma");
			}
			if(!c.getCreador().isEmpty()) {
				strb.append("inner join creador c on j.id_creador=c.id_creador");
			}
			
			if(c.getCategoria()!=null) {
				addClause(strb,first,"c.id_categoria = ?");
				first=false;
			}
			
			//Falta Fecha Lanzamiento
			
			if(c.getIdioma()!=null) {
				addClause(strb,first,"i.id_idioma LIKE ?");
				first=false;
			}
			if(c.getPlataforma()!=null) {
				addClause(strb,first,"p.id_plataforma = ?");
				first=false;
			}
			
			if(c.getCreador()!=null) {
				addClause(strb,first,"c.id_creador = ?");
				first=false;
			}
			
			pst = connection.prepareStatement(strb.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			int i = 1;
			
			if(c.getCategoria()!=null) {
				pst.setString(i++, "%"+ c.getCategoria() +"%");
			}
			// Falta Fecha Lanzamiento
			if(c.getIdioma()!=null) {
				pst.setString(i++, "%"+ c.getIdioma() +"%");
			}
			if(c.getPlataforma()!=null) {
				pst.setString(i++, "%"+ c.getPlataforma() +"%");
			}
			if(c.getCreador()!=null) {
				pst.setString(i++, "%"+ c.getCreador() +"%");
			}
			if (idioma!=null) { 
				pst.setString(i++,idioma);
			}
			
			rs = pst.executeQuery();
			
			List<Juego> juegos = new ArrayList<Juego>();
			Juego j=null;
			
			if(rs.next()){
				j=loadNext(rs);
				juegos.add(j);
			}
			return juegos;
		} finally {
			JDBCUtils.closeResultSet(rs);
			JDBCUtils.closeStatement(pst);
	}
	}

	public List<Juego> findAll() 
		throws Exception{
			Juego j=null;
			Connection connection=null;
			PreparedStatement pst=null;
			ResultSet rs=null;
		try {
			connection=ConnectionManager.getConnection();
			String sql;
			sql="select nombre from juego order by fechaLanzamiento DESc ";
			
			pst=connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			
			int i=1;
		
			
			rs=pst.executeQuery();
			
			List <Juego> juegos=new ArrayList<Juego>();
			if(rs.next()){
				j=loadNext(rs);
				juegos.add(j);
				
			}else { throw new Exception("Non se encontrou o xogo ");}
			if (rs.next()) { throw new Exception("Xogo duplicado");}
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
			sqlupdate = new StringBuilder(" UPDATE Juego");
			
			boolean first = true;
			
			if (j.getNombre()!=null) {
				addUpdate(sqlupdate,first," nombre = ?");
				first=false;
			}
			
			if (j.getFechaLanzamiento()!=null) {
				addUpdate(sqlupdate,first," FECHA_LANZAMIENTO = ?");
				first=false;
			}
			
			if (j.getInformacion()!=null) {
				addUpdate(sqlupdate,first," INFORMACION = ?");
				first=false;
			}
			
			if (j.getRequisitos()!=null) {
				addUpdate(sqlupdate,first," REQUISITOS = ?");
				first=false;
			}
			
			if (j.getId_creador()!=null) {
				addUpdate(sqlupdate,first," ID_CREADOR = ?");
				first=false;
			}
			
			sqlupdate.append("WHERE id_juego = ?");
			
			preparedStatement = connection.prepareStatement(sqlupdate.toString());
			

			int i = 1;
			if (j.getNombre()!=null) 
				preparedStatement.setString(i++,j.getNombre());
			
			if (j.getFechaLanzamiento()!=null) 
				preparedStatement.setDate(i++,new java.sql.Date(j.getFechaLanzamiento().getTime()));
			
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
			
	private void addClause(StringBuilder queryString, boolean first, String clause) {
		queryString.append(first? "WHERE ": " AND ").append(clause);
	}
	
	private void addUpdate(StringBuilder queryString, boolean first, String clause) {
		queryString.append(first? " SET ": " , ").append(clause);
	}
	
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
