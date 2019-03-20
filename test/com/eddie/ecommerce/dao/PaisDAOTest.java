package com.eddie.ecommerce.dao;

import java.sql.Connection;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.eddie.ecommerce.dao.Utils.ConnectionManager;
import com.eddie.ecommerce.dao.impl.PaisDAOImpl;
import com.eddie.ecommerce.model.Pais;
import com.eddie.ecommerce.service.impl.IdiomaServiceImpl;

public class PaisDAOTest {
	PaisDAOImpl pdao=null;
	private static Logger logger=LogManager.getLogger(PaisDAOTest.class);
	public PaisDAOTest() {
		pdao=new PaisDAOImpl();
	}
	
	public void testfindByPais() { 
		try {
			Connection c= ConnectionManager.getConnection();
			Pais pais= pdao.findById(c,8);
			logger.debug(pais.getNombre());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void testfindAll() {
		try {
			Connection c= ConnectionManager.getConnection();
			List<Pais> pais;
			pais = pdao.findAll(c);
			for(int i=0;i<pais.size();i++){
				logger.debug(pais.get(i).getNombre());
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		PaisDAOTest test = new PaisDAOTest();

		test.testfindAll();
		test.testfindByPais();
	}

}
