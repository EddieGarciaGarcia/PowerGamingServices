package com.eddie.ecommerce.service;

import java.sql.Connection;
import java.util.List;
import com.eddie.ecommerce.model.Pedido;

public interface PedidoServiceDAO {
	
	//Historial del Usuario
	public List<Pedido> findByEmail(String email) throws Exception;
	
	//Cancelar pedido
	public Pedido delete(String email, Integer idPedido) throws Exception;
	
	public Pedido create(Connection conexion,Pedido p) throws Exception;
	
	public boolean update(Connection conexion,Pedido p)throws Exception;
	
	//Abrir pedido
	public Pedido findByID(Connection conexion,Integer idPedido)throws Exception;
}
