package com.eddie.ecommerce.dao;

import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.model.Pedido;
import com.eddie.ecommerce.model.Resultados;

import java.sql.Connection;
import java.util.List;

public interface PedidoDAO {
	
	Resultados<Pedido> findByEmail(Connection conexion, String email, int startIndex, int count)throws DataException;

	List<Pedido> findByIds(Connection conexion, List<Integer> ids)throws DataException;

	Pedido findByEmail(Connection conexion, String email)throws DataException;

	boolean create(Connection conexion, Pedido pedido) throws DataException;

	boolean delete(Connection conexion, Integer idPedido) throws DataException;
}
