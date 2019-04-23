package com.eddie.ecommerce.dao;

import java.sql.Connection;
import java.util.List;

import com.eddie.ecommerce.model.LineaPedido;
import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.exceptions.DuplicateInstanceException;
import com.eddie.ecommerce.exceptions.InstanceNotFoundException;

public interface LineaPedidoDAO {

	public List<LineaPedido> findByPedido(Connection conexion,Integer idPedido)throws DataException;
	
	public LineaPedido findById(Connection conexion,Integer numeroLinea)throws InstanceNotFoundException, DataException;
	
	public LineaPedido create(Connection conexion,LineaPedido lp) throws DuplicateInstanceException, DataException;
	
	public long delete(Connection conexion,Integer id) throws DataException;
	
	public long deleteByPedido(Connection conexion,Integer id) throws DataException;
}
