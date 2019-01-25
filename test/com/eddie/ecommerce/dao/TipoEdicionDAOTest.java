package com.eddie.ecommerce.dao;

import java.sql.Connection;
import java.util.List;

import com.eddie.ecommerce.dao.Utils.ConnectionManager;
import com.eddie.ecommerce.dao.impl.TipoEdicionDAOImpl;
import com.eddie.ecommerce.model.TipoEdicion;

public class TipoEdicionDAOTest {

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
				System.out.println(tipoEdicion.get(i).getNombre());
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
			System.out.println(tipoEdicion.getNombre());
			
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
