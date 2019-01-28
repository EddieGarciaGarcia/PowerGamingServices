package com.eddie.ecommerce.dao;

import java.sql.Connection;
import java.util.List;

import com.eddie.ecommerce.model.ItemBiblioteca;
import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.exceptions.DuplicateInstanceException;
import com.eddie.ecommerce.exceptions.InstanceNotFoundException;

public interface ItemBibliotecaDAO {

	public List<ItemBiblioteca> findByUsuario(Connection connection,String email) throws DataException;
	
	public List<ItemBiblioteca> findByJuego(Connection connection,Integer idJuego) throws DataException;
	
	public ItemBiblioteca create(Connection connection,ItemBiblioteca b) throws DuplicateInstanceException, DataException;
	
	public ItemBiblioteca update (Connection connection,ItemBiblioteca b) throws DuplicateInstanceException, DataException;
	
	public long delete(Connection connection,String email,Integer idJuego) throws InstanceNotFoundException, DataException;
	
}
