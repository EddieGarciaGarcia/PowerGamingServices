package com.eddie.ecommerce.service;

import java.util.List;
import com.eddie.ecommerce.model.Plataforma;
import com.eddie.ecommerce.service.impl.PlataformaServiceImpl;

public class PlataformaServiceTest {
	
	PlataformaService serviceP=null;
	
	public PlataformaServiceTest() {
		serviceP=new PlataformaServiceImpl();
	}
	
	public void testfindByPlataforma() { 
		try {
			Plataforma plataforma= serviceP.findbyIdPlataforma(2);
			System.out.println(plataforma.getNombre());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void testfindAll() {
		try {
			List<Plataforma> plataforma;
			plataforma = serviceP.findAll();
			for(Plataforma p:plataforma){
				System.out.println(p.getNombre());
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void findByJuego() {
		try {
			List<Plataforma> p;
			p = serviceP.findByJuego(2);
			for(int i=0;i<p.size();i++){
				System.out.println(p.get(i).getNombre());
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		PlataformaServiceTest test = new PlataformaServiceTest();
		//test.findByJuego();
		//test.testfindAll();
		//test.testfindByPlataforma();

	}

}
