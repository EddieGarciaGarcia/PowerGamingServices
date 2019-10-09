package com.eddie.ecommerce.service;


import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.model.LineaPedido;

import java.util.List;

public interface LineaPedidoService {
	
	//Listado de Lineas de pedido de un Pedido
	List<LineaPedido> findByPedido(Integer idPedido)throws DataException;
	
	//Linea de Pedido de la ecommerce
	LineaPedido findById(Integer numeroLinea)throws DataException;
	
	//Crear Lineas
	boolean create(LineaPedido lp) throws DataException;
	
	//Cancelar Linea de pedido
	boolean delete(Integer id) throws DataException;

	boolean deleteByPedido(Integer id) throws DataException;
}
