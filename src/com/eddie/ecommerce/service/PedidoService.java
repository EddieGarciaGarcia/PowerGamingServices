package com.eddie.ecommerce.service;


import java.util.List;

import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.exceptions.DuplicateInstanceException;
import com.eddie.ecommerce.exceptions.InstanceNotFoundException;
import com.eddie.ecommerce.model.Pedido;

public interface PedidoService {
	
	//Historial del Usuario
	public List<Pedido> findByEmail(String email) throws InstanceNotFoundException, DataException;
	
	//Cancelar pedido
	public Pedido delete(String email, Integer idPedido) throws InstanceNotFoundException, DataException;
	
	public Pedido create(Pedido p) throws DuplicateInstanceException, DataException;
	
	//Abrir pedido
	public Pedido findByID(Integer idPedido)throws DataException;
}
