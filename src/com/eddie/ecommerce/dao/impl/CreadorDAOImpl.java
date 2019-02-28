package com.eddie.ecommerce.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.eddie.ecommerce.dao.CreadorDAO;
import com.eddie.ecommerce.dao.Utils.JDBCUtils;
import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.exceptions.InstanceNotFoundException;
import com.eddie.ecommerce.model.Creador;

public class CreadorDAOImpl implements CreadorDAO{
	
	private static Logger logger=LogManager.getLogger(CreadorDAOImpl.class);

	@Override
	public Creador findbyIdCreador(Connection conexion, Integer id) throws InstanceNotFoundException,DataException {
		
		if(logger.isDebugEnabled()) {
			logger.debug("Id = "+id);
		}
		
		Creador c=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
	try {
		String sql;
		sql="select id_creador, nombre from creador where id_creador= ? ";
		
		logger.debug(sql);
		
		pst=conexion.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		
		int i=1;
		pst.setInt(i++, id);
		
		rs=pst.executeQuery();
		
		if(rs.next()){
			c=loadNext(rs);
			
		}else {
			throw new InstanceNotFoundException("Error "+id+" id introducido incorrecto", Creador.class.getName());
		}
		return c;
	}catch (SQLException ex) {
		logger.error(ex.getMessage(),ex);
		throw new DataException(ex);
	}finally{
		JDBCUtils.closeResultSet(rs);
		JDBCUtils.closeStatement(pst);
	}
	}

	@Override
	public List<Creador> findAll(Connection conexion) throws DataException {
		Creador c=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			
			String sql;
			sql="select id_creador, nombre from creador";

			logger.debug(sql);
			
			pst=conexion.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			
			rs=pst.executeQuery();
			
			List<Creador> resultado=new ArrayList<Creador>();
			while(rs.next()){
				c=loadNext(rs);
				resultado.add(c);
			}
			return resultado;
		}catch (SQLException ex) {
			logger.error(ex.getMessage(),ex);
			throw new DataException(ex);
		}finally{
			JDBCUtils.closeResultSet(rs);
			JDBCUtils.closeStatement(pst);
		}
	}
	public Creador loadNext(ResultSet rs) 
			throws DataException,SQLException{
				int i=1;
				Integer idCreador  = rs.getInt(i++);
				String nombre = rs.getString(i++);
				
				
				Creador c= new Creador();
				c.setIdCreador(idCreador);
				c.setNombre(nombre);
				
				return c;
			
		}
}
