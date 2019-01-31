package com.eddie.ecommerce.service;

import java.sql.Connection;
import java.util.List;

import com.eddie.ecommerce.dao.CategoriaDAOTest;
import com.eddie.ecommerce.dao.Utils.ConnectionManager;
import com.eddie.ecommerce.model.Categoria;
import com.eddie.ecommerce.service.impl.CategoriaServiceImpl;

public class CategoriaServiceTest {
	
	private CategoriaService serviceC=null;
	
	public CategoriaServiceTest() {
		
		serviceC=new CategoriaServiceImpl();
		
		
	}

		public void testfindByCategoria() { 
			try {
				Categoria ca= serviceC.findById(1,"ES");
				System.out.println(ca.getNombre());
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public void testfindAll() {
			try {
				Connection c= ConnectionManager.getConnection();
				List<Categoria> categorias;
				categorias = serviceC.findAll("ES");
				for(int i=0;i<categorias.size();i++){
					System.out.println(categorias.get(i).getNombre());
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		public void findByJuego() {
			try {
				Connection c= ConnectionManager.getConnection();
				List<Categoria> categorias;
				categorias = serviceC.findByJuego(10, "ES");
				for(int i=0;i<categorias.size();i++){
					System.out.println(categorias.get(i).getNombre());
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public static void main(String[] args) {
			
			
			CategoriaServiceTest test = new CategoriaServiceTest();
				//test.findByJuego();
				//test.testfindAll();
				test.testfindByCategoria();
	}
}
