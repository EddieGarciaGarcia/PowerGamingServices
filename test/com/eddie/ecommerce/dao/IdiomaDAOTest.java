package com.eddie.ecommerce.dao;

import java.sql.Connection;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.eddie.ecommerce.dao.Utils.ConnectionManager;
import com.eddie.ecommerce.dao.impl.IdiomaDAOImpl;
import com.eddie.ecommerce.model.Categoria;
import com.eddie.ecommerce.model.Idioma;
import com.eddie.ecommerce.service.impl.IdiomaServiceImpl;

public class IdiomaDAOTest {
	private IdiomaDAOImpl daoI=null;
	private static Logger logger=LogManager.getLogger(IdiomaDAOTest.class);
	
	public IdiomaDAOTest() {
		
		daoI=new IdiomaDAOImpl();
		
		
	}

		public void testfindByIdioma() { 
			try {
				Connection c= ConnectionManager.getConnection();
				Idioma idiomas= daoI.findById(c,"ESP","ES");
				logger.debug(idiomas.getNombre());
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public void testfindAll() {
			try {
				Connection c= ConnectionManager.getConnection();
				List<Idioma> idiomas;
				idiomas = daoI.findAll(c,"ES");
				for(int i=0;i<idiomas.size();i++){
					logger.debug(idiomas.get(i).getNombre());
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		public void findByJuego() {
			try {
				Connection c= ConnectionManager.getConnection();
				List<Idioma> idiomas;
				idiomas = daoI.findByJuego(c, 2, "ES");
				for(int i=0;i<idiomas.size();i++){
					logger.debug(idiomas.get(i).getNombre());
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	public static void main(String[] args) {
		
		IdiomaDAOTest test = new IdiomaDAOTest();
				test.findByJuego();
				//test.testfindAll();
				//test.testfindByIdioma();
			

	}
}
