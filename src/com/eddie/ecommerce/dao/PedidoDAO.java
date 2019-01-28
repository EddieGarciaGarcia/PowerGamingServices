package com.eddie.ecommerce.dao;

import java.sql.Connection;
import java.util.List;

import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.exceptions.InstanceNotFoundException;
import com.eddie.ecommerce.model.Pedido;
import com.eddie.ecommerce.exceptions.DuplicateInstanceException;

public interface PedidoDAO {
	
	public List<Pedido> findByEmail(Connection conexion,String email)throws InstanceNotFoundException, DataException;
	
	public Pedido findByID(Connection conexion,Integer idPedido)throws InstanceNotFoundException, DataException;
	
	public Pedido create(Connection conexion,Pedido p) throws DuplicateInstanceException, DataException;
	
	public void delete(Connection conexion,Integer idPedido) throws DataException;
}
