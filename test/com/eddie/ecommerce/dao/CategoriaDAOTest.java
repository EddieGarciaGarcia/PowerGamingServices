package com.eddie.ecommerce.dao;

import java.sql.Connection;
import java.util.List;

import com.eddie.ecommerce.dao.Utils.ConnectionManager;
import com.eddie.ecommerce.dao.impl.CategoriaDAOImpl;
import com.eddie.ecommerce.model.Categoria;

public class CategoriaDAOTest {
	private CategoriaDAOImpl daoC=null;
	
	
	public CategoriaDAOTest() {
		
		daoC=new CategoriaDAOImpl();
		
		
	}

		public void testfindByCategoria() { 
			try {
				Connection c= ConnectionManager.getConnection();
				Categoria ca= daoC.findById(c,2,"ES");
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
				categorias = daoC.findAll(c,"ES");
				for(int i=0;i<categorias.size();i++){
					System.out.println(categorias.get(i).getNombre());
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		

	public static void main(String[] args) {
		
			CategoriaDAOTest test = new CategoriaDAOTest();

				test.testfindAll();
				test.testfindByCategoria();
	}

}
