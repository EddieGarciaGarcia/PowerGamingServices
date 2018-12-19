package com.eddie.training.dao;

public interface PedidoDAO {

	public PedidoDAO create(PedidoDAO p) throws Exception;
	
	public boolean update(PedidoDAO p) throws Exception;
	
	public void delete(PedidoDAO p) throws Exception;
}
