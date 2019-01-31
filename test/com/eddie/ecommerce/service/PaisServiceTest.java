package com.eddie.ecommerce.service;

import java.util.List;
import com.eddie.ecommerce.model.Pais;
import com.eddie.ecommerce.service.impl.PaisServiceImpl;

public class PaisServiceTest {
	
	PaisService serviceP=null;
	
	public PaisServiceTest() {
		serviceP=new PaisServiceImpl();
	}
	

	public void testfindAll() {
		try {
			List<Pais> pais;
			pais = serviceP.findAll();
			for(int i=0;i<pais.size();i++){
				System.out.println(pais.get(i).getNombre());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		PaisServiceTest test = new PaisServiceTest();

		test.testfindAll();


	}

}
