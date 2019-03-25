package com.eddie.ecommerce.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.eddie.ecommerce.dao.Utils.ConnectionManager;
import com.eddie.ecommerce.dao.impl.ItemBibliotecaDAOImpl;
import com.eddie.ecommerce.model.ItemBiblioteca;
import com.eddie.ecommerce.service.impl.IdiomaServiceImpl;


public class ItemBibliotecaDAOTest {
	private ItemBibliotecaDAO daoIB=null;
	private static Logger logger=LogManager.getLogger(ItemBibliotecaDAOTest.class);
	public ItemBibliotecaDAOTest() {
		daoIB=new ItemBibliotecaDAOImpl();
	}
	
	public void testExist() {
		try {
			List<Integer>idsJuegos=new ArrayList<Integer>();
			idsJuegos.add(1);
			idsJuegos.add(3);
			Connection c= ConnectionManager.getConnection();
			List<Integer> ids;
			ids=daoIB.exists(c, "eddie_garcia@gmail.com", idsJuegos);
			
			for(Integer i:ids) {
				logger.debug(i);
			}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void testfindValoracion() {
		int numele=2,tamanhopag=2;
		try {
			Connection c= ConnectionManager.getConnection();
			List<ItemBiblioteca> biblio;
			biblio =(List<ItemBiblioteca>) daoIB.findByUsuario(c, "eddie_garcia@gmail.com",numele, tamanhopag);
			
			for(ItemBiblioteca ib : biblio){
				logger.debug(ib.getIdJuego()+","+ib.getFechaComentario());
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public static void main(String[] args) {
		ItemBibliotecaDAOTest test = new ItemBibliotecaDAOTest();
//		test.testfindValoracion();
		test.testExist();
	}
}
