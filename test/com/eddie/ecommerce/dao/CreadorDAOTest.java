package com.eddie.ecommerce.dao;

import java.sql.Connection;
import java.util.List;

import com.eddie.ecommerce.dao.Utils.ConnectionManager;
import com.eddie.ecommerce.dao.impl.CreadorDAOImpl;
import com.eddie.ecommerce.model.Creador;

public class CreadorDAOTest {
	private CreadorDAOImpl daoC=null;
	
	
	public CreadorDAOTest() {
		
		daoC=new CreadorDAOImpl();
		
		
	}

		public void testfindByCreador() { 
			try {
				Connection c= ConnectionManager.getConnection();
				Creador cr= daoC.findbyIdCreador(c,5);
				System.out.println(cr.getNombre());
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public void testfindAll() {
			try {
				Connection c= ConnectionManager.getConnection();
				List<Creador> creador;
				creador = daoC.findAll(c);
				for(int i=0;i<creador.size();i++){
					System.out.println(creador.get(i).getNombre());
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	public static void main(String[] args) {
		CreadorDAOTest test = new CreadorDAOTest();

		//test.testfindAll();
		test.testfindByCreador();

	}

}
