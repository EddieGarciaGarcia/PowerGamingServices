package com.eddie.ecommerce.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.eddie.ecommerce.dao.PaisDAOTest;
import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.model.Categoria;
import com.eddie.ecommerce.service.impl.CategoriaServiceImpl;

public class CategoriaServiceTest {
	
	private CategoriaService serviceC=null;
	private static Logger logger=LogManager.getLogger(CategoriaServiceTest.class);
	public CategoriaServiceTest() {
		
		serviceC=new CategoriaServiceImpl();
		
		
	}

		public void testfindByCategoria() { 
			
				Categoria ca;
				try {
					ca = serviceC.findById(1,"ES");
					logger.debug(ca.getNombre());
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
						logger.debug(categorias.get(i).getIdCategria()+","+categorias.get(i).getNombre());
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
						logger.debug(categorias.get(i).getNombre());
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
