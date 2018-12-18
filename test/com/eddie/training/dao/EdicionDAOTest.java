package com.eddie.training.dao;

import java.util.List;

import com.eddie.training.dao.impl.EdicionDAOImpl;
import com.eddie.training.model.Edicion;

public class EdicionDAOTest {

		private EdicionDAOImpl dao=null;		
		 
		public EdicionDAOTest() {
			dao= new EdicionDAOImpl();
		}
		
		
		public void testfindByJuego() 
			throws Exception{
			List<Edicion> edicionesJuego= dao.findByJuegoAll(1);
			for(Edicion e: edicionesJuego) {
				System.out.println(e);
			}
		}
		
		
		public void testCreate()
		throws Exception{
		}
		
		public static void main(String[] args) {
			try {
				EdicionDAOTest test = new EdicionDAOTest();
				//test.testfindByFormato();
				//test.testfindByEdicion();
				//test.testfindByTipoEdicion();
				test.testfindByJuego();

			} catch (Exception e) {
				e.printStackTrace();
			}
			
	}

}
