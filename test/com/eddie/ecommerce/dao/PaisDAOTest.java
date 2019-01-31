package com.eddie.ecommerce.dao;

import java.sql.Connection;
import java.util.List;

import com.eddie.ecommerce.dao.Utils.ConnectionManager;
import com.eddie.ecommerce.dao.impl.PaisDAOImpl;
import com.eddie.ecommerce.model.Pais;

public class PaisDAOTest {
	PaisDAOImpl pdao=null;
	
	public PaisDAOTest() {
		pdao=new PaisDAOImpl();
	}
	
	public void testfindByPais() { 
		try {
			Connection c= ConnectionManager.getConnection();
			Pais pais= pdao.findById(c,8);
			System.out.println(pais.getNombre());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void testfindAll() {
		try {
			Connection c= ConnectionManager.getConnection();
			List<Pais> pais;
			pais = pdao.findAll(c);
			for(int i=0;i<pais.size();i++){
				System.out.println(pais.get(i).getNombre());
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		PaisDAOTest test = new PaisDAOTest();

		test.testfindAll();
		test.testfindByPais();
	}

}
