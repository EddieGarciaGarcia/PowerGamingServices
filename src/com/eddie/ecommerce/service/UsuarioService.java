package com.eddie.ecommerce.service;

import com.eddie.ecommerce.model.Resultados;
import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.model.Direccion;
import com.eddie.ecommerce.model.ItemBiblioteca;
import com.eddie.ecommerce.model.Usuario;

import java.util.List;

public interface UsuarioService {
	
	boolean create(Usuario u) throws Exception;
	
	boolean update(Usuario u) throws DataException;
	
	boolean delete(String email) throws DataException;
	
	 Usuario findById(String email) throws DataException;
	
	Usuario login(String email, String password)throws DataException;

	//Biblioteca
	Resultados<ItemBiblioteca> findByUsuario(String email, int startIndex, int count)throws DataException;

	//Biblioteca
	List<ItemBiblioteca> findByUsuario(String email)throws DataException;
	
	//A�adir a biblioteca
	boolean addJuegoBiblioteca(String email, ItemBiblioteca b)throws DataException;
	
	boolean existsInBiblioteca(String email, Integer idJuego)throws DataException;
	List<Integer> existsInBiblioteca(String email, List<Integer> idsDeJuego)throws DataException;
	
	//Eliminar de la Biblioteca
	boolean borrarJuegoBiblioteca(String email, Integer idJuego)throws DataException;
	
	Direccion findByIdDireccion(String email) throws  DataException;
	
	boolean createDireccion(Direccion d) throws  DataException;
	
	boolean updateDireccion(Direccion d) throws  DataException;
	boolean deleteDireccion(String email) throws DataException;

	//Puntuacion
	boolean create(ItemBiblioteca it)throws DataException;
	
	ItemBiblioteca findByIdEmail(String email, Integer idJuego)throws DataException;

}
