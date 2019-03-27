package com.eddie.ecommerce.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.eddie.ecommerce.dao.FormatoDAO;
import com.eddie.ecommerce.dao.Utils.JDBCUtils;
import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.exceptions.InstanceNotFoundException;
import com.eddie.ecommerce.model.Formato;

public class FormatoDAOImpl implements FormatoDAO{
	
	private static Logger logger=LogManager.getLogger(FormatoDAOImpl.class);
	
	public FormatoDAOImpl() {}
	
	public List<Formato> findbyIdsFormato(Connection conexion,List<Integer> ids, String idioma) throws InstanceNotFoundException,DataException{
			
		if(logger.isDebugEnabled()) {
			logger.debug("id= "+ids+" , idioma = "+idioma);
		}
			List<Formato> resultado=new ArrayList<Formato>();
			Formato f=null;
			PreparedStatement pst=null;
			ResultSet rs=null;
		try {

			StringBuilder sql;
			sql=new StringBuilder("select id_formato, nombre from idiomaweb_formato where id_idioma_web like '"+idioma+"' and id_formato in (");
			JDBCUtils.anhadirIN(sql, ids);
			pst=conexion.prepareStatement(sql.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			
			rs=pst.executeQuery();
			
			logger.debug(sql);
			
			while(rs.next()){
				f=loadNext(rs);
				resultado.add(f);
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
	
	public List<Formato> findAll(Connection conexion, String idioma) throws DataException{
		
		if(logger.isDebugEnabled()) {
			logger.debug("Idioma = "+idioma);
		}
		
		Formato f=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
	
			String sql;
			sql="select f.id_formato, f.nombre from idiomaweb_formato f where id_idioma_web like ?";

			pst=conexion.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			
			int i=1;
			//pst.setString(i++,"%"+nombrejuego.toUpperCase()+"%");
			pst.setString(i++, idioma);	
			rs=pst.executeQuery();
			
			logger.debug(sql);
			
			List<Formato> resultado=new ArrayList<Formato>();
			while(rs.next()){
				f=loadNext(rs);
				resultado.add(f);
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
	
	public Formato loadNext(ResultSet rs)throws DataException,SQLException{
		int i=1;
		int idFormato  = rs.getInt(i++);
		String nombre = rs.getString(i++);

		Formato f=new Formato();
		f.setIdFormato(idFormato);
		f.setNombre(nombre);
		
		return f;

	}
}
