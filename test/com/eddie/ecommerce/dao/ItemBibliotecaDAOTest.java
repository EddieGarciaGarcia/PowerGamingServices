package com.eddie.ecommerce.dao;

import java.sql.Connection;
import java.util.List;

import com.eddie.ecommerce.dao.Utils.ConnectionManager;
import com.eddie.ecommerce.dao.impl.ItemBibliotecaDAOImpl;
import com.eddie.ecommerce.model.ItemBiblioteca;


public class ItemBibliotecaDAOTest {
	private ItemBibliotecaDAO daoIB=null;
	public ItemBibliotecaDAOTest() {
		daoIB=new ItemBibliotecaDAOImpl();
	}
	
	
	public void testfindValoracion() {
		try {
			Connection c= ConnectionManager.getConnection();
			List<ItemBiblioteca> biblio;
			biblio =daoIB.findByUsuario(c, "eddie_garcia@gmail.com");
			
			for(ItemBiblioteca ib : biblio){
			    System.out.println(ib.getIdJuego()+","+ib.getFechaComentario());
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public static void main(String[] args) {
		ItemBibliotecaDAOTest test = new ItemBibliotecaDAOTest();
		test.testfindValoracion();
	}
}
