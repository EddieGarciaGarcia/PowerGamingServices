package com.eddie.ecommerce.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.eddie.ecommerce.dao.PaisDAOTest;
import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.model.Idioma;
import com.eddie.ecommerce.service.impl.IdiomaServiceImpl;

public class IdiomaServiceTest {

	private IdiomaService serviceI=null;
	private static Logger logger=LogManager.getLogger(IdiomaServiceTest.class);
	
	public IdiomaServiceTest() {
		
		serviceI=new IdiomaServiceImpl();
		
		
	}

		public void testfindByIdioma() { 
			
				Idioma idiomas;
				try {
					idiomas = serviceI.findById("ESP","ES");
					logger.debug(idiomas.getNombre());
				} catch (SQLException | DataException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			
		}
		public void testfindAll() {
			
				List<Idioma> idiomas;
				try {
					idiomas = serviceI.findAll("ES");
					for(int i=0;i<idiomas.size();i++){
						logger.debug(idiomas.get(i).getNombre());
					}
				} catch (SQLException | DataException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
		}
		public void findByJuego() {
			
				List<Idioma> idiomas;
				try {
					idiomas = serviceI.findByJuego(2, "ES");
					for(int i=0;i<idiomas.size();i++){
						logger.debug(idiomas.get(i).getNombre());
					}
					
				} catch (DataException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		}
	public static void main(String[] args) {
		IdiomaServiceTest test = new IdiomaServiceTest();
		//test.findByJuego();
		//test.testfindAll();
		//test.testfindByIdioma();

	}

}
