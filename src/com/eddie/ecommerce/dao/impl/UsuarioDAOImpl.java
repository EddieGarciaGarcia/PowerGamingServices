package com.eddie.ecommerce.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import com.eddie.ecommerce.dao.UsuarioDAO;
import com.eddie.ecommerce.dao.Utils.ConnectionManager;
import com.eddie.ecommerce.dao.Utils.JDBCUtils;
import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.exceptions.InstanceNotFoundException;
import com.eddie.ecommerce.model.Categoria;
import com.eddie.ecommerce.model.Direccion;
import com.eddie.ecommerce.model.Usuario;

public class UsuarioDAOImpl implements UsuarioDAO{

	@Override
	public Usuario create(Usuario u, Connection connection) throws DataException {
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			connection=ConnectionManager.getConnection();
			String sql;
			sql="Insert Into usuario(email,nombre,apellido1,apellido2,telefono,password, fecha_nacimiento, genero,nombre_user,id_direccion) "
					+ "values (?,?,?,?,?,?,?,?,?,?)";
			
			pst=connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			int i=1;
			if(u.getEmail()==null || u.getEmail().equals("")) {
				System.out.println("fallo email null o vacio");
			}else {
				pst.setString(i++,u.getEmail());
			}
			pst.setString(i++, u.getNombre());
			pst.setString(i++, u.getApellido1());
			pst.setString(i++, u.getApellido2());
			pst.setString(i++, u.getTelefono());
			pst.setString(i++, u.getPassword());
			pst.setDate(i++, new java.sql.Date(u.getFechaNacimiento().getTime()));
			pst.setString(i++, u.getGenero());
			pst.setString(i++, u.getNombreUser());
			pst.setInt(i++, u.getDireccion());
			
			int insertRow=pst.executeUpdate();
			
			if(insertRow == 0) {
				throw new SQLException(" No se pudo insertar");
			}
			
			return u;
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
	public boolean update(Usuario u, Connection connection) throws InstanceNotFoundException, DataException{
		PreparedStatement preparedStatement = null;
		connection=null;
		StringBuilder sqlupdate;
		try {	
			connection=ConnectionManager.getConnection();
			sqlupdate = new StringBuilder(" UPDATE Usuario");
			
			boolean first = true;
			
			if (u.getNombre()!=null) {
				addUpdate(sqlupdate,first," nombre = ?");
				first=false;
			}
			
			if (u.getApellido1()!=null) {
				addUpdate(sqlupdate,first," apellido1 = ?");
				first=false;
			}
			
			if (u.getApellido2()!=null) {
				addUpdate(sqlupdate,first," apellido2 = ?");
				first=false;
			}
			
			if (u.getTelefono()!=null) {
				addUpdate(sqlupdate,first," telefono = ?");
				first=false;
			}
					
			if (u.getNombreUser()!=null) {
				addUpdate(sqlupdate,first," nombre_user = ?");
				first=false;
			}
			
			if (u.getDireccion()!=null) {
				addUpdate(sqlupdate,first," id_direccion = ?");
				first=false;
			}
			
			sqlupdate.append("WHERE email = ?");
			
			preparedStatement = connection.prepareStatement(sqlupdate.toString());
			

			int i = 1;
			if (u.getNombre()!=null) 
				preparedStatement.setString(i++,u.getNombre());
			
			if (u.getApellido1()!=null) 
				preparedStatement.setString(i++,u.getApellido1());
			if (u.getApellido2()!=null) 
				preparedStatement.setString(i++,u.getApellido2());
			if (u.getTelefono()!=null) 
				preparedStatement.setString(i++,u.getTelefono());
			
			if (u.getNombreUser()!=null) 
				preparedStatement.setString(i++,u.getNombreUser());
			
			if (u.getDireccion()!=null) 
				preparedStatement.setInt(i++,u.getDireccion());
			
			preparedStatement.setString(i++, u.getEmail());

			int updatedRows = preparedStatement.executeUpdate();

			if (updatedRows > 1) {
				throw new SQLException();
			}     
			return true;
		} catch (SQLException e) {
			System.out.println("Hemos detectado problemas. Por favor compruebe los datos");
			throw new DataException(e);    
		} finally {
			JDBCUtils.closeStatement(preparedStatement);
		}              		
	}
	
	@Override
	public long delete(String email, Connection connection) throws DataException{
		PreparedStatement preparedStatement = null;

		try {
			String queryString =	
					  "DELETE FROM Usuario " 
					+ "WHERE email = ? ";
			
			preparedStatement = connection.prepareStatement(queryString);

			int i = 1;
			preparedStatement.setString(i++, email);

			int removedRows = preparedStatement.executeUpdate();

			if (removedRows == 0) {
				throw new InstanceNotFoundException(email,Usuario.class.getName());
			} 
			

			return removedRows;

		} catch (SQLException e) {
			System.out.println("Hemos detectado problemas. Por favor compruebe los datos");
			throw new DataException(e);
		} finally {
			JDBCUtils.closeStatement(preparedStatement);
		}
		
	}

	
	@Override
	public Usuario findById(String email, Connection connection) throws InstanceNotFoundException, DataException {
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
			
			if(rs.next()){
				u=loadNext(rs);
			}else {
				throw new InstanceNotFoundException("Error "+email+" id introducido incorrecto", Usuario.class.getName());
			}
			return u;
		}catch (SQLException ex) {
			System.out.println("Hemos detectado problemas. Por favor compruebe los datos");
			throw new DataException(ex);
		}finally{
			JDBCUtils.closeConnection(connection);
			JDBCUtils.closeResultSet(rs);
			JDBCUtils.closeStatement(pst);
		}
		
	}
	
	
	public Usuario loadNext(ResultSet rs) 
		throws SQLException,DataException{
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
	
	
	private void addUpdate(StringBuilder queryString, boolean first, String clause) {
		queryString.append(first? " SET ": " , ").append(clause);
	}

	
}
