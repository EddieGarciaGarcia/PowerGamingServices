package com.eddie.ecommerce.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.eddie.ecommerce.dao.JuegoDAO;
import com.eddie.ecommerce.dao.Utils.ConnectionManager;
import com.eddie.ecommerce.dao.Utils.JDBCUtils;
import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.exceptions.DuplicateInstanceException;
import com.eddie.ecommerce.exceptions.InstanceNotFoundException;
import com.eddie.ecommerce.model.Categoria;
import com.eddie.ecommerce.model.Idioma;
import com.eddie.ecommerce.model.Juego;
import com.eddie.ecommerce.model.JuegoCriteria;
import com.eddie.ecommerce.model.Plataforma;
import com.eddie.ecommerce.service.CategoriaService;
import com.eddie.ecommerce.service.IdiomaService;
import com.eddie.ecommerce.service.PlataformaService;
import com.eddie.ecommerce.service.impl.CategoriaServiceImpl;
import com.eddie.ecommerce.service.impl.IdiomaServiceImpl;
import com.eddie.ecommerce.service.impl.PlataformaServiceImpl;


public class JuegoDAOImpl implements JuegoDAO{
		
		private static Logger logger=LogManager.getLogger(JuegoDAOImpl.class);

		public List<Juego> findByJuegoCriteria(JuegoCriteria jc, String idioma, Connection connection) throws DataException {
			
			if(logger.isDebugEnabled()) {
				logger.debug("JuegoCriteria = "+jc.toString()+" ,idioma = "+idioma);
			}
			
			PreparedStatement pst=null;
			ResultSet rs=null;
			StringBuilder strb=null;
			try {
				
				strb=new StringBuilder("select j.id_juego, j.nombre, YEAR(j.fecha_lanzamiento), j.id_creador from juego j INNER JOIN juego_idiomaweb jiw ON j.id_juego = jiw.id_juego" );
				
				boolean first=true;
				
			
				if(!jc.getCategoria().isEmpty()) {
					strb.append(" inner join juego_categoria jc on j.id_juego=jc.id_juego inner join categoria c on jc.id_categoria=c.id_categoria ");
				}
				
				if(!jc.getIdioma().isEmpty()) {
					strb.append(" inner join juego_idioma ji on j.id_juego=ji.id_juego inner join idioma i on ji.id_idioma=i.id_idioma ");
				}
				
				if(!jc.getPlataforma().isEmpty()) {
					strb.append(" inner join juego_plataforma jp on j.id_juego=jp.id_juego inner join plataforma p on jp.id_plataforma=p.id_plataforma ");
				}
				
				if(jc.getNombre()!=null) {
					JDBCUtils.addClause(strb,first," j.nombre like ? ");
					first=false;
				}
				
				if(jc.getFechaLanzamiento()!=null) {
					JDBCUtils.addClause(strb,first," YEAR(j.fecha_lanzamiento) = ? ");
					first=false;
				}
			
				if(jc.getIdCreador()!=null) {
					JDBCUtils.addClause(strb,first," c.id_creador = ? ");
					first=false;
				}
				
				if(idioma!=null) {
					JDBCUtils.addClause(strb,first," jiw.id_idioma_web like ? ");
					first=false;
				}
				
				if (!jc.getCategoria().isEmpty()) {
					JDBCUtils.addClause(strb, first,addCategoria(jc.getCategoria()).toString());	
					first = false;
				}
				
				if (!jc.getIdioma().isEmpty()) {
					JDBCUtils.addClause(strb, first,addIdioma(jc.getIdioma()).toString());	
					first = false;
				}
				
				if (!jc.getPlataforma().isEmpty()) {
					JDBCUtils.addClause(strb, first,addPlataforma(jc.getPlataforma()).toString());	
					first = false;
				}
				
				pst = connection.prepareStatement(strb.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				
				int i = 1;
				if(jc.getNombre()!=null) {
					pst.setString(i++, "%" +jc.getNombre()+"%");
				}
				if(jc.getFechaLanzamiento()!=null) {
					pst.setDate(i++, (java.sql.Date) jc.getFechaLanzamiento() );
				}
				if(jc.getIdCreador()!=null) {
					pst.setInt(i++, jc.getIdCreador() );
				}
				if (idioma!=null) { 
					pst.setString(i++,idioma);
				}
				
				logger.debug(strb);
			
				rs = pst.executeQuery();
				
				List<Juego> juegos = new ArrayList<Juego>();
				Juego j=null;
				while(rs.next()){
					j=loadNext(rs);
					juegos.add(j);
				}
				return juegos;
				}catch (SQLException e) {
					logger.error(e.getMessage(),e);
					throw new DataException(e);
				}finally {
				JDBCUtils.closeResultSet(rs);
				JDBCUtils.closeStatement(pst);
		}
		}

		public List<Juego> findAllByDate(Connection connection) throws DataException{
			
				Juego j=null;
				PreparedStatement pst=null;
				ResultSet rs=null;
			try {
				connection=ConnectionManager.getConnection();
				String sql;
				sql="select id_juego, nombre,fecha_lanzamiento, id_creador from juego order by fecha_lanzamiento desc  ";
				
				logger.debug(sql);
				
				pst=connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				
				rs=pst.executeQuery();
				
				List <Juego> juegos=new ArrayList<Juego>();
				while(rs.next()){
					j=loadNext(rs);
					juegos.add(j);
					
				}
				return juegos;
			}catch (SQLException ex) {
				logger.error(ex.getMessage(),ex);
				throw new DataException(ex);
			}finally{
				JDBCUtils.closeResultSet(rs);
				JDBCUtils.closeStatement(pst);
			}
			
			
		}
		
		public Juego findById(Connection connection,Integer idJuego,String idioma) throws InstanceNotFoundException, DataException{
			
			if(logger.isDebugEnabled()) {
				logger.debug("Id= "+idJuego+" , idioma= "+idioma);
			}
			
			
			Juego j=null;
			 connection=null;
			PreparedStatement pst=null;
			ResultSet rs=null;
			
			CategoriaService categoriaServicio=new CategoriaServiceImpl();
			IdiomaService idiomaServicio=new IdiomaServiceImpl();
			PlataformaService plataformaServicio=new PlataformaServiceImpl();
			
			try {
				connection=ConnectionManager.getConnection();
				String sql;
				sql="select j.id_juego, j.nombre, j.fecha_lanzamiento, j.id_creador, ji.informacion from juego j inner join juego_idiomaweb ji on j.id_juego=ji.id_juego where j.id_juego= ? and ji.id_idioma_web like ?";
				
				pst=connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				
				int i=1;
				//pst.setString(i++,"%"+nombrejuego.toUpperCase()+"%");
				pst.setInt(i++, idJuego);
				pst.setString(i++, idioma);
				rs=pst.executeQuery();
				
				logger.debug(sql);
				
				if(rs.next()){
					j=loadNext(rs);
				}else {
					throw new InstanceNotFoundException("Error "+idJuego+" id introducido incorrecto", Juego.class.getName());
				}
				
				List<Categoria> categoria = categoriaServicio.findByJuego(idJuego,idioma);
				if (!categoria.isEmpty())
					j.setCategoria(categoria);
				
				List<Plataforma> plataforma = plataformaServicio.findByJuego(idJuego);
				if (!plataforma.isEmpty())
					j.setPlataformas(plataforma);
				
				List<Idioma> idiomas = idiomaServicio.findByJuego(idJuego, idioma);
				if (!idiomas.isEmpty())
					j.setIdiomas(idiomas);
				
				
				
				return j;
			}catch (SQLException ex) {
				logger.error(ex.getMessage(),ex);
				throw new DataException(ex);
			}finally{
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
				sql="select j.id_juego, j.nombre,j.fecha_lanzamiento, j.id_creador\r\n" + 
						"from juego j inner join usuarios_juego uj on j.id_juego=uj.id_juego\r\n" + 
						"group by j.nombre\r\n" + 
						"order by avg(uj.puntuacion) desc";
				
				logger.debug(sql);
				
				pst=connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);

				rs=pst.executeQuery();
				
				List <Juego> juegos=new ArrayList<Juego>();
				while(rs.next()){
					
					j=loadNext(rs);
					juegos.add(j);
					
				}
				return juegos;
			}catch (SQLException ex) {
				logger.error(ex.getMessage(),ex);
				throw new DataException(ex);
			}finally{
				JDBCUtils.closeResultSet(rs);
				JDBCUtils.closeStatement(pst);
			}
		}
		
