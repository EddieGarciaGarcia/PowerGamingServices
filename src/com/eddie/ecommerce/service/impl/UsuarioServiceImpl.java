package com.eddie.ecommerce.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.eddie.ecommerce.dao.ItemBibliotecaDAO;
import com.eddie.ecommerce.dao.UsuarioDAO;
import com.eddie.ecommerce.dao.Utils.ConnectionManager;
import com.eddie.ecommerce.dao.Utils.JDBCUtils;
import com.eddie.ecommerce.dao.impl.ItemBibliotecaDAOImpl;
import com.eddie.ecommerce.dao.impl.UsuarioDAOImpl;
import com.eddie.ecommerce.model.ItemBiblioteca;
import com.eddie.ecommerce.model.Usuario;
import com.eddie.ecommerce.service.MailService;

import com.eddie.ecommerce.service.UsuarioServiceDAO;

public class UsuarioServiceImpl implements UsuarioServiceDAO{

	private UsuarioDAO udao=null;
	private MailService mail=null;
	private ItemBibliotecaDAO ibDao=null;
	
	public UsuarioServiceImpl() {
		udao=new UsuarioDAOImpl();
		ibDao=new ItemBibliotecaDAOImpl();
	}
	
	@Override
	public Usuario create(Usuario u) throws Exception {
		boolean commit=false;
		Connection c=null;
		try {
		c=ConnectionManager.getConnection();
		c.setAutoCommit(false);
		
		u = udao.create(u,c);
		
		mail.sendMail("", "", "");
		
		commit=true;
		
		return u;
		
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
	public long delete(String  email) throws Exception {

	    Connection connection = null;
        boolean commit = false;

        try {
          
            connection = ConnectionManager.getConnection();

            connection.setAutoCommit(false);

            long result = udao.delete(email, connection);            
                       
            return result;
            
        } catch (SQLException e) {
            throw new Exception(e);

        } finally {
        	JDBCUtils.closeConnection(connection, commit);
        }		
	
	}

	@Override
	public Usuario findById(String email) throws Exception {
		boolean commit=false;
		Connection c=null;
		try {
		c=ConnectionManager.getConnection();
		c.setAutoCommit(false);
		
		
		Usuario u = udao.findById(email,c);
		
		return u;
		
		}catch(SQLException e) {
			e.printStackTrace();
			throw e;
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
	}

	//Metodo que no se si lo necesito
	@Override
	public List<Usuario> findById(String idioma, String nombre) throws Exception {
		return null;
	}

	@Override
	public Usuario login(String email, String password) throws Exception {
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
	public List<ItemBiblioteca> findByUsuario(String email) throws Exception {
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
	public ItemBiblioteca create(ItemBiblioteca b) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long delete(String email, Integer idJuego) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

}
