package com.eddie.training.dao;

import java.sql.Connection;

import com.eddie.training.dao.Utils.ConnectionManager;
import com.eddie.training.dao.impl.UsuarioDAOImpl;
import com.eddie.training.model.Usuario;

import com.eddie.training.service.UsuarioServiceDAO;
import com.eddie.training.service.impl.UsuarioServiceImpl;

public class UsuarioTest {
	
	private UsuarioServiceDAO usuarioService=null;
	private UsuarioDAOImpl usuarioDAO=null;
	
	public UsuarioTest() {
		usuarioService=new UsuarioServiceImpl();
		usuarioDAO= new UsuarioDAOImpl();
	}
	
	public void testLogin() {
		try {
			Usuario u=usuarioService.login("eddie_garcia@gmail.com", "root");
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	
	
	public static void main (String args[]) {
		UsuarioTest testeo=new UsuarioTest();
		//testeo.testLogin();
		testeo.testBuscarUser();
		
		
	}
}
