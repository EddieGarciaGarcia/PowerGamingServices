package com.eddie.ecommerce.dao;

import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.exceptions.InstanceNotFoundException;
import com.eddie.ecommerce.model.Pedido;
import com.eddie.ecommerce.exceptions.DuplicateInstanceException;

public interface PedidoDAO {
	
	public Pedido findByEmail(String email)throws InstanceNotFoundException, DataException;
	
	public Pedido findByID(Integer idPedido)throws InstanceNotFoundException, DataException;
	
	public Pedido create(PedidoDAO p) throws DuplicateInstanceException, DataException;
	
	public boolean update(PedidoDAO p) throws InstanceNotFoundException, DataException;
	
	public void delete(PedidoDAO p) throws DataException;
}
