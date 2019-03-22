package com.eddie.ecommerce.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
import com.eddie.ecommerce.service.Resultados;
import com.eddie.ecommerce.service.impl.IdiomaServiceImpl;

public class JuegoDAOTest {
	private JuegoDAOImpl daoJ=null;
	private CategoriaDAOImpl daoC=null;
	private static Logger logger=LogManager.getLogger(JuegoDAOTest.class);
	public JuegoDAOTest() {
		daoJ=new JuegoDAOImpl();
		daoC=new CategoriaDAOImpl();
	}
	public void testfindAllJuego() {
		try {
			Connection c= ConnectionManager.getConnection();
			List<Juego> juegos;
			juegos =daoJ.findAllByDate(c,"ES", 0, 0);
			for(Juego j : juegos){
				logger.debug(j.getNombre());
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
			juegos =(List<Juego>) daoJ.findByJuegoCriteria(ju, "ES", c, 0, 0);
			for(Juego j:juegos) {
				for(Categoria categoria: j.getCategoria()) {
					categoria.getNombre();
				}
			}
			for(int i =0;i<juegos.size();i++) {
				logger.debug(juegos.get(i).getNombre());
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
			logger.debug(juegos.getNombre()+","+juegos.getIdiomas().get(0).getNombre()+",Fecha "+juegos.getFechaLanzamiento());
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
			logger.debug("Juego creado: "+juegos.getNombre()+", "+juegos.getIdCreador()+", "+juegos.getFechaLanzamiento());
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
				logger.debug(j.getIdJuego()+",Fecha "
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
			juegos =(List<Juego>) daoJ.findAllByDate(c,"ES", 0, 0);
			
			for(Juego j : juegos){
				logger.debug(j.getIdJuego()+","+j.getFechaLanzamiento());
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void testFindByIDs() {
		List<Integer> ids= new ArrayList<Integer>();
		ids.add(1);
		ids.add(4);
		ids.add(6);
		try {
			Connection c= ConnectionManager.getConnection();
			List<Juego> juegos=daoJ.findByIDs(c, ids, "ES");
			
			for(Juego j : juegos){
				logger.debug(j.getIdJuego()+","+j.getNombre());
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
		//test.testfindbyCriteria();
		test.testFindByIDs();
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
