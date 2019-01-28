package com.eddie.ecommerce.dao;

import java.sql.Connection;
import java.util.List;

import com.eddie.ecommerce.dao.Utils.ConnectionManager;
import com.eddie.ecommerce.dao.impl.IdiomaDAOImpl;
import com.eddie.ecommerce.model.Categoria;
import com.eddie.ecommerce.model.Idioma;

public class IdiomaDAOTest {
	private IdiomaDAOImpl daoI=null;
	
	
	public IdiomaDAOTest() {
		
		daoI=new IdiomaDAOImpl();
		
		
	}

		public void testfindByIdioma() { 
			try {
				Connection c= ConnectionManager.getConnection();
				Idioma idiomas= daoI.findById(c,"ESP","ES");
				System.out.println(idiomas.getNombre());
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public void testfindAll() {
			try {
				Connection c= ConnectionManager.getConnection();
				List<Idioma> idiomas;
				idiomas = daoI.findAll(c,"ES");
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
				Connection c= ConnectionManager.getConnection();
				List<Idioma> idiomas;
				idiomas = daoI.findByJuego(c, 2, "ES");
				for(int i=0;i<idiomas.size();i++){
					System.out.println(idiomas.get(i).getNombre());
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	public static void main(String[] args) {
		
		IdiomaDAOTest test = new IdiomaDAOTest();
				test.findByJuego();
				//test.testfindAll();
				//test.testfindByIdioma();
			

	}
}
