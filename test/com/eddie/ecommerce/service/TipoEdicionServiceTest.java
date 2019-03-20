package com.eddie.ecommerce.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.eddie.ecommerce.dao.PaisDAOTest;
import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.model.TipoEdicion;
import com.eddie.ecommerce.service.impl.TipoEdicionServiceImpl;

public class TipoEdicionServiceTest {
	private static Logger logger=LogManager.getLogger(TipoEdicionServiceTest.class);
	private TipoEdicionService serviceTE=null; 
	public TipoEdicionServiceTest() {
		serviceTE=new TipoEdicionServiceImpl();
	}
	
	public void findallTipoEdicion() {
		
			List<TipoEdicion> tipoEdicion;
			try {
				tipoEdicion = serviceTE.findAll("EN");
				for(int i=0;i<tipoEdicion.size();i++){
					logger.debug(tipoEdicion.get(i).getNombre());
				}
			} catch (DataException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		
	}
	
	public void testfindByTipoEdicion() { 
		
			TipoEdicion tipoEdicion;
			try {
				tipoEdicion = serviceTE.findbyIdTipoEdicion(2,"EN");
				logger.debug(tipoEdicion.getNombre());
			} catch (DataException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		
	}
	
	public static void main(String[] args) {
		TipoEdicionServiceTest test = new TipoEdicionServiceTest();

		test.testfindByTipoEdicion();
		test.findallTipoEdicion();

	}

}
