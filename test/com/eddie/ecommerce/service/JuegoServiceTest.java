package com.eddie.ecommerce.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.eddie.ecommerce.exceptions.DataException;
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
	private static Logger logger = LogManager.getLogger(JuegoServiceTest.class.getName());
	public void testFindByDate() {
		
			int tamanhoPagina=2;
			Resultados<Juego> juegos;
			int startIndex=1;
			int i=1;
			try {
				juegos =serviceJ.findAllByDate("ES",startIndex,tamanhoPagina);
				do {
					juegos = serviceJ.findAllByDate("ES",startIndex, tamanhoPagina);
					logger.info("Found "+juegos.getTotal()+" results.");				
					if (juegos.getResultados().size()>0) {
						logger.info("Page ["+startIndex+" - "+(startIndex+juegos.getResultados().size()-1)+"] : ");				
						for (Juego t: juegos.getResultados()) {
							logger.info("Result "+i+": "+t.toString());
							i++;
						}
						startIndex = startIndex + tamanhoPagina;
					}

				} while (!(juegos.getResultados().size()<tamanhoPagina)); 	
			} catch (DataException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	

	
	public void testfindbyCriteria() {
		
			JuegoCriteria ju=new JuegoCriteria();
			
			Categoria ca=new Categoria();
			Categoria cate=new Categoria();
			Categoria categ=new Categoria();
			List<Categoria> cat=new ArrayList();
//			ca.setIdCategria(3);
//			cat.add(ca);
			
			ca.setIdCategria(7);
			cate.setIdCategria(8);
			categ.setIdCategria(9);
			Idioma idiom=new Idioma();
//			idiom.setIdIdioma("ESP");
			List<Idioma>idiomas=new ArrayList();
//			idiomas.add(idiom);
			Plataforma plataf=new Plataforma();
//			plataf.setIdPlatadorma(1);
			List<Plataforma> plataforma=new ArrayList();
//			plataforma.add(plataf);
//			cat.add(ca);
//			cat.add(cate);
//			cat.add(categ);
			ju.setCategoria(cat);
			ju.setFechaLanzamiento(2001);
			
			
			List<Juego> juegos;
			/*try {
				juegos =serviceJ.findByJuegoCriteria(ju, "ES");
				for(Juego j:juegos) {
					logger.info(j.getNombre());
					
				}
				
			} catch (DataException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
	
	}
	public void testfindid() {
		
			Juego juegos;
			try {
				juegos =serviceJ.findById(12, "ES");
				logger.info(juegos.getNombre()+","+juegos.getIdiomas().get(0).getNombre()+",Fecha "+juegos.getFechaLanzamiento());
				
			} catch (DataException  e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}
	
	/*public void create() throws DuplicateInstanceException, DataException {
		

			
			Juego juegos=new Juego("prueba",new Date(04-06-2000),2);
			try {
				juegos =serviceJ.create(juegos);
				logger.info("Juego creado: "+juegos.getNombre()+", "+juegos.getIdCreador()+", "+juegos.getFechaLanzamiento());
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}*/
	
	public void testfindValoracion() {
		
			List<Juego> juegos;
			try {
				juegos =serviceJ.findAllByValoracion("ES");
				for(Juego j : juegos){
					logger.info(j.getIdJuego()+",Fecha "
				    		+ ""+j.getFechaLanzamiento());
				}
			} catch (DataException  e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}
	public void testfindAllJuego() {
		
			/*List<Juego> juegos;
			try {
				juegos =serviceJ.findAllByDate("ES");
				for(Juego j : juegos){
				    logger.info(j.getIdJuego()+","+j.getFechaLanzamiento());
				}
			} catch (DataException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
				
	}
	public static void main(String[] args) {
		JuegoServiceTest test = new JuegoServiceTest();
		//test.testFindByDate();
		test.testfindValoracion();
		
		Properties systemProperties= System.getProperties();
		
		String key=null;
		for(Enumeration keys= systemProperties.keys(); keys.hasMoreElements();){
			key= (String) keys.nextElement();
			System.out.println(key+"="+ System.getProperty(key));
		}
		
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
