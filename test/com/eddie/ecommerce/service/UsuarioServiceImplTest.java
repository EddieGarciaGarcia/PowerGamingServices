package com.eddie.ecommerce.service;


import java.util.List;
import com.eddie.ecommerce.model.ItemBiblioteca;
import com.eddie.ecommerce.model.Usuario;
import com.eddie.ecommerce.service.impl.UsuarioServiceImpl;

public class UsuarioServiceImplTest {
	private UsuarioServiceDAO daoU=null;
	public UsuarioServiceImplTest() {
		daoU=new UsuarioServiceImpl();
	}
	
	
	public void testeServiceBiblio() {
		try {
			List<ItemBiblioteca> biblio;
			Usuario u =new Usuario();
			u.setEmail("eddie_garcia@gmail.com");
			biblio =daoU.findByUsuario(u.getEmail());
			
			for(ItemBiblioteca ib : biblio){
			    System.out.println(ib.getIdJuego());
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public static void main(String[] args) {
		UsuarioServiceImplTest test = new UsuarioServiceImplTest();
		test.testeServiceBiblio();
	}
}
