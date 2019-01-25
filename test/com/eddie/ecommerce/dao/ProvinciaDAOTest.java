package com.eddie.ecommerce.dao;

import java.sql.Connection;
import java.util.List;

import com.eddie.ecommerce.dao.Utils.ConnectionManager;
import com.eddie.ecommerce.dao.impl.ProvinciaDAOImpl;
import com.eddie.ecommerce.model.Provincia;

public class ProvinciaDAOTest {


	ProvinciaDAOImpl pdao=null;
	
	public ProvinciaDAOTest() {
		pdao=new ProvinciaDAOImpl();
	}
	
	public void testfindByProvincia() { 
		try {
			Connection c= ConnectionManager.getConnection();
			Provincia p= pdao.findById(c,7);
			System.out.println(p);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void testfindAll() {
		try {
			Connection c= ConnectionManager.getConnection();
			List<Provincia> provincias;
			provincias = pdao.findAll(c);
			for(int i=0;i<provincias.size();i++){
				System.out.println(provincias.get(i).getNombre());
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void testfindByIDPais() {
		try {
			Connection c= ConnectionManager.getConnection();
			List<Provincia> provincias;
			provincias = pdao.findAllByIdPais(c, 1);
			for(int i=0;i<provincias.size();i++){
				System.out.println(provincias.get(i).getNombre());
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		ProvinciaDAOTest test = new ProvinciaDAOTest();
		
		test.testfindByProvincia();
		//test.testfindAll();
		//test.testfindByIDPais();

	}

}
