package com.eddie.ecommerce.service;

import java.sql.SQLException;
import java.util.List;

import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.model.Idioma;
import com.eddie.ecommerce.service.impl.IdiomaServiceImpl;

public class IdiomaServiceTest {

	private IdiomaService serviceI=null;
	
	
	public IdiomaServiceTest() {
		
		serviceI=new IdiomaServiceImpl();
		
		
	}

		public void testfindByIdioma() { 
			
				Idioma idiomas;
				try {
					idiomas = serviceI.findById("ESP","ES");
					System.out.println(idiomas.getNombre());
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
						System.out.println(idiomas.get(i).getNombre());
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
						System.out.println(idiomas.get(i).getNombre());
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
