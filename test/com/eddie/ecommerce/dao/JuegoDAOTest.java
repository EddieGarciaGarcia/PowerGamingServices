package com.eddie.ecommerce.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.eddie.ecommerce.dao.Utils.ConnectionManager;
import com.eddie.ecommerce.dao.impl.JuegoDAOImpl;
import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.exceptions.DuplicateInstanceException;
import com.eddie.ecommerce.model.Juego;
import com.eddie.ecommerce.model.JuegoCriteria;

public class JuegoDAOTest {
	private JuegoDAOImpl daoJ=null;
	public JuegoDAOTest() {
		daoJ=new JuegoDAOImpl();
	}
	
	public void testfindAllJuego() {
		try {
			Connection c= ConnectionManager.getConnection();
			List<Juego> juegos;
			juegos =daoJ.findAllByDate(c);
			for(Juego j : juegos){
			    System.out.println(j.getNombre());
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void testfindAllJuegoCriteria() {
		try {
			Connection c= ConnectionManager.getConnection();
			JuegoCriteria ju=new JuegoCriteria();
			List<Juego> juegos;
			juegos =daoJ.findByJuegoCriteria(ju, "ES", c);
			for(Juego j : juegos){
			    System.out.println(j.getNombre());
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
			juegos =daoJ.findById(c,5);
			System.out.println(juegos.getNombre());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void create() throws DuplicateInstanceException, DataException {
		try {
			Connection c= ConnectionManager.getConnection();
			Juego juegos=new Juego("prueba",new Date(),2);
			juegos =daoJ.create(c, juegos);
			System.out.println("Juego creado: "+juegos.getNombre()+", "+juegos.getId_creador()+", "+juegos.getFechaLanzamiento());
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public void testfindValoracion() {
		try {
			Connection c= ConnectionManager.getConnection();
			List<Juego> juegos;
			juegos =daoJ.findAllByValoración(c);
			
			for(Juego j : juegos){
			    System.out.println(j.getIdJuego());
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public static void main(String[] args) {
		JuegoDAOTest test = new JuegoDAOTest();
		//test.testfindValoracion();
		/*
		try {
			
			test.create();
		} catch (DuplicateInstanceException e) {
			e.printStackTrace();
		} catch (DataException e) {
			e.printStackTrace();
		}
		*/
		//test.testfindAllJuego();
		//test.testfindid();
	}

}
