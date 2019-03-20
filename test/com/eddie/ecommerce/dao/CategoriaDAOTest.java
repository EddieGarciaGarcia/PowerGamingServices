package com.eddie.ecommerce.dao;

import java.sql.Connection;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.eddie.ecommerce.dao.Utils.ConnectionManager;
import com.eddie.ecommerce.dao.impl.CategoriaDAOImpl;
import com.eddie.ecommerce.model.Categoria;
import com.eddie.ecommerce.service.impl.IdiomaServiceImpl;

public class CategoriaDAOTest {
	private CategoriaDAOImpl daoC=null;
	private static Logger logger=LogManager.getLogger(CategoriaDAOTest.class);
	
	public CategoriaDAOTest() {
		
		daoC=new CategoriaDAOImpl();
		
		
	}

		public void testfindByCategoria() { 
			try {
				Connection c= ConnectionManager.getConnection();
				Categoria ca= daoC.findById(c,2,"ES");
				logger.debug(ca.getNombre());
				
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
					logger.debug(categorias.get(i).getNombre());
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
				categorias = daoC.findByJuego(c, 2, "ES");
				for(int i=0;i<categorias.size();i++){
					logger.debug(categorias.get(i).getNombre());
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

	public static void main(String[] args) {
		
			CategoriaDAOTest test = new CategoriaDAOTest();
				test.findByJuego();
				//test.testfindAll();
				//test.testfindByCategoria();
	}

}
