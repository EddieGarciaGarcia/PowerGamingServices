package com.eddie.ecommerce.dao;

import java.sql.Connection;
import java.util.List;

import com.eddie.ecommerce.dao.Utils.ConnectionManager;
import com.eddie.ecommerce.dao.impl.FormatoDAOImpl;
import com.eddie.ecommerce.model.Formato;
import com.eddie.ecommerce.model.Juego;


public class FormatoDAOTest {

		private FormatoDAOImpl daoF=null;
		
		
		public FormatoDAOTest() {
			
			daoF=new FormatoDAOImpl();
			
			
		}

			public void testfindByFormato() { 
				try {
					Connection c= ConnectionManager.getConnection();
					Formato edicionesFormato= daoF.findbyIdFormato(c,2,"ES");
					System.out.println(edicionesFormato.getNombre());
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			public void testfindAll() {
				try {
					Connection c= ConnectionManager.getConnection();
					List<Formato> edicionesFormato;
					edicionesFormato = daoF.findAll(c,"ES");
					
					for(Formato f : edicionesFormato){
					    System.out.println(f.getNombre());
					}
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			

		public static void main(String[] args) {
			
				FormatoDAOTest test = new FormatoDAOTest();

					test.testfindAll();
					//test.testfindByFormato();
				
				

			
	}

}
