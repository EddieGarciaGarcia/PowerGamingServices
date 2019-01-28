package com.eddie.ecommerce.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.eddie.ecommerce.dao.ItemBibliotecaDAO;
import com.eddie.ecommerce.dao.JuegoDAO;
import com.eddie.ecommerce.dao.Utils.ConnectionManager;
import com.eddie.ecommerce.dao.Utils.JDBCUtils;
import com.eddie.ecommerce.dao.impl.ItemBibliotecaDAOImpl;
import com.eddie.ecommerce.dao.impl.JuegoDAOImpl;
import com.eddie.ecommerce.model.ItemBiblioteca;
import com.eddie.ecommerce.model.Juego;
import com.eddie.ecommerce.model.JuegoCriteria;
import com.eddie.ecommerce.service.JuegoService;

public class JuegoServiceImpl implements JuegoService{

	private JuegoDAO jdao=null;
	private ItemBibliotecaDAO ibDao=null;
	
	public JuegoServiceImpl() {
		jdao=new JuegoDAOImpl();
		ibDao=new ItemBibliotecaDAOImpl();
	}
	
	@Override
	public List<Juego> findByJuegoCriteria(JuegoCriteria c, String idioma) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Juego> findAllByDate() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Juego> findAllByValoración() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Juego findById(Integer id,String idioma) throws Exception {
		boolean commit=false;
		Connection c=null;
		try {
		c=ConnectionManager.getConnection();
		c.setAutoCommit(false);
		
		
		Juego j = jdao.findById(c,id, idioma);
		
		return j;
		
		}catch(SQLException e) {
			e.printStackTrace();
			throw e;
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
	}

	@Override
	public Juego create(Juego j) throws Exception {
		boolean commit=false;
		Connection c=null;
		try {
		c=ConnectionManager.getConnection();
		c.setAutoCommit(false);
		
		j = jdao.create(c,j);

		commit=true;
		
		return j;
		
		}catch(SQLException e) {
			e.printStackTrace();
			throw e;
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
	}

	@Override
	public boolean update(Juego j) throws Exception {
		boolean commit=false;
		Connection c=null;
		try {
	          
            c = ConnectionManager.getConnection();

            c.setAutoCommit(false);

            jdao.update(c,j);
            commit = true;
            
        } catch (SQLException e) {
            throw new Exception(e);

        } finally {
        	JDBCUtils.closeConnection(c, commit);
        }
		return true;
	}

	@Override
	public void delete(Integer id) throws Exception {
		Connection connection = null;
        boolean commit = false;

        try {
          
            connection = ConnectionManager.getConnection();

            connection.setAutoCommit(false);

            jdao.delete(connection, id);          
                       
        } catch (SQLException e) {
            throw new Exception(e);

        } finally {
        	JDBCUtils.closeConnection(connection, commit);
        }		
		
	}

	@Override
	public boolean addComent(ItemBiblioteca it) throws Exception {
		boolean commit=false;
		Connection c=null;
		try {
		c=ConnectionManager.getConnection();
		c.setAutoCommit(false);
		
		it.setPuntuacion(0);
		it.setComprado("No");
		ibDao.create(c, it);
		
		commit=true;
		
		}catch(SQLException e) {
			e.printStackTrace();
			throw e;
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
		return true;
	}

	@Override
	public boolean borrarComent(ItemBiblioteca it) throws Exception {
		boolean commit=false;
		Connection c=null;
		try {
		c=ConnectionManager.getConnection();
		c.setAutoCommit(false);
		
		it.setFechaComentario(null);
		it.setComentario(null);
		it.setPuntuacion(0);
		it.setComprado("No");
		ibDao.update(c, it);
		
		commit=true;
		
		}catch(SQLException e) {
			e.printStackTrace();
			throw e;
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
		return true;
	}


}
