package com.eddie.ecommerce.service;

import java.sql.SQLException;
import java.util.List;

import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.model.TipoEdicion;
import com.eddie.ecommerce.service.impl.TipoEdicionServiceImpl;

public class TipoEdicionServiceTest {
	
	private TipoEdicionService serviceTE=null; 
	public TipoEdicionServiceTest() {
		serviceTE=new TipoEdicionServiceImpl();
	}
	
	public void findallTipoEdicion() {
		
			List<TipoEdicion> tipoEdicion;
			try {
				tipoEdicion = serviceTE.findAll("EN");
				for(int i=0;i<tipoEdicion.size();i++){
					System.out.println(tipoEdicion.get(i).getNombre());
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
				System.out.println(tipoEdicion.getNombre());
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