		public Juego create(Connection connection,Juego j) throws DuplicateInstanceException, DataException{
			
			if(logger.isDebugEnabled()) {
				logger.debug("Juego = "+j.toString());
			}
			
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
				pst.setDate(i++, (java.sql.Date) j.getFechaLanzamiento());			
				pst.setInt(i++, j.getIdCreador());
				
				
				logger.debug(sql);
				
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
				logger.error(ex.getMessage(),ex);
				throw new DataException(ex);
			}finally{
				JDBCUtils.closeResultSet(rs);
				JDBCUtils.closeStatement(pst);
			}
			
		}
		public boolean update(Connection connection,Juego j) throws InstanceNotFoundException, DataException{
			
			if(logger.isDebugEnabled()) {
				logger.debug("Juego = "+j.toString());
			}
			
			PreparedStatement preparedStatement = null;
			connection=null;
			StringBuilder sqlupdate;
			try {	
				connection=ConnectionManager.getConnection();
				sqlupdate = new StringBuilder(" UPDATE Juego");
				
				boolean first = true;
				
				if (j.getNombre()!=null) {
					JDBCUtils.addUpdate(sqlupdate,first," nombre = ?");
					first=false;
				}
				
				if (j.getFechaLanzamiento()!=null) {
					JDBCUtils.addUpdate(sqlupdate,first," FECHA_LANZAMIENTO = ?");
					first=false;
				}
						
				if (j.getIdCreador()!=null) {
					JDBCUtils.addUpdate(sqlupdate,first," ID_CREADOR = ?");
					first=false;
				}
				
				sqlupdate.append("WHERE id_juego = ?");
				
				logger.debug(sqlupdate);
				
				preparedStatement = connection.prepareStatement(sqlupdate.toString());
				

				int i = 1;
				if (j.getNombre()!=null) 
					preparedStatement.setString(i++,j.getNombre());
				
				if (j.getFechaLanzamiento()!=null) 
					preparedStatement.setDate(i++, (java.sql.Date) j.getFechaLanzamiento());
				
				if (j.getIdCreador()!=null) 
					preparedStatement.setInt(i++,j.getIdCreador());
				

				preparedStatement.setInt(i++, j.getIdJuego());

				int updatedRows = preparedStatement.executeUpdate();

				if (updatedRows > 1) {
					throw new SQLException("Id duplicado = '" + j.getIdJuego() + "' en table 'Juego'");
				}     
				return true;
			} catch (SQLException e) {
				logger.error(e.getMessage(),e);
				throw new DataException(e);    
			} finally {
				JDBCUtils.closeStatement(preparedStatement);
			}              		
			
		}
			

		public void delete(Connection connection,Integer id) throws DataException {
			
			if(logger.isDebugEnabled()) {
				logger.debug("Id= "+id);
			}
			
			PreparedStatement preparedStatement = null;

			try {
				String queryString =	
						  "DELETE FROM Juego WHERE id_juego = ? ";
				
				preparedStatement = connection.prepareStatement(queryString);
				
				logger.debug(queryString);
				
				int i = 1;
				preparedStatement.setInt(i++, id);

				int removedRows = preparedStatement.executeUpdate();

				if (removedRows == 0) {
					throw new InstanceNotFoundException(id,Juego.class.getName());
				} 
	

			} catch (SQLException e) {
				logger.error(e.getMessage(),e);
				throw new DataException(e);
			} finally {
				JDBCUtils.closeStatement(preparedStatement);
			}
			
		}
		
		public Juego loadNext(ResultSet rs) 
			throws DataException,SQLException{
				int i=1;
				Integer id  = rs.getInt(i++);
				String nombre = rs.getString(i++);
				Date fechaLanzamiento=rs.getDate(i++);
				Integer idCreador = rs.getInt(i++);
				
				
				Juego j= new Juego();
				j.setIdJuego(id);
				j.setNombre(nombre);
				j.setFechaLanzamiento(fechaLanzamiento);
				j.setIdCreador(idCreador);
				
				return j;
				
		}
		
		private StringBuilder addCategoria(List<Categoria> categorias) {
		
			boolean inner = true;
			StringBuilder lista = new StringBuilder();
			for (Categoria c : categorias) {
				lista.append(inner ? " (c.id_categoria = "+c.getIdCategria() : " OR " + c.getIdCategria());
				inner=false;	
			}
			lista.append(" ) ");
			return lista;
		}
		private StringBuilder addPlataforma(List<Plataforma> plataforma) {
		
			boolean inner = true;
			StringBuilder lista = new StringBuilder();
			for (Plataforma p : plataforma) {
				lista.append(inner ? " (p.id_plataforma = "+p.getIdPlatadorma() : " OR " + p.getIdPlatadorma());
				inner=false;	
			}
			lista.append(" ) ");
			return lista;
		}
		private StringBuilder addIdioma(List<Idioma> idioma) {
			
			boolean inner = true;
			StringBuilder lista = new StringBuilder();
			for (Idioma i : idioma) {
				lista.append(inner ? " (i.id_idioma LIKE '"+ i.getIdIdioma()+"'" : " OR '" + i.getIdIdioma()+"'");
				inner=false;	
			}
			lista.append(" ) ");
			return lista;
		}
		
		
}
