package com.eddie.ecommerce.service;



import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.exceptions.DuplicateInstanceException;
import com.eddie.ecommerce.model.Direccion;
import com.eddie.ecommerce.model.ItemBiblioteca;
import com.eddie.ecommerce.model.Usuario;
import com.eddie.ecommerce.service.impl.UsuarioServiceImpl;

public class UsuarioServiceImplTest {
	
	private UsuarioService daoU=null;
	
	public UsuarioServiceImplTest() {
		daoU=new UsuarioServiceImpl();
	}
	
	
	public void testeServiceBiblio() {
		
			List<ItemBiblioteca> biblio;
			Usuario u =new Usuario();
			u.setEmail("eddie_garcia@gmail.com");
			try {
				biblio =daoU.findByUsuario(u.getEmail());
				for(ItemBiblioteca ib : biblio){
				    System.out.println(ib.getIdJuego());
				}
			} catch (SQLException | DataException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		
	}
	public void testeCreateServiceBiblio() {

			ItemBiblioteca biblio=new ItemBiblioteca();
			biblio.setEmail("eddie_garcia@gmail.com");
			biblio.setIdJuego(5);
			biblio.setComprado("N");
			try {
				daoU.addJuegoBiblioteca(biblio);
			} catch (SQLException | DataException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
	public void testServiceDeleteBiblio() {
		
			try {
				daoU.borrarJuegoBiblioteca("eddietuenti@gmail.com", 5);
			} catch (SQLException | DataException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
	public void testLogin() {
		
			try {
				Usuario u=daoU.login("eddie_garcia@gmail.com", "root");
			} catch (SQLException | DataException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	   
	public void create() {
		try {
			Usuario prueba=new Usuario();
			prueba.setEmail("eddie_taboada@hotmail.com");
			prueba.setNombre("asdada");
			prueba.setApellido1("asdadasda");
			prueba.setApellido2(null);
			prueba.setTelefono(null);
			prueba.setPassword("root");
			DateFormat dF= new SimpleDateFormat("dd/MM/yyyy");
			Date fechaNacimiento=dF.parse("21/06/1994");
			prueba.setFechaNacimiento(fechaNacimiento);
			prueba.setGenero("H");
			prueba.setNombreUser(prueba.getNombre().concat(prueba.getApellido1()));
			
			daoU.create(prueba);
			
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
					System.out.println(daoU.delete("eddie_taboada@hotmail.com"));
				} catch (DataException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
	
	}
	
	public void createdireccion() {
		try {
			Direccion prueba=new Direccion();
			prueba.setCalle("avenida del paraiso");
			prueba.setNumero("34");
			prueba.setPiso("1A");
			prueba.setCodigoPostal("25679");
			prueba.setIdprovincia(23);
			prueba.setLocalidad("Ordes");
			prueba=daoU.createDireccion(prueba);
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
	
	
	public static void main(String[] args) {
		UsuarioServiceImplTest test = new UsuarioServiceImplTest();
		//test.testeServiceBiblio();
		//test.testeCreateServiceBiblio();
		//test.testServiceDeleteBiblio();
		test.create();
		//test.delete();
		//test.createdireccion();
		//test.testLogin();
	}
}
