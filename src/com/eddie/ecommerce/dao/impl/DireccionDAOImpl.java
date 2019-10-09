package com.eddie.ecommerce.dao.impl;

import com.eddie.ecommerce.dao.DireccionDAO;
import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.exceptions.InstanceNotFoundException;
import com.eddie.ecommerce.model.Direccion;
import com.eddie.ecommerce.utils.JDBCUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;


public class DireccionDAOImpl implements DireccionDAO{

	private static Logger logger=LogManager.getLogger(DireccionDAOImpl.class);
	
	@Override
	public Direccion findById(Connection conexion,String email) throws DataException {

		Direccion direccion;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		StringBuilder query;
		try {
			query = new StringBuilder();
			query.append("select id_direccion,id_provincia,email,codigo_postal,calle,numero,piso,localidad ");
			query.append(" from direccion");
			query.append(" where email=?");

			preparedStatement=conexion.prepareStatement(query.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);

			preparedStatement.setString(1, email);
			resultSet=preparedStatement.executeQuery();
			
			if(resultSet.next()){
				direccion = new Direccion();
				direccion.setIdDireccion(resultSet.getInt("id_direccion"));
				direccion.setEmail(resultSet.getString("email"));
				direccion.setIdprovincia(resultSet.getInt("id_provincia"));
				direccion.setCodigoPostal(resultSet.getString("codigo_postal"));
				direccion.setCalle(resultSet.getString("calle"));
				direccion.setNumero(resultSet.getString("numero"));
				direccion.setPiso(resultSet.getString("piso"));
				direccion.setLocalidad(resultSet.getString("localidad"));

			}else {
				throw new InstanceNotFoundException("Error "+email+" id introducido incorrecto", Direccion.class.getName());
			}
			return direccion;
		}catch (SQLException ex) {
			logger.error(ex.getMessage(),ex);
			throw new DataException(ex);
		}finally{
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}
	}

	@Override
	public boolean create(Connection conexion,Direccion direccion) throws DataException {
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		StringBuilder query;
		try {
			query = new StringBuilder();
			query.append("Insert Into direccion(id_provincia,email,codigo_postal,calle,numero,piso,localidad) ");
			query.append("values (?,?,?,?,?,?)");

			preparedStatement=conexion.prepareStatement(query.toString(), Statement.RETURN_GENERATED_KEYS);
			int i = 1;
			preparedStatement.setInt(i++, direccion.getIdprovincia());
			preparedStatement.setString(i++, direccion.getCodigoPostal());
			preparedStatement.setString(i++, direccion.getCalle());
			preparedStatement.setString(i++, direccion.getNumero());
			preparedStatement.setString(i++, direccion.getPiso());
			preparedStatement.setString(i, direccion.getLocalidad());
			
			int insertRow=preparedStatement.executeUpdate();

			return insertRow != 0;
		}catch (SQLException ex) {
			logger.error(ex.getMessage(),ex);
			throw new DataException(ex);
		}finally{
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}
	}

	@Override
	public boolean update(Connection conexion,Direccion direccion) throws DataException {
		PreparedStatement preparedStatement = null;
		StringBuilder query;
		try {
			query = new StringBuilder(" UPDATE direccion");
			boolean first = true;
			if (direccion.getIdprovincia()!=null) {
				JDBCUtils.addUpdate(query,first," id_provincia = ?");
				first=false;
			}
			if (direccion.getCodigoPostal()!=null) {
				JDBCUtils.addUpdate(query,first," codigo_postal = ?");
				first=false;
			}
			if (direccion.getCalle()!=null) {
				JDBCUtils.addUpdate(query,first," calle = ?");
				first=false;
			}
			if (direccion.getNumero()!=null) {
				JDBCUtils.addUpdate(query,first," numero = ?");
				first=false;
			}
			if (direccion.getPiso()!=null) {
				JDBCUtils.addUpdate(query,first," piso = ?");
				first=false;
			}
			if (direccion.getLocalidad()!=null) {
				JDBCUtils.addUpdate(query,first," localidad = ?");
			}
			query.append(" WHERE email = ?");
			
			preparedStatement = conexion.prepareStatement(query.toString());

			int i = 1;
			if (direccion.getIdprovincia()!=null) preparedStatement.setInt(i++,direccion.getIdprovincia());
			if (direccion.getCodigoPostal()!=null) preparedStatement.setString(i++,direccion.getCodigoPostal());
			if (direccion.getCalle()!=null) preparedStatement.setString(i++,direccion.getCalle());
			if (direccion.getNumero()!=null) preparedStatement.setString(i++,direccion.getNumero());
			if (direccion.getPiso()!=null) preparedStatement.setString(i++,direccion.getPiso());
			if (direccion.getLocalidad()!=null) preparedStatement.setString(i++,direccion.getLocalidad());
			
			preparedStatement.setString(i, direccion.getEmail());
			
			int updatedRows = preparedStatement.executeUpdate();

			return updatedRows == 1;
		} catch (SQLException e) {
			logger.error(e.getMessage(),e);
			throw new DataException(e);    
		} finally {
			JDBCUtils.closeStatement(preparedStatement);
		}              		
	}

	@Override
	public boolean delete(Connection conexion,String email) throws DataException {
		PreparedStatement preparedStatement = null;
		StringBuilder query;
		try {
			query = new StringBuilder();
			query.append("DELETE FROM direccion");
			query.append(" WHERE email = ?");
			
			preparedStatement = conexion.prepareStatement(query.toString());

			preparedStatement.setString(1, email);

			int removedRows = preparedStatement.executeUpdate();

			return removedRows != 0 && removedRows <= 1;
		} catch (SQLException e) {
			logger.error(e.getMessage(),e);
			throw new DataException(e);
		} finally {
			JDBCUtils.closeStatement(preparedStatement);
		}
	}
}
