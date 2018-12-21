package com.eddie.training.dao;

import java.util.List;

import com.eddie.training.model.LineaPedido;

public interface LineaPedidoDAO {

	//public List<LineaPedido> findByPedido(id)
	
	public LineaPedido create(LineaPedido lp) throws Exception;
	
	public boolean update(LineaPedido lp) throws Exception;
	
	public void delete(LineaPedido lp) throws Exception;
}
