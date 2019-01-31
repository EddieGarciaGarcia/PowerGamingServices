package com.eddie.ecommerce.service;

import java.util.List;
import com.eddie.ecommerce.model.TipoEdicion;
import com.eddie.ecommerce.service.impl.TipoEdicionServiceImpl;

public class TipoEdicionServiceTest {
	
	private TipoEdicionService serviceTE=null; 
	public TipoEdicionServiceTest() {
		serviceTE=new TipoEdicionServiceImpl();
	}
	
	public void findallTipoEdicion() {
		try {
			List<TipoEdicion> tipoEdicion;
			tipoEdicion = serviceTE.findAll("EN");
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
			TipoEdicion tipoEdicion= serviceTE.findbyIdTipoEdicion(2,"EN");
			System.out.println(tipoEdicion.getNombre());
			
		} catch (Exception e) {
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
