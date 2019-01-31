package com.eddie.ecommerce.service;

import java.sql.SQLException;
import java.util.List;

import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.model.Pais;
import com.eddie.ecommerce.service.impl.PaisServiceImpl;

public class PaisServiceTest {
	
	PaisService serviceP=null;
	
	public PaisServiceTest() {
		serviceP=new PaisServiceImpl();
	}
	

	public void testfindAll() {
		
			List<Pais> pais;
			try {
				pais = serviceP.findAll();
				for(int i=0;i<pais.size();i++){
					System.out.println(pais.get(i).getNombre());
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
