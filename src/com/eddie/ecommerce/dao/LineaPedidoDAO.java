package com.eddie.ecommerce.dao;

import java.util.List;

import com.eddie.ecommerce.model.LineaPedido;
import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.exceptions.DuplicateInstanceException;
import com.eddie.ecommerce.exceptions.InstanceNotFoundException;

public interface LineaPedidoDAO {

	public List<LineaPedido> findByPedido(Integer idPedido)throws DataException;
	
	public LineaPedido findById(Integer numeroLinea)throws InstanceNotFoundException, DataException;
	
	public LineaPedido create(LineaPedido lp) throws DuplicateInstanceException, DataException;
	
	public void delete(LineaPedido lp) throws DataException;
}
