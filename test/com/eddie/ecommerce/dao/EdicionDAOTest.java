package com.eddie.ecommerce.dao;

import java.sql.Connection;
import java.util.List;

import com.eddie.ecommerce.dao.Utils.ConnectionManager;

import com.eddie.ecommerce.dao.impl.EdicionDAOImpl;

import com.eddie.ecommerce.model.Edicion;

public class EdicionDAOTest {
private EdicionDAOImpl daoE=null;
	
	
	public EdicionDAOTest() {
		
		daoE=new EdicionDAOImpl();
		
		
	}

		public void testfindAllByJuego() {
			try {
				Connection c= ConnectionManager.getConnection();
				List<Edicion> edicion;
				edicion = daoE.findByIdJuego(c, 1);
				for(int i=0;i<edicion.size();i++){
					System.out.println(edicion.get(i).getIdFormato());
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		public void testfindAllByEdicion() {
			try {
				Connection c= ConnectionManager.getConnection();
				Edicion edicion;
				edicion = daoE.findByIdEdicion(c, 1);
	
					System.out.println(edicion.getIdFormato());
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		

	public static void main(String[] args) {
		
		EdicionDAOTest test = new EdicionDAOTest();

				test.testfindAllByJuego();
				test.testfindAllByEdicion();
	}

}
