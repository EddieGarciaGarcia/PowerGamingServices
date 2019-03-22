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

	private UsuarioDAO usuarioDao=null;
	private ItemBibliotecaDAO itemBibliotecaDao=null;
	private DireccionDAO direccionDao=null;

	public UsuarioServiceImpl() {
		usuarioDao=new UsuarioDAOImpl();
		itemBibliotecaDao=new ItemBibliotecaDAOImpl();
		direccionDao=new DireccionDAOImpl();
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

			Usuario u2 = usuarioDao.create(u,c);
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

			usuarioDao.update(u, c);
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

			long result = usuarioDao.delete(email, connection);            
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
	public Usuario findById(String email) throws DataException{

		if(logger.isDebugEnabled()) {
			logger.debug("Email = "+email);
		}
		Usuario u =null;
		boolean commit=false;
		Connection c=null;
		try {
			c=ConnectionManager.getConnection();
			c.setAutoCommit(false);


			u = usuarioDao.findById(email,c);



		}catch(DataException e) {
			logger.error(e.getMessage(),e);
		} catch (SQLException e) {
			logger.debug(e);
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
		return u;
	}

	@Override
	public Usuario login(String email, String password) throws DataException{

		if(logger.isDebugEnabled()) {
			logger.debug("Email = "+email);
		}

		if(email == null) {
			return null;
		}
		if(password == null){
			return null;
		}
		Usuario u =null;
		Connection c;
		try {
			c = ConnectionManager.getConnection();
			u = usuarioDao.findById(email, c);
		} catch (SQLException e) {
			logger.debug(e);
		}
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
	public Resultados<ItemBiblioteca> findByUsuario(String email, int startIndex, int count) throws DataException {

		if(logger.isDebugEnabled()) {
			logger.debug("Email = "+email);
		}
		Resultados<ItemBiblioteca> biblio=null;
		boolean commit=false;
		Connection c=null;
		try {
			c=ConnectionManager.getConnection();
			c.setAutoCommit(false);

			biblio=itemBibliotecaDao.findByUsuario(c, email, startIndex, count);



		}catch(SQLException e) {
			logger.error(e.getMessage(),e);
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
		return biblio;
	}

	@Override
	public ItemBiblioteca addJuegoBiblioteca(String email,ItemBiblioteca b) throws DataException {

		if(logger.isDebugEnabled()) {
			logger.debug("Biblioteca = "+b.toString());
		}



		boolean existe= existsInBiblioteca(email,b.getIdJuego());

		if(existe) {
			// ou retornas null ou lanzas unha excepiont... 
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
			b = itemBibliotecaDao.create(c, b);

			commit=true;


		}catch(SQLException e) {
			logger.error(e.getMessage(),e);
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
		return b;
	}

	@Override
	public long borrarJuegoBiblioteca(String email, Integer idJuego) throws DataException{

		if(logger.isDebugEnabled()) {
			logger.debug("id= "+idJuego+" , email = "+email);
		}

		boolean commit=false;
		Connection c=null;
		try {
			c=ConnectionManager.getConnection();
			c.setAutoCommit(false);


			itemBibliotecaDao.delete(c, email, idJuego);

			commit=true;

			return idJuego;
		}catch(SQLException e) {
			logger.error(e.getMessage(),e);
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
		return idJuego;

	}

	@Override
	public Direccion findByIdDireccion(String email) throws InstanceNotFoundException, DataException {

		if(logger.isDebugEnabled()) {
			logger.debug("Email = "+email);
		}
		Direccion d =null;
		boolean commit=false;
		Connection c=null;
		try {
			c=ConnectionManager.getConnection();
			c.setAutoCommit(false);

			d = direccionDao.findById(c,email);


		}catch(DataException | SQLException e) {
			logger.error(e.getMessage(),e);
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
		return d;
	}

	@Override
	public Direccion createDireccion(Direccion d) throws DuplicateInstanceException, DataException {

		if(logger.isDebugEnabled()) {
			logger.debug("Direccion = "+d.toString());
		}

		boolean commit=false;
		Connection c=null;
		try {
			c=ConnectionManager.getConnection();
			c.setAutoCommit(false);


			d = direccionDao.create(c, d);

			commit=true;		


		}catch(SQLException e) {
			logger.error(e.getMessage(),e);
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
		return d;
	}

	@Override
	public boolean updateDireccion(Direccion d) throws InstanceNotFoundException, DataException {

		if(logger.isDebugEnabled()) {
			logger.debug("Direccion = "+d.toString());
		}

		boolean commit=false;
		Connection c=null;
		try {

			c = ConnectionManager.getConnection();

			c.setAutoCommit(false);

			direccionDao.update(c, d);
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
	public void deleteDireccion(String email) throws DataException {

		if(logger.isDebugEnabled()) {
			logger.debug("Email = "+email);
		}

		boolean commit=false;
		Connection c=null;
		try {
			c=ConnectionManager.getConnection();
			c.setAutoCommit(false);

			direccionDao.delete(c, email);

			commit=true;
		}catch(SQLException e) {
			logger.error(e.getMessage(),e);
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}

	}

	@Override
	public List<Integer> existsInBiblioteca(String email, List<Integer> idsDeJuego) throws DataException {



		return null;
	}

	@Override
	public boolean existsInBiblioteca(String email, Integer idJuego) throws DataException {

		if(logger.isDebugEnabled()) {
			logger.debug("Email = "+email+", idJuego "+idJuego);
		}
		boolean commit=false;
		Connection c=null;
		boolean result=false;
		try {	
			c=ConnectionManager.getConnection();
			c.setAutoCommit(false);

			result=itemBibliotecaDao.exists(c, email, idJuego);
			
			commit=true;
		}catch(SQLException e) {
			logger.error(e.getMessage(),e);
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
		return result;
	}


}
