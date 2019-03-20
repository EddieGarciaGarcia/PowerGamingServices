package com.eddie.ecommerce.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.eddie.ecommerce.dao.PaisDAOTest;
import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.model.Provincia;
import com.eddie.ecommerce.service.impl.ProvinciaServiceImpl;

public class ProvinciaServiceTest {
	
	ProvinciaService serviceP=null;
	private static Logger logger=LogManager.getLogger(ProvinciaServiceTest.class);
	public ProvinciaServiceTest() {
		serviceP=new ProvinciaServiceImpl();
	}
	
	public void testfindByIDPais() {
		
			
			List<Provincia> provincias;
			try {
				provincias = serviceP.findAllByIdPais(1);
				for(Provincia p:provincias){
					logger.debug(p.getIdProvincia()+" "+p.getNombre());
				}
			} catch (DataException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		
		
	}
	public static void main(String[] args) {
		
		ProvinciaServiceTest test = new ProvinciaServiceTest();

		test.testfindByIDPais();

	}

}
