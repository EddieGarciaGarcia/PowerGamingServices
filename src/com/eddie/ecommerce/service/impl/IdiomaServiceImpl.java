package com.eddie.ecommerce.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.eddie.ecommerce.dao.IdiomaDAO;
import com.eddie.ecommerce.dao.Utils.ConnectionManager;
import com.eddie.ecommerce.dao.Utils.JDBCUtils;
import com.eddie.ecommerce.dao.impl.IdiomaDAOImpl;
import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.exceptions.InstanceNotFoundException;
import com.eddie.ecommerce.model.Categoria;
import com.eddie.ecommerce.model.Idioma;
import com.eddie.ecommerce.service.IdiomaService;

public class IdiomaServiceImpl implements IdiomaService{
	
	IdiomaDAO idao=null;
	
	public IdiomaServiceImpl() {
		idao=new IdiomaDAOImpl();
	}
	
	@Override
	public Idioma findById(String id, String idioma) throws InstanceNotFoundException, DataException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Idioma> findAll(String idioma) throws DataException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Idioma> findByJuego(Integer idJuego, String idioma) throws DataException, SQLException {
		boolean commit=false;
		Connection c=null;
		try {
		c=ConnectionManager.getConnection();
		c.setAutoCommit(false);
		
		List<Idioma> i=idao.findByJuego(c, idJuego, idioma);
		
		return i;
		
		}catch(SQLException e) {
			e.printStackTrace();
			throw e;
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
	}

}
