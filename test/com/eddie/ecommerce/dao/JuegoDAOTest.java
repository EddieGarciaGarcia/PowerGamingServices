package com.eddie.ecommerce.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.eddie.ecommerce.dao.Utils.ConnectionManager;
import com.eddie.ecommerce.dao.impl.CategoriaDAOImpl;
import com.eddie.ecommerce.dao.impl.JuegoDAOImpl;
import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.exceptions.DuplicateInstanceException;
import com.eddie.ecommerce.model.Categoria;
import com.eddie.ecommerce.model.Idioma;
import com.eddie.ecommerce.model.Juego;
import com.eddie.ecommerce.model.JuegoCriteria;
import com.eddie.ecommerce.model.Plataforma;

public class JuegoDAOTest {
	private JuegoDAOImpl daoJ=null;
	private CategoriaDAOImpl daoC=null;
	public JuegoDAOTest() {
		daoJ=new JuegoDAOImpl();
		daoC=new CategoriaDAOImpl();
	}
	
	public void testfindAllJuego() {
		try {
			Connection c= ConnectionManager.getConnection();
			List<Juego> juegos;
			juegos =daoJ.findAllByDate(c,"ES");
			for(Juego j : juegos){
			    System.out.println(j.getNombre());
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	
	public void testfindbyCriteria() {
		try {
			Connection c= ConnectionManager.getConnection();
			JuegoCriteria ju=new JuegoCriteria();
					
		
			
			List<Juego> juegos;
			juegos =daoJ.findByJuegoCriteria(ju, "ES", c);
			for(Juego j:juegos) {
				for(Categoria categoria: j.getCategoria()) {
					categoria.getNombre();
				}
			}
			for(int i =0;i<juegos.size();i++) {
				System.out.println(juegos.get(i).getNombre());
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
			juegos =daoJ.findById(c,1, "ES");
			System.out.println(juegos.getNombre()+","+juegos.getIdiomas().get(0).getNombre()+",Fecha "+juegos.getFechaLanzamiento());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
		
	}
	
	public void create() throws DuplicateInstanceException, DataException {
		try {
			Connection c= ConnectionManager.getConnection();
			Juego juegos=new Juego("prueba",1998,2);
			juegos =daoJ.create(c, juegos);
			System.out.println("Juego creado: "+juegos.getNombre()+", "+juegos.getIdCreador()+", "+juegos.getFechaLanzamiento());
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public void testfindValoracion() {
		try {
			Connection c= ConnectionManager.getConnection();
			List<Juego> juegos;
			juegos =daoJ.findAllByValoracion(c,"ES");
			
			for(Juego j : juegos){
			    System.out.println(j.getIdJuego()+",Fecha "
			    		+ ""+j.getFechaLanzamiento());
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void testFindByDate() {
		try {
			Connection c= ConnectionManager.getConnection();
			List<Juego> juegos;
			juegos =daoJ.findAllByDate(c,"ES");
			
			for(Juego j : juegos){
			    System.out.println(j.getIdJuego()+","+j.getFechaLanzamiento());
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public static void main(String[] args) {
		JuegoDAOTest test = new JuegoDAOTest();
		//test.testFindByDate();
		//test.testfindValoracion();
		test.testfindbyCriteria();
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
