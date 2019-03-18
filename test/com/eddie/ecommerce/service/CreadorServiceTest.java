package com.eddie.ecommerce.service;

import java.sql.SQLException;
import java.util.List;

import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.model.Creador;
import com.eddie.ecommerce.service.impl.CreadorServiceImpl;

public class CreadorServiceTest {
	
	private CreadorService serviceC=null;
	
	
	public CreadorServiceTest() {
		
		serviceC=new CreadorServiceImpl();
		
		
	}

		public void testfindByCreador() { 
				
				Creador cr;
				try {
					cr = serviceC.findbyIdCreador(5);
					System.out.println(cr.getNombre());
				} catch (DataException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
		public void testfindAll() {
			
				List<Creador> creador;
				try {
					creador = serviceC.findAll();
					for(int i=0;i<creador.size();i++){
						System.out.println(creador.get(i).getNombre());
					}
				} catch (DataException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			
			
		}
	public static void main(String[] args) {
		CreadorServiceTest test = new CreadorServiceTest();

		test.testfindAll();
		test.testfindAll();
		//test.testfindByCreador();

	}
}
