package com.eddie.ecommerce.service.impl;

import com.eddie.ecommerce.dao.DireccionDAO;
import com.eddie.ecommerce.dao.ItemBibliotecaDAO;
import com.eddie.ecommerce.dao.UsuarioDAO;
import com.eddie.ecommerce.dao.impl.DireccionDAOImpl;
import com.eddie.ecommerce.dao.impl.ItemBibliotecaDAOImpl;
import com.eddie.ecommerce.dao.impl.UsuarioDAOImpl;
import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.exceptions.DuplicateInstanceException;
import com.eddie.ecommerce.exceptions.InstanceNotFoundException;
import com.eddie.ecommerce.model.Direccion;
import com.eddie.ecommerce.model.ItemBiblioteca;
import com.eddie.ecommerce.model.Resultados;
import com.eddie.ecommerce.model.Usuario;
import com.eddie.ecommerce.service.MailService;
import com.eddie.ecommerce.service.UsuarioService;
import com.eddie.ecommerce.utils.ConnectionManager;
import com.eddie.ecommerce.utils.JDBCUtils;
import com.eddie.ecommerce.utils.PasswordEncryptionUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioServiceImpl implements UsuarioService{

	private static Logger logger=LogManager.getLogger(UsuarioServiceImpl.class);

	private UsuarioDAO usuarioDao=null;
	private ItemBibliotecaDAO itemBibliotecaDao=null;
	private DireccionDAO direccionDao=null;
	private MailService mailService=null;
	public UsuarioServiceImpl() {
		usuarioDao=new UsuarioDAOImpl();
		itemBibliotecaDao=new ItemBibliotecaDAOImpl();
		direccionDao=new DireccionDAOImpl();
		mailService=new MailServiceImpl();
	}

	@Override
	public boolean create(Usuario u) throws Exception {

		if(logger.isDebugEnabled()) {
			logger.debug("Usuario = "+u.toString());
		}
		boolean creado = false;
		boolean commit=false;
		Connection c=null;
		try {
			c= ConnectionManager.getConnection();
			c.setAutoCommit(false);

			creado = usuarioDao.create(c,u);
			if(creado) {
				mailService.sendMail(u.getEmail(), "Bienvenido a mi p�gina web", "<html><h1>Bienvenido a Power Gaming</h1><p>Hola muy buenas te has registrado correctamente</p></html>");
			}
			commit=true;
			return creado;

		}catch(SQLException e) {
			logger.error(e.getMessage(),e);
			throw e;
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
	}

	@Override
	public boolean update(Usuario u) throws DataException {

		if(logger.isDebugEnabled()) {
			logger.debug("Usuario = "+u.toString());
		}
		boolean actualizado = false;
		boolean commit=false;
		Connection c=null;
		try {

			c = ConnectionManager.getConnection();

			c.setAutoCommit(false);

			commit = true;

			Usuario usuario = findById(u.getEmail());

            if(usuario.getNombre().equals(u.getNombre())){
                u.setNombre(null);
            }else if(usuario.getApellido1().equals(u.getApellido1())){
                u.setApellido1(null);
            }else if (usuario.getApellido2().equals(u.getApellido2())){
                u.setApellido2(null);
            }else if(usuario.getGenero().equals(u.getGenero())){
                u.setGenero(null);
            }else if(usuario.getPassword().equals(u.getPassword())){
                u.setPassword(null);
            }else if(usuario.getTelefono().equals(u.getTelefono())){
                u.setTelefono(null);
            }else if(usuario.getNombreUser().equals(u.getNombreUser())){
                u.setNombreUser(null);
            }

			actualizado = usuarioDao.update(c,u);

		} catch (SQLException e) {
			logger.error(e.getMessage(),e);
		} finally {
			JDBCUtils.closeConnection(c, commit);
		}
		return actualizado;
	}

	@Override
	public boolean delete(String  email) throws DataException {

		if(logger.isDebugEnabled()) {
			logger.debug("Email = "+email);
		}
		boolean borrado = false;
		Connection connection = null;
		boolean commit = false;

		try {

			connection = ConnectionManager.getConnection();

			connection.setAutoCommit(false);

			borrado = usuarioDao.delete(connection,email);
			commit=true;  


		} catch (SQLException e) {
			logger.error(e.getMessage(),e);
			throw new DataException(e);
		} finally {
			JDBCUtils.closeConnection(connection, commit);
		}		
		return borrado;
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


			u = usuarioDao.findById(c,email);



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
		
		Connection c = null;
		Usuario u=null;
		
			if(email == null) {
				return null;
			}
			if(password == null){
				return null;
			}
			try {
			c = ConnectionManager.getConnection();
			c.setAutoCommit(true);
			u = usuarioDao.findById(c,email);
			
			} catch (SQLException e) {
				logger.debug(e);
			}finally {
				JDBCUtils.closeConnection(c);
			}
			if(u==null) {
				return u;
			}
			if(PasswordEncryptionUtil.checkPassword(password, u.getPassword())) {
				if(logger.isDebugEnabled()) {
					logger.debug("Usuario"+u.getEmail()+" autenticado");
				}
				return u;
			}else {
				return null;
			}
		

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
	public List<ItemBiblioteca> findByUsuario(String email) throws DataException {

		if(logger.isDebugEnabled()) {
			logger.debug("Email = "+email);
		}
		List<ItemBiblioteca> biblio=null;
		boolean commit=false;
		Connection c=null;
		try {
			c=ConnectionManager.getConnection();
			c.setAutoCommit(false);

			biblio=itemBibliotecaDao.findByUsuario(c, email);

		}catch(SQLException e) {
			logger.error(e.getMessage(),e);
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
		return biblio;
	}

	@Override
	public boolean addJuegoBiblioteca(String email,ItemBiblioteca b) throws DataException {

		if(logger.isDebugEnabled()) {
			logger.debug("Biblioteca = "+b.toString());
		}

		boolean existe= existsInBiblioteca(email,b.getIdJuego());

		if(existe) {
			// ou retornas null ou lanzas unha excepiont... 
		}
		boolean anhadido=false;
		boolean commit=false;
		Connection c=null;
		try {
			c=ConnectionManager.getConnection();
			c.setAutoCommit(false);

			b.setComentario("N");
			b.setFechaComentario(null);
			b.setComentario(null);
			b.setPuntuacion(0);

			commit=true;

			anhadido= itemBibliotecaDao.create(c, b);

		}catch(SQLException e) {
			logger.error(e.getMessage(),e);
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
		return anhadido;
	}

	@Override
	public boolean borrarJuegoBiblioteca(String email, Integer idJuego) throws DataException{

		if(logger.isDebugEnabled()) {
			logger.debug("id= "+idJuego+" , email = "+email);
		}
		boolean borrado = false;
		boolean commit=false;
		Connection c=null;
		try {
			c=ConnectionManager.getConnection();
			c.setAutoCommit(false);

			borrado = itemBibliotecaDao.delete(c, email, idJuego);

			commit=true;

		}catch(SQLException e) {
			logger.error(e.getMessage(),e);
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
		return borrado;

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
	public boolean createDireccion(Direccion d) throws DataException {

		if(logger.isDebugEnabled()) {
			logger.debug("Direccion = "+d.toString());
		}
		boolean creado = false;
		boolean commit=false;
		Connection c=null;
		try {
			c=ConnectionManager.getConnection();
			c.setAutoCommit(false);


			creado = direccionDao.create(c, d);

			commit=true;		


		}catch(SQLException e) {
			logger.error(e.getMessage(),e);
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
		return creado;
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
	public boolean deleteDireccion(String email) throws DataException {

		if(logger.isDebugEnabled()) {
			logger.debug("Email = "+email);
		}
		boolean borrado= false;
		boolean commit=false;
		Connection c=null;
		try {
			c=ConnectionManager.getConnection();
			c.setAutoCommit(false);

			borrado = direccionDao.delete(c, email);

			commit=true;
		}catch(SQLException e) {
			logger.error(e.getMessage(),e);
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
		return borrado;
	}

	@Override
	public List<Integer> existsInBiblioteca(String email, List<Integer> idsDeJuego) throws DataException {
		if(logger.isDebugEnabled()) {
			logger.debug("Email = "+email+", idJuego "+idsDeJuego);
		}
		boolean commit=false;
		Connection c=null;
		List<Integer> result=new ArrayList<Integer>();
		try {	
			c=ConnectionManager.getConnection();
			c.setAutoCommit(false);

			result=itemBibliotecaDao.exists(c, email, idsDeJuego);
			
			commit=true;
		}catch(SQLException e) {
			logger.error(e.getMessage(),e);
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
		return result;
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

	@Override
	public boolean create(ItemBiblioteca it) throws DataException {
		if(logger.isDebugEnabled()) {
			logger.debug("it = "+it.toString());
		}
		boolean creado = false;
		boolean commit=false;
		Connection c=null;
		try {

			c = ConnectionManager.getConnection();

			c.setAutoCommit(false);

			creado=itemBibliotecaDao.create(c, it);
			commit = true;

		} catch (SQLException e) {
			logger.error(e.getMessage(),e);
			throw new DataException(e);

		} finally {
			JDBCUtils.closeConnection(c, commit);
		}
		return creado;
	}

	@Override
	public ItemBiblioteca findByIdEmail(String email, Integer idJuego) throws DataException {
		if(logger.isDebugEnabled()) {
			logger.debug("Email = "+email+", idJuego "+idJuego);
		}
		boolean commit=false;
		Connection c=null;
		ItemBiblioteca result=null;
		try {	
			c=ConnectionManager.getConnection();
			c.setAutoCommit(false);

			result=itemBibliotecaDao.fingByIdEmail(c, email, idJuego);
			
			commit=true;
		}catch(SQLException e) {
			logger.error(e.getMessage(),e);
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
		return result;
	}


}
