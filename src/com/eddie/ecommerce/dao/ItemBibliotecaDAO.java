package com.eddie.ecommerce.dao;

import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.model.ItemBiblioteca;
import com.eddie.ecommerce.model.Resultados;

import java.sql.Connection;
import java.util.List;

public interface ItemBibliotecaDAO {

	Resultados<ItemBiblioteca> findByUsuario(Connection connection, String email, int startIndex, int count) throws DataException;

	boolean exists(Connection connection, String email, Integer idJuego) throws DataException;

	//Comprobar
	List<Integer> exists(Connection connection, String email, List<Integer> idsJuegos) throws DataException;

	//Comprobar
	List<ItemBiblioteca> findByJuego(Connection connection, Integer idJuego) throws DataException;

	boolean create(Connection connection, ItemBiblioteca itemBiblioteca) throws DataException;

	boolean update(Connection connection, ItemBiblioteca itemBiblioteca) throws DataException;

	boolean delete(Connection connection, String email, Integer idJuego) throws DataException;
	
	ItemBiblioteca fingByIdEmail(Connection connection, String email, Integer idJuego) throws DataException;
}
