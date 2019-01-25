package com.eddie.ecommerce.service;

import java.util.List;

import com.eddie.ecommerce.model.Pedido;

public interface PedidoServiceDAO {
	
	public List<Pedido> findAllHistorial(String email) throws Exception;
	
	public Pedido cancelPedido(String email, Integer idPedido) throws Exception;
}
