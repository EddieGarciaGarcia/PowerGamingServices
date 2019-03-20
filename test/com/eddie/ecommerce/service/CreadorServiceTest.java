package com.eddie.ecommerce.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.eddie.ecommerce.dao.PaisDAOTest;
import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.model.Creador;
import com.eddie.ecommerce.service.impl.CreadorServiceImpl;

public class CreadorServiceTest {
	
	private CreadorService serviceC=null;
	private static Logger logger=LogManager.getLogger(CreadorServiceTest.class);
	
	public CreadorServiceTest() {
		
		serviceC=new CreadorServiceImpl();
		
		
	}

		public void testfindByCreador() { 
				
				Creador cr;
				try {
					cr = serviceC.findbyIdCreador(5);
					logger.debug(cr.getNombre());
				} catch (DataException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
		public void testfindAll() {
			
				List<Creador> creador;
				try {
					creador = serviceC.findAll();
					for(int i=0;i<creador.size();i++){
						logger.debug(creador.get(i).getNombre());
					}
				} catch (DataException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			
			
		}
	public static void main(String[] args) {
		CreadorServiceTest test = new CreadorServiceTest();

		test.testfindAll();
		test.testfindAll();
		//test.testfindByCreador();

	}
}
