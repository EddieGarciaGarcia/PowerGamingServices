package com.eddie.ecommerce.service;

import java.sql.SQLException;
import java.util.List;
import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.model.Categoria;
import com.eddie.ecommerce.service.impl.CategoriaServiceImpl;

public class CategoriaServiceTest {
	
	private CategoriaService serviceC=null;
	
	public CategoriaServiceTest() {
		
		serviceC=new CategoriaServiceImpl();
		
		
	}

		public void testfindByCategoria() { 
			
				Categoria ca;
				try {
					ca = serviceC.findById(1,"ES");
					System.out.println(ca.getNombre());
				} catch (SQLException | DataException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			
		}
		public void testfindAll() {	
				try {
					List<Categoria> categorias;
					categorias = serviceC.findAll("ES");
					for(int i=0;i<categorias.size();i++){
						System.out.println(categorias.get(i).getIdCategria()+","+categorias.get(i).getNombre());
					}
				} catch (SQLException | DataException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
		
		public void findByJuego() {
			
				List<Categoria> categorias;
				try {
					categorias = serviceC.findByJuego(10, "ES");
					for(int i=0;i<categorias.size();i++){
						System.out.println(categorias.get(i).getNombre());
					}
				} catch (DataException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			
		}
		
		public static void main(String[] args) {
			
			
			CategoriaServiceTest test = new CategoriaServiceTest();
				//test.findByJuego();
				test.testfindAll();
				test.testfindAll();
				//test.testfindByCategoria();
	}
}
