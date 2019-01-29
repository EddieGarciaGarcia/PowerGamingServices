package com.eddie.ecommerce.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.eddie.ecommerce.dao.EdicionDAO;
import com.eddie.ecommerce.dao.Utils.ConnectionManager;
import com.eddie.ecommerce.dao.Utils.JDBCUtils;
import com.eddie.ecommerce.dao.impl.EdicionDAOImpl;
import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.exceptions.DuplicateInstanceException;
import com.eddie.ecommerce.exceptions.InstanceNotFoundException;
import com.eddie.ecommerce.model.Edicion;
import com.eddie.ecommerce.service.EdicionService;

public class EdicionServiceImpl implements EdicionService{

	EdicionDAO edao=null;
	public EdicionServiceImpl() {
		edao= new EdicionDAOImpl();
	}
	
	@Override
	public List<Edicion> findByIdJuego(Integer id) throws DataException, SQLException {
		boolean commit=false;
		Connection c=null;
		try {
		c=ConnectionManager.getConnection();
		c.setAutoCommit(false);
		
		List<Edicion> ediciones=edao.findByIdJuego(c, id);
		
		return ediciones;
		
		}catch(SQLException e) {
			e.printStackTrace();
			throw e;
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
	}

	@Override
	public Edicion create(Edicion e) throws DuplicateInstanceException, DataException, SQLException {
		boolean commit=false;
		Connection c=null;
		try {
		c=ConnectionManager.getConnection();
		c.setAutoCommit(false);
		
		e = edao.create(c,e);

		commit=true;
		
		return e;
		
		}catch(SQLException ed) {
			ed.printStackTrace();
			throw ed;
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
	}

	@Override
	public boolean update(Edicion e) throws InstanceNotFoundException, DataException, SQLException {
		boolean commit=false;
		Connection c=null;
		try {
	          
            c = ConnectionManager.getConnection();

            c.setAutoCommit(false);

            edao.update(c,e);
            commit = true;
            
        } catch (SQLException ed) {
            throw new DataException(ed);

        } finally {
        	JDBCUtils.closeConnection(c, commit);
        }
		return true;
	}

}
