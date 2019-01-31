package com.eddie.ecommerce.service;

import java.util.List;
import com.eddie.ecommerce.model.Idioma;
import com.eddie.ecommerce.service.impl.IdiomaServiceImpl;

public class IdiomaServiceTest {

	private IdiomaService serviceI=null;
	
	
	public IdiomaServiceTest() {
		
		serviceI=new IdiomaServiceImpl();
		
		
	}

		public void testfindByIdioma() { 
			try {
				Idioma idiomas= serviceI.findById("ESP","ES");
				System.out.println(idiomas.getNombre());
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public void testfindAll() {
			try {
				List<Idioma> idiomas;
				idiomas = serviceI.findAll("ES");
				for(int i=0;i<idiomas.size();i++){
					System.out.println(idiomas.get(i).getNombre());
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		public void findByJuego() {
			try {
				List<Idioma> idiomas;
				idiomas = serviceI.findByJuego(2, "ES");
				for(int i=0;i<idiomas.size();i++){
					System.out.println(idiomas.get(i).getNombre());
				}
				
			} catch (Exception e) {
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
