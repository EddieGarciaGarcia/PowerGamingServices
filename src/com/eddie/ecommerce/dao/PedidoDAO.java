package com.eddie.ecommerce.dao;

import java.sql.Connection;

import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.exceptions.InstanceNotFoundException;
import com.eddie.ecommerce.model.Pedido;
import com.eddie.ecommerce.service.Resultados;
import com.eddie.ecommerce.exceptions.DuplicateInstanceException;

public interface PedidoDAO {
	
	public Resultados<Pedido> findByEmail(Connection conexion,String email, int startIndex, int count)throws InstanceNotFoundException, DataException;
	
	public Pedido findByID(Connection conexion,Integer idPedido)throws InstanceNotFoundException, DataException;
	
	public Pedido create(Connection conexion,Pedido p) throws DuplicateInstanceException, DataException;
	
	public void delete(Connection conexion,Integer idPedido) throws DataException;
}
