package com.eddie.training.dao;

import java.sql.Connection;
import java.util.List;

import com.eddie.training.dao.Utils.ConnectionManager;
import com.eddie.training.dao.impl.FormatoDAOImpl;
import com.eddie.training.dao.impl.JuegoDAOImpl;
import com.eddie.training.model.Edicion;
import com.eddie.training.model.Formato;
import com.eddie.training.model.Juego;

public class FormatoDAOTest {

		private JuegoDAOImpl daoJ=null;
		private FormatoDAOImpl daoF=null;
		 
		public FormatoDAOTest() {
			
			daoF=new FormatoDAOImpl();
			daoJ=new JuegoDAOImpl();
		}

			
			public void testfindByFormato() { 
				try {
					Connection c= ConnectionManager.getConnection();
					Formato edicionesFormato= daoF.findbyIdFormato(c,2);
					System.out.println(edicionesFormato);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			public void testfindAll() {
				try {
					Connection c= ConnectionManager.getConnection();
					List<Formato> edicionesFormato;
					edicionesFormato = daoF.findAll(c);
					for(int i=0;i<edicionesFormato.size();i++){
						System.out.println(edicionesFormato.get(i).getNombre());
					}
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			public void testfindAllJuego() {
				try {
					Connection c= ConnectionManager.getConnection();
					List<Juego> juegos;
					juegos =daoJ.findAll();
					for(int i=0;i<juegos.size();i++){
						System.out.println(juegos.get(i));
					}
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			public void testfindid() {
				try {
					Connection c= ConnectionManager.getConnection();
					Juego juegos;
					juegos =daoJ.findById(6);
					System.out.println(juegos.getNombre());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		public static void main(String[] args) {
			
				FormatoDAOTest test = new FormatoDAOTest();
	
				try {
					//test.testfindid();
					test.testfindAll();
					//test.testfindByEdicion();
					//test.testfindByTipoEdicion();
					//test.testfindByJuego();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				

			
	}

}
