package com.eddie.ecommerce.service;


import java.util.List;

import com.eddie.ecommerce.exceptions.DataException;
import com.eddie.ecommerce.exceptions.DuplicateInstanceException;
import com.eddie.ecommerce.exceptions.InstanceNotFoundException;
import com.eddie.ecommerce.model.LineaPedido;

public interface LineaPedidoService {
	
	//Listado de Lineas de pedido de un Pedido
	public List<LineaPedido> findByPedido(Integer idPedido)throws DataException;
	
	//Linea de Pedido de la ecommerce
	public LineaPedido findById(Integer numeroLinea)throws InstanceNotFoundException, DataException;
	
	//Crear Lineas
	public LineaPedido create(LineaPedido lp) throws DuplicateInstanceException, DataException;
	
	//Cancelar Linea de pedido
	public void delete(Integer id) throws DataException;
	
	public void deleteByPedido(Integer id) throws DataException;
}
