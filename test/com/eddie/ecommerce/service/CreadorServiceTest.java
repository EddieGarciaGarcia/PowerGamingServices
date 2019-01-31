package com.eddie.ecommerce.service;

import java.util.List;
import com.eddie.ecommerce.model.Creador;
import com.eddie.ecommerce.service.impl.CreadorServiceImpl;

public class CreadorServiceTest {
	
	private CreadorService serviceC=null;
	
	
	public CreadorServiceTest() {
		
		serviceC=new CreadorServiceImpl();
		
		
	}

		public void testfindByCreador() { 
			try {			
				Creador cr= serviceC.findbyIdCreador(5);
				System.out.println(cr.getNombre());
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public void testfindAll() {
			try {
				List<Creador> creador;
				creador = serviceC.findAll();
				for(int i=0;i<creador.size();i++){
					System.out.println(creador.get(i).getNombre());
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	public static void main(String[] args) {
		CreadorServiceTest test = new CreadorServiceTest();

		test.testfindAll();
		//test.testfindByCreador();

	}
}
