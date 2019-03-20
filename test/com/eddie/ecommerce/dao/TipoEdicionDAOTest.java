package com.eddie.ecommerce.dao;

import java.sql.Connection;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.eddie.ecommerce.dao.Utils.ConnectionManager;
import com.eddie.ecommerce.dao.impl.TipoEdicionDAOImpl;
import com.eddie.ecommerce.model.TipoEdicion;

public class TipoEdicionDAOTest {
	private static Logger logger=LogManager.getLogger(TipoEdicionDAOTest.class);
	private TipoEdicionDAOImpl daote=null; 
	public TipoEdicionDAOTest() {
		daote=new TipoEdicionDAOImpl();
	}
	
	public void findallTipoEdicion() {
		try {
			Connection c= ConnectionManager.getConnection();
			List<TipoEdicion> tipoEdicion;
			tipoEdicion = daote.findAll(c,"EN");
			for(int i=0;i<tipoEdicion.size();i++){
				logger.debug(tipoEdicion.get(i).getNombre());
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void testfindByTipoEdicion() { 
		try {
			Connection c= ConnectionManager.getConnection();
			TipoEdicion tipoEdicion= daote.findbyIdTipoEdicion(c,2,"EN");
			logger.debug(tipoEdicion.getNombre());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		TipoEdicionDAOTest test = new TipoEdicionDAOTest();

		test.testfindByTipoEdicion();
		test.findallTipoEdicion();
		
	}

}
