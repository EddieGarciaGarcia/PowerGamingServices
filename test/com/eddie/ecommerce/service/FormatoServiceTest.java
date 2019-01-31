package com.eddie.ecommerce.service;

import java.sql.SQLException;
import java.util.List;

import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.model.Formato;
import com.eddie.ecommerce.service.impl.FormatoServiceImpl;

public class FormatoServiceTest {
	
	private FormatoService serviceF=null;
	
	
	public FormatoServiceTest() {
		
		serviceF=new FormatoServiceImpl();
		
	}

		public void testfindByFormato() { 
			
				Formato edicionesFormato;
				try {
					edicionesFormato = serviceF.findbyIdFormato(2,"ES");
					System.out.println(edicionesFormato.getNombre());
				} catch (DataException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			
		}
		public void testfindAll() {
			

				List<Formato> edicionesFormato;
				try {
					edicionesFormato = serviceF.findAll("ES");
					for(Formato f : edicionesFormato){
					    System.out.println(f.getNombre());
					}
				} catch (DataException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			
			
		}
	public static void main(String[] args) {
		FormatoServiceTest test = new FormatoServiceTest();

		test.testfindAll();
		//test.testfindByFormato();
	}

}
