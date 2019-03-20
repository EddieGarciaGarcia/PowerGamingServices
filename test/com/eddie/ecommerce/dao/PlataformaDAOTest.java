package com.eddie.ecommerce.dao;

import java.sql.Connection;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.eddie.ecommerce.dao.Utils.ConnectionManager;
import com.eddie.ecommerce.dao.impl.PlataformaDAOImpl;
import com.eddie.ecommerce.model.Idioma;
import com.eddie.ecommerce.model.Plataforma;

public class PlataformaDAOTest {

	PlataformaDAOImpl pdao=null;
	private static Logger logger=LogManager.getLogger(PlataformaDAOTest.class);
	public PlataformaDAOTest() {
		pdao=new PlataformaDAOImpl();
	}
	
	public void testfindByPlataforma() { 
		try {
			Connection c= ConnectionManager.getConnection();
			Plataforma plataforma= pdao.findbyIdPlataforma(c,2);
			logger.debug(plataforma.getNombre());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void testfindAll() {
		try {
			Connection c= ConnectionManager.getConnection();
			List<Plataforma> plataforma;
			plataforma = pdao.findAll(c);
			for(Plataforma p:plataforma){
				logger.debug(p.getNombre());
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void findByJuego() {
		try {
			Connection c= ConnectionManager.getConnection();
			List<Plataforma> p;
			p = pdao.findByJuego(c, 2);
			for(int i=0;i<p.size();i++){
				logger.debug(p.get(i).getNombre());
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		PlataformaDAOTest test = new PlataformaDAOTest();
		test.findByJuego();
		//test.testfindAll();
		//test.testfindByPlataforma();

	}

}
