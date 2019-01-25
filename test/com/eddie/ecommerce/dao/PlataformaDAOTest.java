package com.eddie.ecommerce.dao;

import java.sql.Connection;
import java.util.List;

import com.eddie.ecommerce.dao.Utils.ConnectionManager;
import com.eddie.ecommerce.dao.impl.PlataformaDAOImpl;
import com.eddie.ecommerce.model.Plataforma;

public class PlataformaDAOTest {

	PlataformaDAOImpl pdao=null;
	
	public PlataformaDAOTest() {
		pdao=new PlataformaDAOImpl();
	}
	
	public void testfindByPlataforma() { 
		try {
			Connection c= ConnectionManager.getConnection();
			Plataforma plataforma= pdao.findbyIdPlataforma(c,2);
			System.out.println(plataforma.getNombre());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void testfindAll() {
		try {
			Connection c= ConnectionManager.getConnection();
			List<Plataforma> plataforma;
			plataforma = pdao.findAll(c);
			for(int i=0;i<plataforma.size();i++){
				System.out.println(plataforma.get(i).getNombre());
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		PlataformaDAOTest test = new PlataformaDAOTest();

		test.testfindAll();
		test.testfindByPlataforma();

	}

}
