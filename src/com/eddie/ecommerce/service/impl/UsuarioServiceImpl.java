package com.eddie.ecommerce.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.eddie.ecommerce.dao.DireccionDAO;
import com.eddie.ecommerce.dao.ItemBibliotecaDAO;
import com.eddie.ecommerce.dao.UsuarioDAO;
import com.eddie.ecommerce.dao.Utils.ConnectionManager;
import com.eddie.ecommerce.dao.Utils.JDBCUtils;
import com.eddie.ecommerce.dao.Utils.PasswordEncryptionUtil;
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
import com.eddie.ecommerce.service.Resultados;
import com.eddie.ecommerce.service.UsuarioService;

public class UsuarioServiceImpl implements UsuarioService{
	
	private static Logger logger=LogManager.getLogger(UsuarioServiceImpl.class);

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
		
		if(logger.isDebugEnabled()) {
			logger.debug("Usuario = "+u.toString());
		}
		
		boolean commit=false;
		Connection c=null;
		try {
		c=ConnectionManager.getConnection();
		c.setAutoCommit(false);
		
		Usuario u2 = udao.create(u,c);
		MailService mail=new MailServiceImpl();
		mail.sendMail(u.getEmail(), "Bienvenido a mi página web","<html><h1>Bienvenido a Power Gaming</h1><p>Hola muy buenas te has registrado correctamente</p></html>");
		
		commit=true;
		return u2;
		
		}catch(SQLException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
	}

	@Override
	public void update(Usuario u) throws DataException {
		
		if(logger.isDebugEnabled()) {
			logger.debug("Usuario = "+u.toString());
		}
		
		boolean commit=false;
		Connection c=null;
		try {
	          
            c = ConnectionManager.getConnection();

            c.setAutoCommit(false);

            udao.update(u, c);
            commit = true;
            
        } catch (SQLException e) {
        	logger.error(e.getMessage(),e);
        } finally {
        	JDBCUtils.closeConnection(c, commit);
        }
		
	}

	@Override
	public long delete(String  email) throws DataException {

		if(logger.isDebugEnabled()) {
			logger.debug("Email = "+email);
		}
		
	    Connection connection = null;
        boolean commit = false;

        try {
          
            connection = ConnectionManager.getConnection();

            connection.setAutoCommit(false);

            long result = udao.delete(email, connection);            
                     commit=true;  
            return result;
            
        } catch (SQLException e) {
        	logger.error(e.getMessage(),e);
            throw new DataException(e);
        } finally {
        	JDBCUtils.closeConnection(connection, commit);
        }		
	
	}

	@Override
	public Usuario findById(String email) throws DataException, SQLException {
		
		if(logger.isDebugEnabled()) {
			logger.debug("Email = "+email);
		}
		
		boolean commit=false;
		Connection c=null;
		try {
		c=ConnectionManager.getConnection();
		c.setAutoCommit(false);
		
		
		Usuario u = udao.findById(email,c);
		
		
		return u;
		
		}catch(DataException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
	}

	@Override
	public Usuario login(String email, String password) throws DataException, SQLException {
		
		if(logger.isDebugEnabled()) {
			logger.debug("Email = "+email);
		}
		
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
		if(PasswordEncryptionUtil.checkPassword(password, u.getPassword())) {
			if(logger.isDebugEnabled()) {
				logger.debug("Usuario"+u.getEmail()+" autenticado");
			}
			return u;
		}
		return null;
		
		
	}

	@Override
	public Resultados<ItemBiblioteca> findByUsuario(String email, int startIndex, int count) throws DataException, SQLException {
		
		if(logger.isDebugEnabled()) {
			logger.debug("Email = "+email);
		}
		
		boolean commit=false;
		Connection c=null;
		try {
		c=ConnectionManager.getConnection();
		c.setAutoCommit(false);
		
		Resultados<ItemBiblioteca> biblio=ibDao.findByUsuario(c, email, startIndex, count);
		
		return biblio;
		
		}catch(SQLException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
	}

	@Override
	public ItemBiblioteca addJuegoBiblioteca(ItemBiblioteca b) throws DataException, SQLException {
		
		if(logger.isDebugEnabled()) {
			logger.debug("Biblioteca = "+b.toString());
		}
		
		boolean commit=false;
		Connection c=null;
		try {
		c=ConnectionManager.getConnection();
		c.setAutoCommit(false);
		
		b.setComentario("N");
		b.setFechaComentario(null);
		b.setComentario(null);
		b.setPuntuacion(0);
		b = ibDao.create(c, b);
		
		commit=true;
		
		return b;
		
		}catch(SQLException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
	}

	@Override
	public long borrarJuegoBiblioteca(String email, Integer idJuego) throws DataException, SQLException {
		
		if(logger.isDebugEnabled()) {
			logger.debug("id= "+idJuego+" , email = "+email);
		}
		
		boolean commit=false;
		Connection c=null;
		try {
		c=ConnectionManager.getConnection();
		c.setAutoCommit(false);
		
		
		ibDao.delete(c, email, idJuego);
		
		commit=true;
		
		return idJuego;
		
		}catch(SQLException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
		
	}

	@Override
	public Direccion findByIdDireccion(String email) throws SQLException, InstanceNotFoundException, DataException {
		
		if(logger.isDebugEnabled()) {
			logger.debug("Email = "+email);
		}
		
		boolean commit=false;
		Connection c=null;
		try {
		c=ConnectionManager.getConnection();
		c.setAutoCommit(false);
		
		
		Direccion d = ddao.findById(c,email);
		
		
		return d;
		
		}catch(DataException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
	}

	@Override
	public Direccion createDireccion(Direccion d) throws SQLException, DuplicateInstanceException, DataException {
		
		if(logger.isDebugEnabled()) {
			logger.debug("Direccion = "+d.toString());
		}
		
		boolean commit=false;
		Connection c=null;
		try {
		c=ConnectionManager.getConnection();
		c.setAutoCommit(false);
		
		
		d = ddao.create(c, d);
		
		commit=true;
		
		return d;
		
		}catch(SQLException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
	}

	@Override
	public boolean updateDireccion(Direccion d) throws SQLException, InstanceNotFoundException, DataException {
		
		if(logger.isDebugEnabled()) {
			logger.debug("Direccion = "+d.toString());
		}
		
		boolean commit=false;
		Connection c=null;
		try {
	          
            c = ConnectionManager.getConnection();

            c.setAutoCommit(false);

            ddao.update(c, d);
            commit = true;
            
        } catch (SQLException e) {
        	logger.error(e.getMessage(),e);
            throw new DataException(e);

        } finally {
        	JDBCUtils.closeConnection(c, commit);
        }
		return true;
	}

	@Override
	public void deleteDireccion(String email) throws SQLException, DataException {
		
		if(logger.isDebugEnabled()) {
			logger.debug("Email = "+email);
		}
		
		boolean commit=false;
		Connection c=null;
		try {
		c=ConnectionManager.getConnection();
		c.setAutoCommit(false);

		ddao.delete(c, email);
		
		commit=true;
		}catch(SQLException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
		
	}

}
