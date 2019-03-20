package com.eddie.ecommerce.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.eddie.ecommerce.dao.PaisDAOTest;
import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.model.Plataforma;
import com.eddie.ecommerce.service.impl.PlataformaServiceImpl;

public class PlataformaServiceTest {
	
	PlataformaService serviceP=null;
	private static Logger logger=LogManager.getLogger(PlataformaServiceTest.class);
	public PlataformaServiceTest() {
		serviceP=new PlataformaServiceImpl();
	}
	
	public void testfindByPlataforma() { 
		
			Plataforma plataforma;
			try {
				plataforma = serviceP.findbyIdPlataforma(2);
				logger.debug(plataforma.getNombre());
			} catch (SQLException | DataException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	public void testfindAll() {
		
			List<Plataforma> plataforma;
			try {
				plataforma = serviceP.findAll();
				for(Plataforma p:plataforma){
					logger.debug(p.getNombre());
				}
			} catch (SQLException | DataException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	public void findByJuego() {
		
			List<Plataforma> p;
			try {
				p = serviceP.findByJuego(2);
				for(int i=0;i<p.size();i++){
					logger.debug(p.get(i).getNombre());
				}
			} catch (DataException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		
	}
	
	public static void main(String[] args) {
		PlataformaServiceTest test = new PlataformaServiceTest();
		//test.findByJuego();
		//test.testfindAll();
		//test.testfindByPlataforma();

	}

}
