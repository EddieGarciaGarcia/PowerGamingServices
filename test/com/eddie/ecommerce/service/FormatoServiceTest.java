package com.eddie.ecommerce.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.eddie.ecommerce.dao.PaisDAOTest;
import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.model.Formato;
import com.eddie.ecommerce.service.impl.FormatoServiceImpl;

public class FormatoServiceTest {
	
	private FormatoService serviceF=null;
	private static Logger logger=LogManager.getLogger(FormatoServiceTest.class);
	
	public FormatoServiceTest() {
		
		serviceF=new FormatoServiceImpl();
		
	}

		public void testfindByFormato() { 
			
				Formato edicionesFormato;
				try {
					edicionesFormato = serviceF.findbyIdFormato(2,"ES");
					logger.debug(edicionesFormato.getNombre());
				} catch (DataException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			
		}
		public void testfindAll() {
			

				List<Formato> edicionesFormato;
				try {
					edicionesFormato = serviceF.findAll("ES");
					for(Formato f : edicionesFormato){
						logger.debug(f.getNombre());
					}
				} catch (DataException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			
			
		}
	public static void main(String[] args) {
		FormatoServiceTest test = new FormatoServiceTest();

		test.testfindAll();
		//test.testfindByFormato();
	}

}
