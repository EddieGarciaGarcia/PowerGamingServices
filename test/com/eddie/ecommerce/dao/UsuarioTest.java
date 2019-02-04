package com.eddie.ecommerce.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import com.eddie.ecommerce.dao.Utils.ConnectionManager;
import com.eddie.ecommerce.dao.impl.UsuarioDAOImpl;
import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.model.Usuario;
import com.eddie.ecommerce.service.impl.UsuarioServiceImpl;

public class UsuarioTest {
	

	private UsuarioDAOImpl usuarioDAO=null;
	
	public UsuarioTest() {
		usuarioDAO= new UsuarioDAOImpl();
	}
	

	public void testBuscarUser() {
		try {
			Connection c=ConnectionManager.getConnection();
			Usuario u=usuarioDAO.findById("eddie_garcia@gmail.com",c);
			
			//Usuario u2=usuarioService.findById("eddie_garcia@gmail.com");
			System.out.println(u);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public void create() throws DataException {
		try {
			Connection c=ConnectionManager.getConnection();
			//Usuario prueba=new Usuario("prueba1","","hasd","asda","root","123134241","", new Date(), "has","H");
			//prueba=usuarioDAO.create(prueba,c);
			//System.out.println(prueba.toString());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void update() throws DataException {
		try {
			Connection c=ConnectionManager.getConnection();
			Usuario prueba=new Usuario(null,null,null,null,"546364356","prueba1@gmail.com", null, null,null);
			System.out.println(usuarioDAO.update(prueba,c));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void delete() throws DataException {
		try {
			Connection c=ConnectionManager.getConnection();
			System.out.println(usuarioDAO.delete("eddietuenti@gmail.com",c));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main (String args[]) {
		UsuarioTest testeo=new UsuarioTest();
		//testeo.testLogin();
		//testeo.testBuscarUser();
		try {
			//testeo.create();
			//testeo.update();
			testeo.delete();
		}catch(DataException e) {
			e.printStackTrace();
		}
		
		
	}
}
