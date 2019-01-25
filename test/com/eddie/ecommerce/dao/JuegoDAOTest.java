package com.eddie.ecommerce.dao;

import java.sql.Connection;
import java.util.List;

import com.eddie.ecommerce.dao.Utils.ConnectionManager;
import com.eddie.ecommerce.dao.impl.JuegoDAOImpl;
import com.eddie.ecommerce.model.Juego;

public class JuegoDAOTest {
	private JuegoDAOImpl daoJ=null;
	
	public JuegoDAOTest() {
		daoJ=new JuegoDAOImpl();
	}
	
	public void testfindAllJuego() {
		try {
			Connection c= ConnectionManager.getConnection();
			List<Juego> juegos;
			juegos =daoJ.findAllByDate();
			for(int i=0;i<juegos.size();i++){
				System.out.println(juegos.get(i));
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void testfindid() {
		try {
			Connection c= ConnectionManager.getConnection();
			Juego juegos;
			juegos =daoJ.findById(6);
			System.out.println(juegos.getNombre());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		JuegoDAOTest test = new JuegoDAOTest();
		
		test.testfindAllJuego();
		test.testfindid();
	}

}
