package com.eddie.ecommerce.service;


import java.sql.SQLException;

import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.exceptions.DuplicateInstanceException;
import com.eddie.ecommerce.exceptions.InstanceNotFoundException;
import com.eddie.ecommerce.model.Pedido;

public interface PedidoService {
	
	//Historial del Usuario
	public Resultados<Pedido> findByEmail(String email, int startIndex, int count) throws InstanceNotFoundException, SQLException, DataException;
	
	//Cancelar pedido
	public void delete(Integer idPedido) throws InstanceNotFoundException, SQLException, DataException;
	
	public Pedido create(Pedido p) throws DuplicateInstanceException, DataException, SQLException;
	
	//Abrir pedido
	public Pedido findByID(Integer idPedido)throws DataException, SQLException;
}
