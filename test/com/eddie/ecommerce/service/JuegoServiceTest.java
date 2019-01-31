package com.eddie.ecommerce.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.eddie.ecommerce.dao.Utils.ConnectionManager;
import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.exceptions.DuplicateInstanceException;
import com.eddie.ecommerce.model.Categoria;
import com.eddie.ecommerce.model.Idioma;
import com.eddie.ecommerce.model.Juego;
import com.eddie.ecommerce.model.JuegoCriteria;
import com.eddie.ecommerce.model.Plataforma;
import com.eddie.ecommerce.service.impl.CategoriaServiceImpl;
import com.eddie.ecommerce.service.impl.JuegoServiceImpl;

public class JuegoServiceTest {
	private JuegoService serviceJ=null;
	private CategoriaService serviceC=null;
	public JuegoServiceTest() {
		serviceJ=new JuegoServiceImpl();
		serviceC=new CategoriaServiceImpl();
	}
	
	public void testfindAllJuego() {
		try {
			Connection c= ConnectionManager.getConnection();
			List<Juego> juegos;
			juegos =serviceJ.findAllByDate();
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
			JuegoCriteria ju=new JuegoCriteria();
			
			Categoria ca=new Categoria();
			Categoria cate=new Categoria();
			List<Categoria> cat=new ArrayList();
			ca.setIdCategria(3);
			cat.add(ca);
			cat.add(cate);
			
			List<Idioma>idiomas=new ArrayList();
			List<Plataforma> plataforma=new ArrayList();
			
			ju.setCategoria(cat);
			ju.setIdioma(idiomas);
			ju.setPlataforma(plataforma);
			
			List<Juego> juegos;
			juegos =serviceJ.findByJuegoCriteria(ju, "ES");
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
			Juego juegos;
			juegos =serviceJ.findById(1, "ES");
			System.out.println(juegos.getNombre()+","+juegos.getIdiomas().get(0).getNombre()+",Fecha "+juegos.getFechaLanzamiento());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
		
	}
	
	public void create() throws DuplicateInstanceException, DataException {
		try {
			Juego juegos=new Juego("prueba",new Date(),2);
			juegos =serviceJ.create(juegos);
			System.out.println("Juego creado: "+juegos.getNombre()+", "+juegos.getIdCreador()+", "+juegos.getFechaLanzamiento());
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public void testfindValoracion() {
		try {
			List<Juego> juegos;
			juegos =serviceJ.findAllByValoración();
			
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
			List<Juego> juegos;
			juegos =serviceJ.findAllByDate();
			
			for(Juego j : juegos){
			    System.out.println(j.getIdJuego()+","+j.getFechaLanzamiento());
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		JuegoServiceTest test = new JuegoServiceTest();
		test.testFindByDate();
		//test.testfindValoracion();
		//test.testfindbyCriteria();
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
