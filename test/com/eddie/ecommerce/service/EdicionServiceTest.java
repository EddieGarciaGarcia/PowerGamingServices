package com.eddie.ecommerce.service;

import java.sql.SQLException;
import java.util.List;

import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.model.Edicion;
import com.eddie.ecommerce.service.impl.EdicionServiceImpl;

public class EdicionServiceTest {

	private EdicionService serviceE=null;
	
	
	public EdicionServiceTest() {
		
		serviceE=new EdicionServiceImpl();
		
		
	}

		public void testfindAllByJuego() {
			
				List<Edicion> edicion;
				try {
					edicion = serviceE.findByIdJuego(1);
					for(int i=0;i<edicion.size();i++){
						System.out.println(edicion.get(i).getIdFormato());
					}
				} catch (DataException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			
			
		}
		
	public static void main(String[] args) {
		EdicionServiceTest test = new EdicionServiceTest();

		test.testfindAllByJuego();
		

	}

}
