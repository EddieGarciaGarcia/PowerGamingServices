package com.eddie.ecommerce.service;



import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.exceptions.DuplicateInstanceException;
import com.eddie.ecommerce.model.ItemBiblioteca;
import com.eddie.ecommerce.model.Usuario;
import com.eddie.ecommerce.service.impl.UsuarioServiceImpl;

public class UsuarioServiceImplTest {
	
	private UsuarioService daoU=null;
	
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
	public void testeCreateServiceBiblio() {
		try {
			
			ItemBiblioteca biblio=new ItemBiblioteca();
			biblio.setEmail("eddie_garcia@gmail.com");
			biblio.setIdJuego(5);
			biblio.setComprado("N");
			daoU.addJuegoBiblioteca(biblio);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void testServiceDeleteBiblio() {
		try {
			daoU.borrarJuegoBiblioteca("eddietuenti@gmail.com", 5);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void testLogin() {
		try {
			Usuario u=daoU.login("eddie_garcia@gmail.com", "root");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void create() {
		try {
			Usuario prueba=new Usuario("prueba1","hasd","asda","root","123134241","eddietuenti@gmail.com", new Date(), "has","H", 1);
			prueba=daoU.create(prueba);
			System.out.println(prueba.toString());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DuplicateInstanceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void delete() {
		
			try {
				System.out.println(daoU.delete("eddietuenti@gmail.com"));
			} catch (DataException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
	}
	
	public static void main(String[] args) {
		UsuarioServiceImplTest test = new UsuarioServiceImplTest();
		//test.testeServiceBiblio();
		//test.testeCreateServiceBiblio();
		//test.testServiceDeleteBiblio();
		//test.create();
		test.delete();
	}
}
