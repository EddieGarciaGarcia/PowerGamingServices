package com.eddie.ecommerce.dao;

import java.util.Date;
import java.util.List;

import com.eddie.ecommerce.model.ItemBiblioteca;
import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.exceptions.DuplicateInstanceException;
import com.eddie.ecommerce.exceptions.InstanceNotFoundException;

public interface ItemBibliotecaDAO {

	public List<ItemBiblioteca> findByUsuario(String email) throws DataException;
	
	public ItemBiblioteca create(ItemBiblioteca b) throws DuplicateInstanceException, DataException;
	
	public boolean update(ItemBiblioteca b) throws InstanceNotFoundException, DataException;
	
	public void delete(ItemBiblioteca b) throws DataException;
}
