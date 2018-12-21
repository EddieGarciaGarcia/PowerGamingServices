package com.eddie.training.dao;

import java.util.Date;
import java.util.List;

import com.eddie.training.model.ItemBiblioteca;

public interface ItemBibliotecaDAO {

	public List<ItemBiblioteca> findByUsuario(String email) throws Exception;
	
	public ItemBiblioteca create(ItemBiblioteca b) throws Exception;
	
	public boolean update(ItemBiblioteca b) throws Exception;
	
	public void delete(ItemBiblioteca b) throws Exception;
}
