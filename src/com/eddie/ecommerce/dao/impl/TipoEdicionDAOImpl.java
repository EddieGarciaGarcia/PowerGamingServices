package com.eddie.ecommerce.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.eddie.ecommerce.dao.TipoEdicionDAO;
import com.eddie.ecommerce.dao.Utils.ConnectionManager;
import com.eddie.ecommerce.dao.Utils.JDBCUtils;
import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.exceptions.InstanceNotFoundException;
import com.eddie.ecommerce.model.Categoria;
import com.eddie.ecommerce.model.TipoEdicion;

public class TipoEdicionDAOImpl implements TipoEdicionDAO{
	
	public TipoEdicionDAOImpl() {}
	
	public TipoEdicion findbyIdTipoEdicion(Connection conexion, Integer id,String idioma)
			throws InstanceNotFoundException, DataException{
			TipoEdicion te=null;
			Connection connection=null;
			PreparedStatement pst=null;
			ResultSet rs=null;
		try {
			connection=ConnectionManager.getConnection();
			String sql;
			sql="select te.id_tipo_edicion, id.nombre from tipoedicion te inner join idiomaweb_tipoedicion id on te.id_tipo_edicion=id.id_tipo_edicion where te.id_tipo_edicion= ? and id_idioma_web like '"+idioma+"'";
			
			pst=connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			
			int i=1;
			pst.setLong(i++, id);
			
			rs=pst.executeQuery();
			
			
			if(rs.next()){
				te=loadNext(rs);
				
			}else {
				throw new InstanceNotFoundException("Error "+id+" id introducido incorrecto", TipoEdicionDAOImpl.class.getName());
			}
			return te;
		}catch (SQLException ex) {
			System.out.println("Hemos detectado problemas. Por favor compruebe los datos");
			throw new DataException(ex);
		}finally{
			JDBCUtils.closeConnection(connection);
			JDBCUtils.closeResultSet(rs);
			JDBCUtils.closeStatement(pst);
		}
		
		
	}
	
	@Override
	public List<TipoEdicion> findAll(Connection conexion, String idioma) throws DataException {
		TipoEdicion te=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			conexion=ConnectionManager.getConnection();
			String sql;
			sql="select id_tipo_edicion, nombre from idiomaweb_tipoedicion where id_idioma_web like ?";

			pst=conexion.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			
			int i=1;
			//pst.setString(i++,"%"+nombrejuego.toUpperCase()+"%");
			pst.setString(i++, idioma);	
			rs=pst.executeQuery();
			
			List<TipoEdicion> resultado=new ArrayList<TipoEdicion>();
			while(rs.next()){
				te=loadNext(rs);
				resultado.add(te);
			}
			return resultado;
		}catch (SQLException ex) {
			System.out.println("Hemos detectado problemas. Por favor compruebe los datos");
			throw new DataException(ex);
		}finally{
			JDBCUtils.closeConnection(conexion);
			JDBCUtils.closeResultSet(rs);
			JDBCUtils.closeStatement(pst);
		}
	}
	
		
	public TipoEdicion loadNext(ResultSet rs)throws SQLException,DataException{
		int i=1;
		int idTipoEdicion  = rs.getInt(i++);
		String nombre = rs.getString(i++);

		TipoEdicion te=new TipoEdicion();
		te.setIdTipoEdicion(idTipoEdicion);
		te.setNombre(nombre);
		
		return te;

	}

}