package com.eddie.ecommerce.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.eddie.ecommerce.dao.DireccionDAO;
import com.eddie.ecommerce.dao.ItemBibliotecaDAO;
import com.eddie.ecommerce.dao.UsuarioDAO;
import com.eddie.ecommerce.dao.Utils.ConnectionManager;
import com.eddie.ecommerce.dao.Utils.JDBCUtils;
import com.eddie.ecommerce.dao.impl.DireccionDAOImpl;
import com.eddie.ecommerce.dao.impl.ItemBibliotecaDAOImpl;
import com.eddie.ecommerce.dao.impl.UsuarioDAOImpl;
import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.exceptions.DuplicateInstanceException;
import com.eddie.ecommerce.exceptions.InstanceNotFoundException;
import com.eddie.ecommerce.model.Direccion;
import com.eddie.ecommerce.model.ItemBiblioteca;
import com.eddie.ecommerce.model.Usuario;
import com.eddie.ecommerce.service.MailService;
import com.eddie.ecommerce.service.UsuarioService;


public class UsuarioServiceImpl implements UsuarioService{

	private UsuarioDAO udao=null;
	private ItemBibliotecaDAO ibDao=null;
	private DireccionDAO ddao=null;
	
	public UsuarioServiceImpl() {
		udao=new UsuarioDAOImpl();
		ibDao=new ItemBibliotecaDAOImpl();
		ddao=new DireccionDAOImpl();
	}
	
	@Override
	public Usuario create(Usuario u) throws Exception {
		boolean commit=false;
		Connection c=null;
		try {
		c=ConnectionManager.getConnection();
		c.setAutoCommit(false);
		
		Usuario u2 = udao.create(u,c);
		MailService mail=new MailServiceImpl();
		mail.sendMail(u.getEmail(), "Bienvenido a mi página web","Hola muy buenas te has registrado correctamente");
		
		commit=true;
		
		return u2;
		
		}catch(SQLException e) {
			e.printStackTrace();
			throw e;
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
	}

	@Override
	public void update(Usuario u) throws Exception {
		boolean commit=false;
		Connection c=null;
		try {
	          
            c = ConnectionManager.getConnection();

            c.setAutoCommit(false);

            udao.update(u, c);
            commit = true;
            
        } catch (SQLException e) {
            throw new Exception(e);

        } finally {
        	JDBCUtils.closeConnection(c, commit);
        }
		
	}

	@Override
	public long delete(String  email) throws DataException {

	    Connection connection = null;
        boolean commit = false;

        try {
          
            connection = ConnectionManager.getConnection();

            connection.setAutoCommit(false);

            long result = udao.delete(email, connection);            
                     commit=true;  
            return result;
            
        } catch (SQLException e) {
            throw new DataException(e);

        } finally {
        	JDBCUtils.closeConnection(connection, commit);
        }		
	
	}

	@Override
	public Usuario findById(String email) throws DataException, SQLException {
		boolean commit=false;
		Connection c=null;
		try {
		c=ConnectionManager.getConnection();
		c.setAutoCommit(false);
		
		
		Usuario u = udao.findById(email,c);
		
		
		return u;
		
		}catch(DataException e) {
			e.printStackTrace();
			throw e;
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
	}

	@Override
	public Usuario login(String email, String password) throws DataException, SQLException {
		if(email == null) {
			return null;
		}
		if(password == null){
			return null;
		}
		Connection c=ConnectionManager.getConnection();
		Usuario u = udao.findById(email, c);
		if(u==null) {
			return null;
		}
		if(u.getPassword().equals(password)) {
			System.out.println("Usuario"+u.getEmail()+" autenticado");
			return u;
		}
		return null;
		
		
	}

	@Override
	public List<ItemBiblioteca> findByUsuario(String email) throws DataException, SQLException {
		boolean commit=false;
		Connection c=null;
		try {
		c=ConnectionManager.getConnection();
		c.setAutoCommit(false);
		
		List<ItemBiblioteca> biblio=ibDao.findByUsuario(c, email);
		
		return biblio;
		
		}catch(SQLException e) {
			e.printStackTrace();
			throw e;
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
	}

	@Override
	public ItemBiblioteca addJuegoBiblioteca(ItemBiblioteca b) throws DataException, SQLException {
		boolean commit=false;
		Connection c=null;
		try {
		c=ConnectionManager.getConnection();
		c.setAutoCommit(false);
		
		b.setFechaComentario(null);
		b.setComentario(null);
		b.setPuntuacion(0);
		b = ibDao.create(c, b);
		
		commit=true;
		
		return b;
		
		}catch(SQLException e) {
			e.printStackTrace();
			throw e;
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
	}

	@Override
	public long borrarJuegoBiblioteca(String email, Integer idJuego) throws DataException, SQLException {
		boolean commit=false;
		Connection c=null;
		try {
		c=ConnectionManager.getConnection();
		c.setAutoCommit(false);
		
		
		ibDao.delete(c, email, idJuego);
		
		commit=true;
		
		return idJuego;
		
		}catch(SQLException e) {
			e.printStackTrace();
			throw e;
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
		
	}

	@Override
	public Direccion findByIdDireccion(String email) throws SQLException, InstanceNotFoundException, DataException {
		boolean commit=false;
		Connection c=null;
		try {
		c=ConnectionManager.getConnection();
		c.setAutoCommit(false);
		
		
		Direccion d = ddao.findById(c,email);
		
		
		return d;
		
		}catch(DataException e) {
			e.printStackTrace();
			throw e;
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
	}

	@Override
	public Direccion createDireccion(Direccion d) throws SQLException, DuplicateInstanceException, DataException {
		boolean commit=false;
		Connection c=null;
		try {
		c=ConnectionManager.getConnection();
		c.setAutoCommit(false);
		
		
		d = ddao.create(c, d);
		
		commit=true;
		
		return d;
		
		}catch(SQLException e) {
			e.printStackTrace();
			throw e;
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
	}

	@Override
	public boolean updateDireccion(Direccion d) throws SQLException, InstanceNotFoundException, DataException {
		boolean commit=false;
		Connection c=null;
		try {
	          
            c = ConnectionManager.getConnection();

            c.setAutoCommit(false);

            ddao.update(c, d);
            commit = true;
            
        } catch (SQLException e) {
            throw new DataException(e);

        } finally {
        	JDBCUtils.closeConnection(c, commit);
        }
		return true;
	}

	@Override
	public void deleteDireccion(String email) throws SQLException, DataException {
		boolean commit=false;
		Connection c=null;
		try {
		c=ConnectionManager.getConnection();
		c.setAutoCommit(false);

		ddao.delete(c, email);
		
		commit=true;
		}catch(SQLException e) {
			e.printStackTrace();
			throw e;
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
		
	}


}
