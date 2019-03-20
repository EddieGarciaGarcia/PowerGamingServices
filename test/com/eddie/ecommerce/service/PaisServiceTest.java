package com.eddie.ecommerce.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.eddie.ecommerce.dao.PaisDAOTest;
import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.model.Pais;
import com.eddie.ecommerce.service.impl.PaisServiceImpl;

public class PaisServiceTest {
	private static Logger logger=LogManager.getLogger(PaisServiceTest.class);
	PaisService serviceP=null;
	
	public PaisServiceTest() {
		serviceP=new PaisServiceImpl();
	}
	

	public void testfindAll() {
		
			List<Pais> pais;
			try {
				pais = serviceP.findAll();
				for(int i=0;i<pais.size();i++){
					logger.debug(pais.get(i).getNombre());
				}
			} catch (DataException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
	}
	
	public static void main(String[] args) {
		PaisServiceTest test = new PaisServiceTest();

		test.testfindAll();


	}

}
