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
import com.eddie.ecommerce.model.Categoria;
import com.eddie.ecommerce.model.Idioma;
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
		
		
		if(rs.next()){
			p=loadNext(rs);
			
		}else {
			throw new InstanceNotFoundException("Error "+id+" id introducido incorrecto", Plataforma.class.getName());
		}
		return p;
	}catch (SQLException ex) {
		System.out.println("Hemos detectado problemas. Por favor compruebe los datos");
		throw new DataException(ex);
	}finally{
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
			System.out.println("Hemos detectado problemas. Por favor compruebe los datos");
			throw new DataException(ex);
		}finally{
			JDBCUtils.closeResultSet(rs);
			JDBCUtils.closeStatement(pst);
		}
	}
	
	@Override
	public List<Plataforma> findByJuego(Connection conexion, Integer idJuego) throws DataException {
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {

			String queryString = (
									"select jp.id_plataforma, p.nombre " +
									"from juego_plataforma jp " +
									"inner join plataforma p on jp.id_plataforma=p.id_plataforma " +
									"where jp.id_juego = ?");


			preparedStatement = conexion.prepareStatement(queryString,
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			int i = 1;
			preparedStatement.setInt(i++, idJuego);

			rs = preparedStatement.executeQuery();

			// Recupera la pagina de resultados
			List<Plataforma> plataformas = new ArrayList<Plataforma>();                        
			Plataforma p = null;

			
			while(rs.next()){
					p = loadNext(rs);
					plataformas.add(p);               	
			}

			return plataformas;
	
			} catch (SQLException e) {
				System.out.println("Hemos detectado problemas. Por favor compruebe los datos");
				throw new DataException(e);
			} finally {
				JDBCUtils.closeResultSet(rs);
				JDBCUtils.closeStatement(preparedStatement);
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
