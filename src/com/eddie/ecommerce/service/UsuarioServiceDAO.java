package com.eddie.ecommerce.service;

import java.util.List;

import com.eddie.ecommerce.model.ItemBiblioteca;
import com.eddie.ecommerce.model.Usuario;

public interface UsuarioServiceDAO {
	
	public Usuario create(Usuario u) throws Exception;
	
	public void update(Usuario u) throws Exception;
	
	public long delete(String email) throws Exception;
	
	public Usuario findById(String email) throws Exception;
	
	public Usuario login(String email, String password)throws Exception;
	
	//Biblioteca
	public List<ItemBiblioteca> findByUsuario(String email)throws Exception;
	
	//Añadir a biblioteca
	public ItemBiblioteca create(ItemBiblioteca b)throws Exception;
	
	//Eliminar de la Biblioteca
	public long delete(String email,Integer idJuego)throws Exception;
	
	//Metodo "inservible" de momento
	public List<Usuario> findById(String idioma, String nombre) throws Exception; 
}
