package com.eddie.ecommerce.dao;

import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.model.LineaPedido;

import java.sql.Connection;
import java.util.List;

public interface LineaPedidoDAO {

	List<LineaPedido> findByPedido(Connection conexion, Integer idPedido)throws DataException;

	LineaPedido findById(Connection conexion, Integer numeroLinea)throws DataException;

	boolean create(Connection conexion, LineaPedido lineaPedido) throws DataException;

	boolean delete(Connection conexion, Integer id) throws DataException;

	boolean deleteByPedido(Connection conexion, Integer id) throws DataException;
}
