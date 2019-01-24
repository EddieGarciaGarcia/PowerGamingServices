package com.eddie.training.dao;

import java.util.List;

import com.eddie.training.dao.impl.JuegoDAOImpl;
import com.eddie.training.model.Juego;

public class TestJuego {
	
	private JuegoDAOImpl juegodao=null;
	
	public TestJuego() {
		juegodao=new JuegoDAOImpl();
	}
	
	public void testJuego(Integer id) {
		try {
			Juego j=juegodao.findById(id);
			//List<Juego> j=juegodao.findAll();
			System.out.println(j.getNombre());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		TestJuego test=new TestJuego();
		//test.testJuego(1);
		test.testJuego(2);
	}
}
