package com.eddie.ecommerce.service;

import com.eddie.ecommerce.model.Resultados;
import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.model.Pedido;

import java.util.List;

public interface PedidoService {

	//Historial del Usuario
	Resultados<Pedido> findByEmail(String email, int startIndex, int count) throws DataException;

	//Historial del Usuario
	List<Pedido> findAllByEmail(String email) throws DataException;
	
	List<Pedido> findByIds(List<Integer> ids)throws DataException;
	
	//Cancelar pedido
	void delete(Integer idPedido) throws DataException;

	boolean create(Pedido p) throws DataException;
	
	Pedido findByEmail(String email)throws DataException;
}
