package com.eddie.ecommerce.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.eddie.ecommerce.dao.JuegoDAO;
import com.eddie.ecommerce.dao.Utils.ConnectionManager;
import com.eddie.ecommerce.dao.Utils.JDBCUtils;
import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.exceptions.DuplicateInstanceException;
import com.eddie.ecommerce.exceptions.InstanceNotFoundException;
import com.eddie.ecommerce.model.Juego;
import com.eddie.ecommerce.model.JuegoCriteria;
import com.eddie.ecommerce.model.Usuario;


public class JuegoDAOImpl implements JuegoDAO{
	//private EdicionDAOImpl edicionDAO=null;
	
		public JuegoDAOImpl() {
			//edicionDAO= new EdicionDAOImpl();
		}
		
		public List<Juego> findByJuegoCriteria(JuegoCriteria c, String idioma, Connection connection) throws DataException {
			PreparedStatement pst=null;
			ResultSet rs=null;
			StringBuilder strb=null;
			Juego j=null;
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
				
				while(rs.next()){
					j=loadNext(rs);
					juegos.add(j);
				}
				return juegos;
				}catch (SQLException e) {
					throw new DataException(e);
				}finally {
				JDBCUtils.closeResultSet(rs);
				JDBCUtils.closeStatement(pst);
				JDBCUtils.closeConnection(connection);
		}
		}

		public List<Juego> findAllByDate(Connection connection) throws DataException{
				Juego j=null;
				PreparedStatement pst=null;
				ResultSet rs=null;
			try {
				connection=ConnectionManager.getConnection();
				String sql;
				sql="select id_juego, nombre, FECHA_LANZAMIENTO,id_Creador\r\n" + 
						" from juego order by fecha_Lanzamiento DESc  ";
				
				pst=connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				
				rs=pst.executeQuery();
				
				List <Juego> juegos=new ArrayList<Juego>();
				while(rs.next()){
					j=loadNext(rs);
					juegos.add(j);
					
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
		
		public Juego findById(Connection connection,Integer idJuego) 
			throws InstanceNotFoundException, DataException{
			Juego j=null;
			 connection=null;
			PreparedStatement pst=null;
			ResultSet rs=null;
			try {
				connection=ConnectionManager.getConnection();
				String sql;
				sql="select id_juego, nombre, id_creador, fecha_lanzamiento from juego where id_juego= ?";
				
				pst=connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				
				int i=1;
				//pst.setString(i++,"%"+nombrejuego.toUpperCase()+"%");
				pst.setInt(i++, idJuego);
		
				rs=pst.executeQuery();
				
				if(rs.next()){
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
		
		@Override
		public List<Juego> findAllByValoración(Connection connection) throws DataException {
				Juego j=null;
				connection=null;
				PreparedStatement pst=null;
				ResultSet rs=null;
			try {
				connection=ConnectionManager.getConnection();
				String sql;
				sql="select j.nombre\r\n" + 
						"from juego j inner join usuarios_juego uj on j.id_juego=uj.id_juego\r\n" + 
						"group by j.nombre\r\n" + 
						"order by avg(uj.puntuacion) desc";
				
				pst=connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				int i =1;
				pst.setString(i++, j.getNombre());
				rs=pst.executeQuery();
				
				List <Juego> juegos=new ArrayList<Juego>();
				while(rs.next()){
					
					j=loadNext(rs);
					juegos.add(j);
					
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
		
		public Juego create(Connection connection,Juego j) 
			throws DuplicateInstanceException, DataException{
			connection=null;
			PreparedStatement pst=null;
			ResultSet rs=null;
			try {
				connection=ConnectionManager.getConnection();
				String sql;
				sql="Insert Into juego(Nombre, fecha_lanzamiento, id_creador) "
						+ "values (?,?,?)";
				
				pst=connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				int i=1;
				pst.setString(i++, j.getNombre());
				pst.setDate(i++, new java.sql.Date(j.getFechaLanzamiento().getTime()));			
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
				
				return j;
			}catch (SQLException ex) {
				throw new DataException(ex);
			}finally{
				JDBCUtils.closeConnection(connection);
				JDBCUtils.closeResultSet(rs);
				JDBCUtils.closeStatement(pst);
			}
			
		}
		public boolean update(Connection connection,Juego j) throws InstanceNotFoundException, DataException{
			
			PreparedStatement preparedStatement = null;
			connection=null;
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
			

		public void delete(Connection connection,Integer id) throws DataException {
			PreparedStatement preparedStatement = null;

			try {
				String queryString =	
						  "DELETE FROM Juego WHERE id_juego = ? ";
				
				preparedStatement = connection.prepareStatement(queryString);

				int i = 1;
				preparedStatement.setInt(i++, id);

				int removedRows = preparedStatement.executeUpdate();

				if (removedRows == 0) {
					throw new InstanceNotFoundException(id,Juego.class.getName());
				} 
	

			} catch (SQLException e) {
				throw new DataException(e);
			} finally {
				JDBCUtils.closeStatement(preparedStatement);
			}
			
		}
				
		private void addClause(StringBuilder queryString, boolean first, String clause) {
			queryString.append(first? "WHERE ": " AND ").append(clause);
		}
		
		private void addUpdate(StringBuilder queryString, boolean first, String clause) {
			queryString.append(first? " SET ": " , ").append(clause);
		}
		
		public Juego loadNext(ResultSet rs) 
			throws DataException,SQLException{
				int i=1;
				Integer id  = rs.getInt(i++);
				String nombre = rs.getString(i++);
				Integer idCreador = rs.getInt(i++);
				Date fechaLanzamiento=rs.getDate(i++);
				
				
				Juego j= new Juego();
				j.setIdJuego(id);
				j.setNombre(nombre);
				j.setFechaLanzamiento(fechaLanzamiento);
				j.setId_creador(idCreador);
				
				return j;
				
				
				/*List<Edicion> ediciones = edicionDAO.findByJuego(id);
				j.setEdiciones(ediciones);
				Edicion e=EdicionDAO.findById(id);
				j.setEdiciones(e);
				*/
		}

		
}
