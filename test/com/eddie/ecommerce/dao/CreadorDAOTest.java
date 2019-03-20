package com.eddie.ecommerce.dao;

import java.sql.Connection;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.eddie.ecommerce.dao.Utils.ConnectionManager;
import com.eddie.ecommerce.dao.impl.CreadorDAOImpl;
import com.eddie.ecommerce.model.Creador;
import com.eddie.ecommerce.service.impl.IdiomaServiceImpl;

public class CreadorDAOTest {
	private CreadorDAOImpl daoC=null;
	private static Logger logger=LogManager.getLogger(CreadorDAOTest.class);
	
	public CreadorDAOTest() {
		
		daoC=new CreadorDAOImpl();
		
		
	}

		public void testfindByCreador() { 
			try {
				Connection c= ConnectionManager.getConnection();
				Creador cr= daoC.findbyIdCreador(c,5);
				logger.debug(cr.getNombre());
				
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
					logger.debug(creador.get(i).getNombre());
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
