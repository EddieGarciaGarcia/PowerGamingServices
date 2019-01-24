package com.eddie.training.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.eddie.training.dao.UsuarioDAO;
import com.eddie.training.dao.Utils.ConnectionManager;
import com.eddie.training.dao.Utils.JDBCUtils;
import com.eddie.training.model.Direccion;
import com.eddie.training.model.Usuario;

public class UsuarioDAOImpl implements UsuarioDAO{

	@Override
	public Usuario create(Usuario u, Connection connection) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Usuario u, Connection connection) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public long delete(Long id, Connection connection) throws Exception {
		return id;
		// TODO Auto-generated method stub
		
	}

	@Override
	public Usuario findById(String email, Connection connection) throws Exception {
		Usuario u=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			connection=ConnectionManager.getConnection();
			String sql;
			sql="select email,nombre,apellido1,apellido2,telefono,password,fecha_nacimiento,genero,nombre_user,id_direccion from usuario where email=?";
			
			pst=connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			
			int i=1;
			//pst.setString(i++,"%"+nombrejuego.toUpperCase()+"%");
			pst.setString(i++, email);	
			rs=pst.executeQuery();
			
			while(rs.next()){
				u=loadNext(rs);
			}
			return u;
		}catch (SQLException ex) {
			throw new Exception(ex);
		}finally{
			JDBCUtils.closeConnection(connection);
			JDBCUtils.closeResultSet(rs);
			JDBCUtils.closeStatement(pst);
		}
		
	}
	
	
	public Usuario loadNext(ResultSet rs) 
		throws Exception{
			int i=1;
			String email  = rs.getString(i++);
			String nombre = rs.getString(i++);
			String apellido1=rs.getString(i++);
			String apellido2=rs.getString(i++);
			String telefono=rs.getString(i++);
			String password=rs.getString(i++);
			Date fechaNacimiento=rs.getDate(i++);
			String genero=rs.getString(i++);
			String nombreUser=rs.getString(i++);
			Integer idDireccion=rs.getInt(i++);
			
			Usuario u= new Usuario();
			
			u.setEmail(email);
			u.setNombre(nombre);
			u.setApellido1(apellido1);
			u.setApellido2(apellido2);
			u.setTelefono(telefono);
			u.setPassword(password);
			u.setFechaNacimiento(fechaNacimiento);
			u.setNombreUser(nombreUser);
			u.setGenero(genero);
			
			//u.setDireccion(idDireccion);
			
			/*List<Edicion> ediciones = edicionDAO.findByJuego(id);
			j.setEdiciones(ediciones);
			*/
			return u;
			//Edicion e=EdicionDAO.findById(id);
			//j.setEdiciones(e);
		
	}
}
