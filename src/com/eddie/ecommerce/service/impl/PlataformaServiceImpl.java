package com.eddie.ecommerce.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.eddie.ecommerce.dao.PlataformaDAO;
import com.eddie.ecommerce.dao.Utils.ConnectionManager;
import com.eddie.ecommerce.dao.Utils.JDBCUtils;
import com.eddie.ecommerce.dao.impl.PlataformaDAOImpl;
import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.exceptions.InstanceNotFoundException;
import com.eddie.ecommerce.model.Plataforma;
import com.eddie.ecommerce.service.PlataformaService;

public class PlataformaServiceImpl implements PlataformaService{

	PlataformaDAO pdao=null;
	
	public PlataformaServiceImpl() {
		pdao= new PlataformaDAOImpl();
	}
	
	@Override
	public Plataforma findbyIdPlataforma(Integer id) throws InstanceNotFoundException, DataException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Plataforma> findAll() throws DataException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Plataforma> findByJuego(Integer idJuego) throws DataException, SQLException {
		boolean commit=false;
		Connection c=null;
		try {
		c=ConnectionManager.getConnection();
		c.setAutoCommit(false);
		
		List<Plataforma> p=pdao.findByJuego(c, idJuego);
		
		return p;
		
		}catch(SQLException e) {
			e.printStackTrace();
			throw e;
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
	}

}
