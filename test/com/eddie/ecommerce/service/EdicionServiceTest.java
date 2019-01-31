package com.eddie.ecommerce.service;

import java.util.List;

import com.eddie.ecommerce.model.Edicion;
import com.eddie.ecommerce.service.impl.EdicionServiceImpl;

public class EdicionServiceTest {

	private EdicionService serviceE=null;
	
	
	public EdicionServiceTest() {
		
		serviceE=new EdicionServiceImpl();
		
		
	}

		public void testfindAllByJuego() {
			try {
				List<Edicion> edicion;
				edicion = serviceE.findByIdJuego(1);
				for(int i=0;i<edicion.size();i++){
					System.out.println(edicion.get(i).getIdFormato());
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	public static void main(String[] args) {
		EdicionServiceTest test = new EdicionServiceTest();

		test.testfindAllByJuego();
		

	}

}
