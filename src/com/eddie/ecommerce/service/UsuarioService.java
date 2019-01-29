package com.eddie.ecommerce.service;

import java.sql.SQLException;
import java.util.List;

import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.exceptions.DuplicateInstanceException;
import com.eddie.ecommerce.exceptions.InstanceNotFoundException;
import com.eddie.ecommerce.model.ItemBiblioteca;
import com.eddie.ecommerce.model.Usuario;

public interface UsuarioService {
	
	public Usuario create(Usuario u) throws DuplicateInstanceException,Exception;
	
	public void update(Usuario u) throws InstanceNotFoundException,Exception;
	
	public long delete(String email) throws DataException;
	
	public Usuario findById(String email) throws SQLException,InstanceNotFoundException,DataException;
	
	public Usuario login(String email, String password)throws SQLException,DataException;
	
	//Biblioteca
	public List<ItemBiblioteca> findByUsuario(String email)throws SQLException,DataException;
	
	//Añadir a biblioteca
	public ItemBiblioteca addJuegoBiblioteca(ItemBiblioteca b)throws DuplicateInstanceException,SQLException,DataException;
	
	//Eliminar de la Biblioteca
	public long borrarJuegoBiblioteca(String email,Integer idJuego)throws InstanceNotFoundException,SQLException, DataException;
	

}
