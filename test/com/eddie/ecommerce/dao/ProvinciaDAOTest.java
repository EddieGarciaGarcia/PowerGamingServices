package com.eddie.ecommerce.dao;

import java.sql.Connection;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.eddie.ecommerce.dao.Utils.ConnectionManager;
import com.eddie.ecommerce.dao.impl.ProvinciaDAOImpl;
import com.eddie.ecommerce.model.Provincia;

public class ProvinciaDAOTest {

	private static Logger logger=LogManager.getLogger(ProvinciaDAOTest.class);
	ProvinciaDAOImpl pdao=null;
	
	public ProvinciaDAOTest() {
		pdao=new ProvinciaDAOImpl();
	}
	
	public void testfindByProvincia() { 
		try {
			Connection c= ConnectionManager.getConnection();
			Provincia p= pdao.findById(c,7);
			logger.debug(p.getIdProvincia()+" "+p.getNombre());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void testfindAll() {
		try {
			Connection c= ConnectionManager.getConnection();
			List<Provincia> provincias;
			provincias = pdao.findAll(c);
			for(Provincia p:provincias){
				logger.debug(p.getNombre());
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void testfindByIDPais() {
		try {
			Connection c= ConnectionManager.getConnection();
			List<Provincia> provincias;
			provincias = pdao.findAllByIdPais(c, 1);
			for(Provincia p:provincias){
				logger.debug(p.getIdProvincia()+" "+p.getNombre());
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		ProvinciaDAOTest test = new ProvinciaDAOTest();
		
		//test.testfindByProvincia();
		//test.testfindAll();
		//test.testfindByIDPais();

	}

}
