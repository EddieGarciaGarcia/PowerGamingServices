package com.eddie.training.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.eddie.training.dao.UsuarioDAO;
import com.eddie.training.dao.Utils.ConnectionManager;
import com.eddie.training.dao.Utils.JDBCUtils;
import com.eddie.training.dao.impl.UsuarioDAOImpl;
import com.eddie.training.model.Usuario;
import com.eddie.training.service.MailService;
import com.eddie.training.service.UsuarioService;

public class UsuarioServiceImpl implements UsuarioService{

	private UsuarioDAO udao=null;
	private MailService mail=null;
	
	public UsuarioServiceImpl() {
		udao=new UsuarioDAOImpl();
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
	public long delete(Long  id) throws Exception {

	    Connection connection = null;
        boolean commit = false;

        try {
          
            connection = ConnectionManager.getConnection();

            connection.setAutoCommit(false);

            long result = udao.delete(id, connection);            
            commit = true;            
            return result;
            
        } catch (SQLException e) {
            throw new Exception(e);

        } finally {
        	JDBCUtils.closeConnection(connection, commit);
        }		
	
	}

	@Override
	public Usuario findById(Integer id) throws Exception {
		boolean commit=false;
		Connection c=null;
		try {
		c=ConnectionManager.getConnection();
		c.setAutoCommit(true);
		
		Usuario u = udao.findById(id,c);
		
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
	public List<Usuario> findById(String idioma, String nombre) throws Exception {
		boolean commit=false;
		Connection c=null;
		
		
		return null;
	}

}
