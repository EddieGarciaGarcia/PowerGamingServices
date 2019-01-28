package com.eddie.ecommerce.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.eddie.ecommerce.dao.CategoriaDAO;
import com.eddie.ecommerce.dao.Utils.ConnectionManager;
import com.eddie.ecommerce.dao.Utils.JDBCUtils;
import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.exceptions.InstanceNotFoundException;
import com.eddie.ecommerce.model.Categoria;



public class CategoriaDAOImpl implements CategoriaDAO{

	@Override
	public Categoria findById(Connection conexion, Integer id, String idioma) throws InstanceNotFoundException, DataException {
		Categoria c=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
	try {
		conexion=ConnectionManager.getConnection();
		String sql;
		sql="select id_categoria, nombre from categoria_idiomaweb where id_categoria= ? and id_idioma_web like '"+idioma+"'";
		
		pst=conexion.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		
		int i=1;
		pst.setInt(i++, id);
		
		rs=pst.executeQuery();
		
		
		while(rs.next()){
			c=loadNext(rs);
			
		}
		return c;
	}catch (SQLException ex) {
		throw new DataException(ex);
	}finally{
		JDBCUtils.closeConnection(conexion);
		JDBCUtils.closeResultSet(rs);
		JDBCUtils.closeStatement(pst);
	}
	}

	@Override
	public List<Categoria> findAll(Connection conexion, String idioma) throws DataException {
		Categoria c=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			conexion=ConnectionManager.getConnection();
			String sql;
			sql="select c.id_categoria, c.nombre from categoria_idiomaweb c where id_idioma_web like ?";

			pst=conexion.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			
			int i=1;
			//pst.setString(i++,"%"+nombrejuego.toUpperCase()+"%");
			pst.setString(i++, idioma);	
			rs=pst.executeQuery();
			
			List<Categoria> categorias=new ArrayList<Categoria>();
			while(rs.next()){
				c=loadNext(rs);
				categorias.add(c);
			}
			return categorias;
		}catch (SQLException ex) {
			throw new DataException(ex);
		}finally{
			JDBCUtils.closeConnection(conexion);
			JDBCUtils.closeResultSet(rs);
			JDBCUtils.closeStatement(pst);
		}
	}
	
	@Override
	public List<Categoria> findByJuego(Connection conexion, Integer idJuego, String idioma) throws DataException {
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {

			String queryString = (
									"select jc.id_categoria, ciw.nombre " +
									"from juego_categoria jc " +
									"inner join categoria c on jc.id_categoria=c.id_categoria " +
									"inner join categoria_idiomaweb ciw on ciw.id_categoria=c.id_categoria " +
									"where jc.id_juego = ? AND ciw.id_idioma_web like ? ");


			preparedStatement = conexion.prepareStatement(queryString,
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			int i = 1;
			preparedStatement.setInt(i++, idJuego);
			preparedStatement.setString(i++, idioma);

			rs = preparedStatement.executeQuery();

			// Recupera la pagina de resultados
			List<Categoria> categorias = new ArrayList<Categoria>();                        
			Categoria c = null;

			
			while(rs.next()){
					c = loadNext(rs);
					categorias.add(c);               	
			}

			return categorias;
	
			} catch (SQLException e) {
				throw new DataException(e);
			} finally {
				JDBCUtils.closeResultSet(rs);
				JDBCUtils.closeStatement(preparedStatement);
		}
	}
	
	public Categoria loadNext(ResultSet rs)throws DataException,SQLException{
		int i=1;
		int idCategoria = rs.getInt(i++);
		String nombre = rs.getString(i++);

		Categoria c=new Categoria();
		c.setIdCategria(idCategoria);
		c.setNombre(nombre);
		
		return c;

	}

	
}
